package com.human.hms.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.hms.entity.CinquiryEntity;
import com.human.hms.entity.CommentEntity;
import com.human.hms.entity.NoticeEntity;
import com.human.hms.entity.PinquiryEntity;
import com.human.hms.entity.ProductEntity;
import com.human.hms.entity.ReviewEntity;
import com.human.hms.service.CinquiryService;
import com.human.hms.service.CommentService;
import com.human.hms.service.NoticeService;
import com.human.hms.service.PinquiryService;
import com.human.hms.service.ProductService;
import com.human.hms.service.ReviewService;
import com.human.hms.vo.CinquiryVO;
import com.human.hms.vo.NoticeVO;
import com.human.hms.vo.PagingVO;
import com.human.hms.vo.PinquiryVO;
import com.human.hms.vo.ReviewVO;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {
	
    private NoticeService noticeServiceImpl;
    private ReviewService reviewServiceImpl;
    private PinquiryService pinquiryServiceImpl;
    private CinquiryService cinquiryServiceImpl;
    private CommentService commentService;
    private ProductService productServiceImpl;

    // 공지사항 목록
    @GetMapping("/notice.no")
    public String noticeList(@RequestParam(value = "searchType", defaultValue = "name") String searchType,
                             @RequestParam(value = "search", defaultValue = "") String searchKeyword,
                             @RequestParam(defaultValue = "1") int page, Model model) {
        page = Math.max(1, page);
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

    // 리뷰 목록
    @GetMapping("/review.no")
    public String reviewList(@RequestParam(value = "searchType", defaultValue = "name") String searchType,
                             @RequestParam(value = "search", defaultValue = "") String searchKeyword,
                             @RequestParam(defaultValue = "1") int page, Model model) {
        page = Math.max(1, page);
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

    // 상품문의 목록
    @GetMapping("/pinquiry.no")
    public String pinquiryList(@RequestParam(value = "searchType", defaultValue = "name") String searchType,
                               @RequestParam(value = "search", defaultValue = "") String searchKeyword,
                               @RequestParam(defaultValue = "1") int page, Model model) {
        page = Math.max(1, page);
        int pageSize = 10;
        int totalCount = pinquiryServiceImpl.getTotalPinquiryCount();
        PagingVO pagingVO = new PagingVO(page, pageSize, totalCount);
        List<PinquiryEntity> pinquiryList = pinquiryServiceImpl.searchPinquirys(searchType, searchKeyword, page, pageSize);

        model.addAttribute("pinquiryList", pinquiryList);
        model.addAttribute("searchType", searchType);
        model.addAttribute("search", searchKeyword);
        model.addAttribute("paging", pagingVO);

        return "board/pinquiry_list";
    }
    
    // 고객문의 목록
    @GetMapping("/cinquiry.no")
    public String cinquiryList(@RequestParam(value = "searchType", defaultValue = "name") String searchType,
                               @RequestParam(value = "search", defaultValue = "") String searchKeyword,
                               @RequestParam(defaultValue = "1") int page, Model model) {
        page = Math.max(1, page);
        int pageSize = 10;
        int totalCount = cinquiryServiceImpl.getTotalCinquiryCount();
        PagingVO pagingVO = new PagingVO(page, pageSize, totalCount);
        List<CinquiryEntity> cinquiryList = cinquiryServiceImpl.searchCinquirys(searchType, searchKeyword, page, pageSize);

        model.addAttribute("cinquiryList", cinquiryList);
        model.addAttribute("searchType", searchType);
        model.addAttribute("search", searchKeyword);
        model.addAttribute("paging", pagingVO);

        return "board/cinquiry_list";
    }

    // 공지사항 작성 페이지
    @GetMapping("/notice/write.do")
    public ModelAndView noticeWrite(ModelAndView mav) {
        mav.setViewName("board/notice_write");
        return mav;
    }

    // 리뷰 작성 페이지
    @GetMapping("/review/write.do")
    public ModelAndView reviewWrite(ModelAndView mav, @RequestParam(required = false, defaultValue = "0") int pdtIdx) {
        mav.setViewName("board/review_write");

        if(pdtIdx != 0) {
        	ProductEntity product = productServiceImpl.findbyId(pdtIdx);
        	mav.addObject("product", product);        	
        }
        
        return mav;
    }

    // 상품문의 작성 페이지
    @GetMapping("/pinquiry/write.do")
    public ModelAndView pinquiryWrite(ModelAndView mav, @RequestParam(required = false, defaultValue = "0") int pdtIdx) {
        mav.setViewName("board/pinquiry_write");
        
        if(pdtIdx != 0) {
        	ProductEntity product = productServiceImpl.findbyId(pdtIdx);
        	mav.addObject("product", product);        	
        }
        
        return mav;
    }

    // 고객문의 작성 페이지
    @GetMapping("/cinquiry/write.do")
    public ModelAndView cinquiryWrite(ModelAndView mav) {
        mav.setViewName("mypage/inquirywrite");
        return mav;
    }

    // 공지사항 작성 처리
    @PostMapping("/notice/writeProcess.do")
    public ModelAndView noticeWriteProcess(NoticeVO vo, HttpServletRequest request, ModelAndView mav) {
        NoticeEntity entity = NoticeEntity.builder()
                .author(vo.getAuthor())
                .noticeTitle(vo.getNoticeTitle())
                .noticeContent(vo.getNoticeContent())
                .build();

        NoticeEntity savedEntity = noticeServiceImpl.insertNotice(entity, request);
        String viewName = (savedEntity != null) ? "redirect:/board/notice.no" : "board/write";
        mav.setViewName(viewName);
        return mav;
    }

    // 리뷰 작성 처리
    @PostMapping("/review/writeProcess.do")
    public ModelAndView reviewWriteProcess(ReviewVO vo, HttpServletRequest request, ModelAndView mav) {
        ReviewEntity entity = ReviewEntity.builder()
                .author(vo.getAuthor())
                .reviewTitle(vo.getReviewTitle())
                .reviewContent(vo.getReviewContent())
                .rating(vo.getRating())
                .build();
        if(vo.getPdtIdx() != 0) {
        	entity.updateProductEntity(productServiceImpl.getProductById(vo.getPdtIdx()));        	
        }

        ReviewEntity savedEntity = reviewServiceImpl.insertReview(entity, request);
        String viewName = (savedEntity != null) ? "redirect:/board/review.no" : "board/write";
        mav.setViewName(viewName);
        return mav;
    }

    // 상품문의 작성 처리
    @PostMapping("/pinquiry/writeProcess.do")
    public ModelAndView pinquiryWriteProcess(PinquiryVO vo, HttpServletRequest request, ModelAndView mav) {
        PinquiryEntity entity = PinquiryEntity.builder()
                .author(vo.getAuthor())
                .pinquiryTitle(vo.getPinquiryTitle())
                .pinquiryContent(vo.getPinquiryContent())
                .build();
        if(vo.getPdtIdx() != 0) {
        	entity.updateProductEntity(productServiceImpl.getProductById(vo.getPdtIdx()));        	
        }

        PinquiryEntity savedEntity = pinquiryServiceImpl.insertPinquiry(entity, request);
        String viewName = (savedEntity != null) ? "redirect:/board/pinquiry.no" : "board/write";
        mav.setViewName(viewName);
        return mav;
    }

    // 고객문의 작성 처리
    @PostMapping("/cinquiry/writeProcess.do")
    public ModelAndView cinquiryWriteProcess(CinquiryVO vo, HttpServletRequest request, ModelAndView mav) {
        CinquiryEntity entity = CinquiryEntity.builder()
                .author(vo.getAuthor())
                .cinquiryTitle(vo.getCinquiryTitle())
                .cinquiryContent(vo.getCinquiryContent())
                .build();

        CinquiryEntity savedEntity = cinquiryServiceImpl.insertCinquiry(entity, request);
        String viewName = (savedEntity != null) ? "redirect:/board/cinquiry.no" : "board/write";
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

    // 상품문의 상세 보기
    @GetMapping("/pinquiry/view.do")
    public ModelAndView pinquiryView(long pinquiryId, ModelAndView mav) {
        pinquiryServiceImpl.updateReadCount(pinquiryId);
        Optional<PinquiryEntity> optional = pinquiryServiceImpl.getPinquiryById(pinquiryId);
        optional.ifPresent(pinquiryEntity -> mav.addObject("pinquiry", pinquiryEntity));
        mav.setViewName("board/pinquiry_view");
        return mav;
    }

    // 고객문의 상세 보기
    @GetMapping("/cinquiry/view.do")
    public ModelAndView cinquiryView(long cinquiryId, ModelAndView mav) {
        cinquiryServiceImpl.updateReadCount(cinquiryId);
        Optional<CinquiryEntity> optional = cinquiryServiceImpl.getCinquiryById(cinquiryId);
        optional.ifPresent(cinquiryEntity -> mav.addObject("cinquiry", cinquiryEntity));
        
        List<CommentEntity> comment = commentService.getCommentBycinquiry(cinquiryId);
        mav.addObject("ComList", comment);
        mav.setViewName("board/cinquiry_view");
        return mav;
    }
    
    //상품문의 판매자 답변 등록
    @GetMapping("/pinquiry/saveComment.do")
    @ResponseBody
    public PinquiryEntity saveComment(@RequestParam String comment, @RequestParam Long id) {
    	PinquiryEntity pinquiry = null;
    	
    	pinquiry = pinquiryServiceImpl.updateComment(comment, id);
    	
    	return pinquiry;
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

    // 상품문의 수정 페이지 요청
    @GetMapping("/pinquiry/update.do")
    public ModelAndView pinquiryUpdate(@RequestParam("pinquiryIdx") long pinquiryIdx, ModelAndView mav) {
        Optional<PinquiryEntity> optional = pinquiryServiceImpl.getPinquiryById(pinquiryIdx);
        if (optional.isPresent()) {
            mav.addObject("pinquiry", optional.get());
            mav.setViewName("board/pinquiry_update");
        } else {
            mav.setViewName("error/404");
            mav.addObject("msg", "존재하지 않는 게시글입니다.");
        }
        return mav;
    }

    // 고객문의 수정 페이지 요청
    @GetMapping("/cinquiry/update.do")
    public ModelAndView cinquiryUpdate(@RequestParam("cinquiryIdx") long cinquiryIdx, ModelAndView mav) {
        Optional<CinquiryEntity> optional = cinquiryServiceImpl.getCinquiryById(cinquiryIdx);
        if (optional.isPresent()) {
            mav.addObject("cinquiry", optional.get());
            mav.setViewName("board/cinquiry_update");
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

    // 상품문의 수정 처리
    @PostMapping("/pinquiry/updateProcess.do")
    public ModelAndView pinquiryUpdateProcess(PinquiryVO vo, ModelAndView mav) {
        if (vo.getPinquiryIdx() == null) {
            mav.addObject("msg", "잘못된 요청입니다. ID가 없습니다.");
            mav.setViewName("error/invalid_request");
            return mav;
        }
        int result = pinquiryServiceImpl.updatePinquiry(vo);
        mav.setViewName(result == 1 ? "redirect:/board/pinquiry.no" : "error/invalid_request");
        return mav;
    }

    // 고객문의 수정 처리
    @PostMapping("/cinquiry/updateProcess.do")
    public ModelAndView cinquiryUpdateProcess(CinquiryVO vo, ModelAndView mav) {
        if (vo.getCinquiryIdx() == null) {
            mav.addObject("msg", "잘못된 요청입니다. ID가 없습니다.");
            mav.setViewName("error/invalid_request");
            return mav;
        }
        int result = cinquiryServiceImpl.updateCinquiry(vo);
        mav.setViewName(result == 1 ? "redirect:/board/cinquiry.no" : "error/invalid_request");
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

    // 상품문의 삭제 처리
    @GetMapping("/pinquiry/deleteProcess.do")
    public ModelAndView pinquiryDeleteProcess(@RequestParam(value = "pinquiryIdx", required = false) Long pinquiryIdx, ModelAndView mav) {
        if (pinquiryIdx == null) {
            mav.addObject("msg", "잘못된 요청입니다. 게시글 ID가 없습니다.");
            mav.setViewName("error/invalid_request");
            return mav;
        }
        int result = pinquiryServiceImpl.deletePinquiry(pinquiryIdx);
        mav.setViewName(result == 1 ? "redirect:/board/pinquiry.no" : "error/invalid_request");
        return mav;
    }

    // 고객문의 삭제 처리
    @GetMapping("/cinquiry/deleteProcess.do")
    public ModelAndView cinquiryDeleteProcess(@RequestParam(value = "cinquiryIdx", required = false) Long cinquiryIdx, ModelAndView mav) {
        if (cinquiryIdx == null) {
            mav.addObject("msg", "잘못된 요청입니다. 게시글 ID가 없습니다.");
            mav.setViewName("error/invalid_request");
            return mav;
        }
        int result = cinquiryServiceImpl.deleteCinquiry(cinquiryIdx);
        mav.setViewName(result == 1 ? "redirect:/board/cinquiry.no" : "error/invalid_request");
        return mav;
    }

    // FAQ 페이지
    @GetMapping("/faq.no")
    public String faq() {
        return "board/faq";
    }
}
