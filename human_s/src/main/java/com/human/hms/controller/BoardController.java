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
import com.human.hms.service.NoticeService;
import com.human.hms.vo.NoticeVO;
import com.human.hms.vo.PagingVO;


@Controller
@RequestMapping("/board")
public class BoardController {
	@GetMapping("/notice.no")
	public String noticeList(@RequestParam(value = "searchType", defaultValue = "name") String searchType,
            				@RequestParam(value = "search", defaultValue = "") String searchKeyword,
            				@RequestParam(defaultValue = "1") int page, Model model) {
		System.out.println(searchKeyword);
	    // 페이지가 1 미만이면 기본값으로 설정
	    page = (page < 1) ? 1 : page;
	    
        // searchType과 searchKeyword가 기본값을 잘 설정되었는지 확인
        System.out.println("searchType: " + searchType + ", searchKeyword: " + searchKeyword);
        int pageSize = 10; // 한 페이지당 10개의 게시물
        int totalCount = noticeServiceImpl.getTotalNoticeCount(); // 전체 게시물 수
        // PagingVO 초기화
	    PagingVO pagingVO = new PagingVO(page, pageSize, totalCount);
        // 검색 로직
        List<NoticeEntity> noticeList = noticeServiceImpl.searchNotices(searchType, searchKeyword,page,pageSize);
        model.addAttribute("noticeList", noticeList);
        model.addAttribute("searchType", searchType);
        model.addAttribute("search", searchKeyword);
        model.addAttribute("noticeList", noticeList);
	    model.addAttribute("paging", pagingVO);

	    return "board/notice_list";
	}
    @Autowired
    private NoticeService noticeServiceImpl;


    // 글 등록 페이지 요청
    @GetMapping("/write.do")
    public ModelAndView write(ModelAndView mav) {
        mav.setViewName("board/notice_write");
        return mav;
    }
    
    

    
	//글등록 요청
	@PostMapping("/writeProcess.do")
	public ModelAndView writeProcess(NoticeVO vo, HttpServletRequest request, ModelAndView mav) {
		
		//VO객체에 저장된 값을 Entity에 저장되도록 Builder를 이용해서 Entity객체를 생성함
		NoticeEntity entity = NoticeEntity.builder()
							  .author(vo.getAuthor())
							  .noticeTitle(vo.getNoticeTitle())
							  .noticeContent(vo.getNoticeContent())
							  .build();
		
		String viewName = "board/write";//글등록 실패시 뷰이름
		
		NoticeEntity savedEntity = noticeServiceImpl.insertNotice(entity, request);
		if(savedEntity != null) {//글등록 성공
			viewName = "redirect:/board/notice.no";//메인 페이지 재요청
		}else {//글등록 실패
			mav.addObject("msg", "게시글이 정상적으로 등록되지 않았습니다.");
		}
		mav.setViewName(viewName);
		
		return mav;
	}
    //상세보기
    @GetMapping("/notice_view.do")
    public ModelAndView view(long noticeId, ModelAndView mav) {
        // 조회수 증가
        noticeServiceImpl.updateReadCount(noticeId);

        // 상세 페이지 정보 조회
        Optional<NoticeEntity> optional = noticeServiceImpl.getNoticeById(noticeId);
        optional.ifPresent(noticeEntity -> mav.addObject("notice", noticeEntity));  // notice라는 이름으로 데이터 전달
        mav.setViewName("board/notice_view");
        System.out.println(noticeId);
        return mav;
    }
    
    
    

    // 수정 페이지 요청
    @GetMapping("/update.do")
    public ModelAndView update(@RequestParam("noticeIdx") long noticeIdx, ModelAndView mav) {
        // 수정할 게시글 정보 조회
        Optional<NoticeEntity> optional = noticeServiceImpl.getNoticeById(noticeIdx);
        
        if (optional.isPresent()) {
            NoticeEntity entity = optional.get();
            mav.addObject("notice", entity); // 뷰에서 "noticeVO"로 접근 가능
            mav.setViewName("board/notice_update");
        } else {
            mav.setViewName("error/404"); // 게시글이 존재하지 않는 경우 404 페이지로 이동
            mav.addObject("msg", "존재하지 않는 게시글입니다.");
        }
        
        return mav;
    }


    // 글 수정 처리
    @PostMapping("/updateProcess.do")
    public ModelAndView updateProcess(NoticeVO vo, ModelAndView mav) {
        System.out.println("Received ID: " + vo.getNoticeIdx());
        System.out.println("Received Title: " + vo.getNoticeTitle());
        System.out.println("Received Content: " + vo.getNoticeContent());

        String viewName = "notice/update"; // 실패 시 기본 뷰

        if (vo.getNoticeIdx() == null) {
            mav.addObject("msg", "잘못된 요청입니다. ID가 없습니다.");
            mav.setViewName("error/invalid_request");
            return mav;
        }

        int result = noticeServiceImpl.updateNotice(vo);
        if (result == 1) {
            viewName = "redirect:/board/notice.no"; // 수정 성공 후 목록 페이지로 이동
        } else {
            mav.addObject("msg", "게시글을 찾을 수 없습니다.");
            viewName = "error/invalid_request";
        }
        mav.setViewName(viewName);

        return mav;
    }




 // 글 삭제 요청
    @GetMapping("/deleteProcess.do")
    public ModelAndView deleteProcess(@RequestParam(value = "noticeIdx", required = false) Long noticeIdx, ModelAndView mav) {
        String viewName = "notice/view"; // 기본적으로 실패 시 뷰

        if (noticeIdx != null) {
            int result = noticeServiceImpl.deleteNotice(noticeIdx);
            if (result == 1) { // 삭제 성공 시 메인 페이지로 리다이렉트
                viewName = "redirect:/board/notice.no";
            } else {
                mav.addObject("msg", "게시글 삭제에 실패했습니다.");
            }
            System.out.println();
        } else {
            mav.addObject("msg", "잘못된 요청입니다. 게시글 ID가 없습니다.");
        }
        
        mav.setViewName(viewName);
        return mav;
    }

    @GetMapping("/search.do")
    public String search(@RequestParam(value = "searchType", defaultValue = "name") String searchType,
                         @RequestParam(value = "search", defaultValue = "") String searchKeyword,
                         Model model) {
        // searchType과 searchKeyword가 기본값을 잘 설정되었는지 확인
        System.out.println("searchType: " + searchType + ", searchKeyword: " + searchKeyword);
        
        // 검색 로직
		/*
		 * List<NoticeEntity> noticeList = noticeServiceImpl.searchNotices(searchType,
		 * searchKeyword); model.addAttribute("noticeList", noticeList);
		 * model.addAttribute("searchType", searchType); model.addAttribute("search",
		 * searchKeyword);
		 */
        
        return "board/notice_list";  // 검색 결과를 표시할 JSP 파일 경로
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










