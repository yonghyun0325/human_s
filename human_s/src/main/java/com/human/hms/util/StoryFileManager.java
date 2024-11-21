package com.human.hms.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StoryFileManager {

    // 단일 파일 저장 메서드 (메인 이미지 및 컨텐츠 이미지 공용)
    public String saveFile(MultipartFile file, HttpServletRequest request) {
        if (file == null || file.isEmpty()) {
            return null; // 파일이 없으면 null 반환
        }

        try {
            // 업로드 디렉토리 경로 생성
            String uploadDir = request.getServletContext().getRealPath("/resources/uploads/");
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs(); // 디렉터리가 없으면 생성
            }

            // 파일명 생성
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = timeStamp + "_" + file.getOriginalFilename();
            File destination = new File(uploadDir, fileName);

            // 파일 저장
            file.transferTo(destination);

            // 저장된 경로 반환
            return "/resources/uploads/" + fileName;
        } catch (Exception e) {
            log.error("파일 저장 중 오류 발생: {}", e.getMessage(), e);
            return null;
        }
    }

    // 컨텐츠 이미지 단일 파일 저장
    public String saveContentImage(MultipartFile contentImage, HttpServletRequest request) {
        if (contentImage == null || contentImage.isEmpty()) {
            log.warn("컨텐츠 이미지 파일이 비어있거나 null입니다.");
            return null; // 파일이 없으면 null 반환
        }

        // saveFile 메서드를 재사용하여 파일 저장
        String savedPath = saveFile(contentImage, request);
        if (savedPath != null) {
            log.info("컨텐츠 이미지 저장 완료: {}", savedPath);
        } else {
            log.error("컨텐츠 이미지 저장 실패");
        }
        return savedPath;
    }

	
}
