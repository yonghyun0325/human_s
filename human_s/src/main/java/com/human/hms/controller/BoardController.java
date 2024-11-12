package com.human.hms.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.human.hms.entity.NoticeEntity;
import com.human.hms.service.NoticeService;
import com.human.hms.vo.NoticeVO;
import com.human.hms.vo.PagingVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	@GetMapping("/notice.no")
	public String noticeList(@RequestParam(defaultValue = "1") int page, Model model) {
	    // 페이지가 1 미만이면 기본값으로 설정
	    page = (page < 1) ? 1 : page;

	    int pageSize = 10; // 한 페이지당 10개의 게시물
	    int totalCount = noticeServiceImpl.getTotalNoticeCount(); // 전체 게시물 수
	    
	    // PagingVO 초기화
	    PagingVO pagingVO = new PagingVO(page, pageSize, totalCount);
	    
	    // 현재 페이지에 해당하는 공지사항 목록
	    List<NoticeEntity> noticeList = noticeServiceImpl.getNotices(page, pageSize);
	    
	    model.addAttribute("noticeList", noticeList);
	    model.addAttribute("paging", pagingVO);
	    
	    return "board/notice_list";
	}
    @Autowired
    private NoticeService noticeServiceImpl;


    // 글 등록 페이지 요청
    @GetMapping("/noticewrite.do")
    public ModelAndView write(ModelAndView mav) {
        mav.setViewName("notice/notice_write");
        return mav;
    }

    // 글 등록 처리
    @PostMapping("/writeProcess.do")
    public ModelAndView writeProcess(NoticeVO vo, HttpServletRequest request, ModelAndView mav) {
        // NoticeVO 객체를 NoticeEntity로 변환하고 저장
        NoticeEntity savedEntity = noticeServiceImpl.createNotice(vo);
        String viewName = "notice/write"; // 기본적으로 실패 시 뷰

        if (savedEntity != null) { // 글 등록 성공 시
            viewName = "redirect:/index.do"; // 메인 페이지로 리다이렉트
        } else { // 글 등록 실패 시
            mav.addObject("msg", "게시글이 정상적으로 등록되지 않았습니다.");
        }
        mav.setViewName(viewName);

        return mav;
    }

    // 상세 보기 페이지 요청
    @GetMapping("/view.do")
    public ModelAndView view(long noticeIdx, ModelAndView mav) {
        // 조회수 증가
        noticeServiceImpl.updateReadCount(noticeIdx);

        // 상세 페이지 정보 조회
        Optional<NoticeEntity> optional = noticeServiceImpl.getNoticeById(noticeIdx);
        optional.ifPresent(noticeEntity -> mav.addObject("noticeVO", noticeEntity));
        mav.setViewName("notice/view");

        return mav;
    }

    // 수정 페이지 요청
    @GetMapping("/update.do")
    public ModelAndView update(long noticeIdx, ModelAndView mav) {
        // 수정할 게시글 정보 조회
        Optional<NoticeEntity> optional = noticeServiceImpl.getNoticeById(noticeIdx);
        optional.ifPresent(entity -> mav.addObject("noticeVO", entity));
        mav.setViewName("notice/update");

        return mav;
    }

    // 글 수정 처리
    @PostMapping("/updateProcess.do")
    public ModelAndView updateProcess(NoticeVO vo, ModelAndView mav) {
        String viewName = "notice/update"; // 기본적으로 실패 시 뷰

        int result = noticeServiceImpl.updateNotice(vo);
        if (result == 1) { // 수정 성공 시
            // 수정된 글 상세 페이지로 이동
            Optional<NoticeEntity> optional = noticeServiceImpl.getNoticeById(vo.getNoticeIdx());
            optional.ifPresent(newEntity -> mav.addObject("noticeVO", newEntity));
            viewName = "notice/view";
        }
        mav.setViewName(viewName);

        return mav;
    }

    // 글 삭제 요청
    @GetMapping("/deleteProcess.do")
    public ModelAndView deleteProcess(long noticeIdx, ModelAndView mav) {
        String viewName = "notice/view"; // 기본적으로 실패 시 뷰
        int result = noticeServiceImpl.deleteNotice(noticeIdx);
        if (result == 1) { // 삭제 성공 시 메인 페이지로 리다이렉트
            viewName = "redirect:/index.do";
        }
        mav.setViewName(viewName);

        return mav;
    }
    
    
    @GetMapping("/faq.no")
	public String faq() {
		return "board/faq";
	}
    
    @GetMapping("/productquestion.no")
    public String productquestion() {
    	return "board/productquestion";
    }
    
    @GetMapping("/review.no")
    public String review() {
    	return "board/review";
    }
    
    
    
    
    
    
    
    
}










