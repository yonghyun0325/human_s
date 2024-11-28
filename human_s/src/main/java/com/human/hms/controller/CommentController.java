package com.human.hms.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.human.hms.entity.CinquiryEntity;
import com.human.hms.entity.CommentEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.service.CinquiryService;
import com.human.hms.service.CommentService;

@RestController
@RequestMapping("/board/cinquiry")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CinquiryService cinquiryService;

    //고객문의 댓글 등록
    @ResponseBody
    @GetMapping("/saveComment.do")
    public CommentEntity saveComment(@RequestParam String comment, @RequestParam Long id, HttpServletRequest request) {
    	
    	CommentEntity entity = CommentEntity.builder()
    									.comment(comment)
    									.build();
    	
    	Optional<CinquiryEntity> cinquiryOptional = cinquiryService.getCinquiryById(id);
    	if (cinquiryOptional.isPresent()) {
    		CinquiryEntity cinquiry = cinquiryOptional.get();
    		entity.updateCinquiry(cinquiry);
    	}
    	
    	UserEntity user = (UserEntity) request.getSession().getAttribute("user");
    	entity.updateUser(user);
    	
    	return commentService.insertComment(entity);
    }
    
    //스토리 댓글 삭제
    @ResponseBody
    @GetMapping("/deleteComment.do")
    public List<CommentEntity> deleteComment(@RequestParam int ccIdx, @RequestParam Long id){
    	List<CommentEntity> commentList = null;
    	
    	commentService.deleteComment(ccIdx);
    	commentList = commentService.getCommentBycinquiry(id);
    	
    	
    	return commentList;
    }
    
    
}
