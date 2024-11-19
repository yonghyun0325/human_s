package com.human.hms.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.human.hms.entity.StoryEntity;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StoryFileManager {

    // 단일 파일 저장 메서드
    public String saveFile(MultipartFile file, HttpServletRequest request) {
        if (file == null || file.isEmpty()) {
            return null; // 파일이 없으면 null 반환
        }

        try {
            // 업로드 디렉터리 경로 생성
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

    // 다중 파일 처리 및 StoryEntity 업데이트
    public StoryEntity handleFile(StoryEntity entity, HttpServletRequest request) {
        try {
            if (entity.getUploadFiles() != null) {
                StringBuilder filePaths = new StringBuilder();

                for (MultipartFile file : entity.getUploadFiles()) {
                    if (file != null && !file.isEmpty()) {
                        String savedPath = saveFile(file, request);
                        if (savedPath != null) {
                            filePaths.append(savedPath).append(","); // 저장된 파일 경로 추가
                        }
                    }
                }

                // 파일 경로 문자열에서 마지막 ',' 제거
                if (filePaths.length() > 0) {
                    entity.setImage(filePaths.substring(0, filePaths.length() - 1)); // 파일 경로 업데이트
                }
            }
        } catch (Exception e) {
            log.error("다중 파일 처리 중 오류 발생: {}", e.getMessage(), e);
        }

        return entity; // 업데이트된 엔티티 반환
    }
}
