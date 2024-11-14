package com.human.hms.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.human.hms.entity.NoticeEntity;
import com.human.hms.entity.ReviewEntity;
import com.human.hms.service.NoticeService;
import com.human.hms.service.ReviewService;
import com.human.hms.vo.NoticeVO;
import com.human.hms.vo.PagingVO;
import com.human.hms.vo.ReviewVO;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private NoticeService noticeServiceImpl;

    @Autowired
    private ReviewService reviewServiceImpl;

    // 게시판 공지사항 목록
    @GetMapping("/notice.no")
    public String noticeList(@RequestParam(value = "searchType", defaultValue = "name") String searchType,
                             @RequestParam(value = "search", defaultValue = "") String searchKeyword,
                             @RequestParam(defaultValue = "1") int page, Model model) {
        System.out.println(searchKeyword);

        // 페이지가 1 미만이면 기본값으로 설정
        page = Math.max(1, page);

        // 검색 로직
        int pageSize = 10;
        int totalCount = noticeServiceImpl.getTotalNoticeCount();
        PagingVO pagingVO = new PagingVO(page, pageSize, totalCount);
        List<NoticeEntity> noticeList = noticeServiceImpl.searchNotices(searchType, searchKeyword, page, pageSize);

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("searchType", searchType);
        model.addAttribute("search", searchKeyword);
        model.addAttribute("paging", pagingVO);

        return "board/notice_list";
    }

    // 게시판 리뷰 목록
    @GetMapping("/review.no")
    public String reviewList(@RequestParam(value = "searchType", defaultValue = "name") String searchType,
                             @RequestParam(value = "search", defaultValue = "") String searchKeyword,
                             @RequestParam(defaultValue = "1") int page, Model model) {
        System.out.println(searchKeyword);

        page = Math.max(1, page);

        // 검색 로직
        int pageSize = 10;
        int totalCount = reviewServiceImpl.getTotalReviewCount();
        PagingVO pagingVO = new PagingVO(page, pageSize, totalCount);
        List<ReviewEntity> reviewList = reviewServiceImpl.searchReviews(searchType, searchKeyword, page, pageSize);

        model.addAttribute("reviewList", reviewList);
        model.addAttribute("searchType", searchType);
        model.addAttribute("search", searchKeyword);
        model.addAttribute("paging", pagingVO);

        return "board/review_list";
    }

    // 공지사항 글 작성 페이지
    @GetMapping("/notice/write.do")
    public ModelAndView noticeWrite(ModelAndView mav) {
        mav.setViewName("board/notice_write");
        return mav;
    }

    // 리뷰 글 작성 페이지
    @GetMapping("/review/write.do")
    public ModelAndView reviewWrite(ModelAndView mav) {
        mav.setViewName("board/review_write");
        return mav;
    }

    // 공지사항 글 작성 처리
    @PostMapping("/notice/writeProcess.do")
    public ModelAndView noticeWriteProcess(NoticeVO vo, HttpServletRequest request, ModelAndView mav) {
        NoticeEntity entity = NoticeEntity.builder()
                .author(vo.getAuthor())
                .noticeTitle(vo.getNoticeTitle())
                .noticeContent(vo.getNoticeContent())
                .build();

        String viewName = "board/write";
        NoticeEntity savedEntity = noticeServiceImpl.insertNotice(entity, request);

        if (savedEntity != null) {
            viewName = "redirect:/board/notice.no";
        } else {
            mav.addObject("msg", "게시글이 정상적으로 등록되지 않았습니다.");
        }
        mav.setViewName(viewName);
        return mav;
    }

    // 리뷰 글 작성 처리
    @PostMapping("/review/writeProcess.do")
    public ModelAndView reviewWriteProcess(ReviewVO vo, HttpServletRequest request, ModelAndView mav) {
        ReviewEntity entity = ReviewEntity.builder()
                .author(vo.getAuthor())
                .reviewTitle(vo.getReviewTitle())
                .reviewContent(vo.getReviewContent())
                .rating(vo.getRating())
                .build();

        String viewName = "board/write";
        ReviewEntity savedEntity = reviewServiceImpl.insertReview(entity, request);

        if (savedEntity != null) {
            viewName = "redirect:/board/review.no";
        } else {
            mav.addObject("msg", "게시글이 정상적으로 등록되지 않았습니다.");
        }
        mav.setViewName(viewName);
        return mav;
    }

    // 공지사항 상세 보기
    @GetMapping("/notice/view.do")
    public ModelAndView noticeView(long noticeId, ModelAndView mav) {
        noticeServiceImpl.updateReadCount(noticeId);

        Optional<NoticeEntity> optional = noticeServiceImpl.getNoticeById(noticeId);
        optional.ifPresent(noticeEntity -> mav.addObject("notice", noticeEntity));
        mav.setViewName("board/notice_view");
        return mav;
    }

    // 리뷰 상세 보기
    @GetMapping("/review/view.do")
    public ModelAndView reviewView(long reviewId, ModelAndView mav) {
        reviewServiceImpl.updateReadCount(reviewId);

        Optional<ReviewEntity> optional = reviewServiceImpl.getReviewById(reviewId);
        optional.ifPresent(reviewEntity -> mav.addObject("review", reviewEntity));
        mav.setViewName("board/review_view");
        return mav;
    }

    // 공지사항 수정 페이지 요청
    @GetMapping("/notice/update.do")
    public ModelAndView noticeUpdate(@RequestParam("noticeIdx") long noticeIdx, ModelAndView mav) {
        Optional<NoticeEntity> optional = noticeServiceImpl.getNoticeById(noticeIdx);
        
        if (optional.isPresent()) {
            mav.addObject("notice", optional.get());
            mav.setViewName("board/notice_update");
        } else {
            mav.setViewName("error/404");
            mav.addObject("msg", "존재하지 않는 게시글입니다.");
        }
        return mav;
    }

    // 리뷰 수정 페이지 요청
    @GetMapping("/review/update.do")
    public ModelAndView reviewUpdate(@RequestParam("reviewIdx") long reviewIdx, ModelAndView mav) {
        Optional<ReviewEntity> optional = reviewServiceImpl.getReviewById(reviewIdx);

        if (optional.isPresent()) {
            mav.addObject("review", optional.get());
            mav.setViewName("board/review_update");
        } else {
            mav.setViewName("error/404");
            mav.addObject("msg", "존재하지 않는 게시글입니다.");
        }
        return mav;
    }

    // 공지사항 수정 처리
    @PostMapping("/notice/updateProcess.do")
    public ModelAndView noticeUpdateProcess(NoticeVO vo, ModelAndView mav) {
        if (vo.getNoticeIdx() == null) {
            mav.addObject("msg", "잘못된 요청입니다. ID가 없습니다.");
            mav.setViewName("error/invalid_request");
            return mav;
        }

        int result = noticeServiceImpl.updateNotice(vo);
        mav.setViewName(result == 1 ? "redirect:/board/notice.no" : "error/invalid_request");
        return mav;
    }

    // 리뷰 수정 처리
    @PostMapping("/review/updateProcess.do")
    public ModelAndView reviewUpdateProcess(ReviewVO vo, ModelAndView mav) {
        if (vo.getReviewIdx() == null) {
            mav.addObject("msg", "잘못된 요청입니다. ID가 없습니다.");
            mav.setViewName("error/invalid_request");
            return mav;
        }

        int result = reviewServiceImpl.updateReview(vo);
        mav.setViewName(result == 1 ? "redirect:/board/review.no" : "error/invalid_request");
        return mav;
    }

    // 공지사항 삭제 처리
    @GetMapping("/notice/deleteProcess.do")
    public ModelAndView noticeDeleteProcess(@RequestParam(value = "noticeIdx", required = false) Long noticeIdx, ModelAndView mav) {
        if (noticeIdx == null) {
            mav.addObject("msg", "잘못된 요청입니다. 게시글 ID가 없습니다.");
            mav.setViewName("error/invalid_request");
            return mav;
        }

        int result = noticeServiceImpl.deleteNotice(noticeIdx);
        mav.setViewName(result == 1 ? "redirect:/board/notice.no" : "error/invalid_request");
        return mav;
    }

    // 리뷰 삭제 처리
    @GetMapping("/review/deleteProcess.do")
    public ModelAndView reviewDeleteProcess(@RequestParam(value = "reviewIdx", required = false) Long reviewIdx, ModelAndView mav) {
        if (reviewIdx == null) {
            mav.addObject("msg", "잘못된 요청입니다. 게시글 ID가 없습니다.");
            mav.setViewName("error/invalid_request");
            return mav;
        }

        int result = reviewServiceImpl.deleteReview(reviewIdx);
        mav.setViewName(result == 1 ? "redirect:/board/review.no" : "error/invalid_request");
        return mav;
    }
    @GetMapping("/faq.no")
    public String faq() {
    	return "board/faq";
    }
    
    
}
