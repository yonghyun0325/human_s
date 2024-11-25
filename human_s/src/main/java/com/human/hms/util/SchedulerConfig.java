package com.human.hms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.human.hms.controller.ManagerController;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableScheduling // 스케줄링을 활성화
public class SchedulerConfig {
	
	private ManagerController managerController;

//    // 매일 09시에 api 테이블 내용 변경
//    @Scheduled(cron = "0 0 9 * * *")   
//    public void ApiSetUp9() {
//    	ApiSetUp();
//    }
//    
//    // 매일 10시에 api 테이블 내용 변경
//    @Scheduled(cron = "0 0 10 * * *")   
//    public void ApiSetUp10() {
//    	ApiSetUp();
//    }
//    
//    // 매일 11시에 api 테이블 내용 변경
//    @Scheduled(cron = "0 0 11 * * *")   
//    public void ApiSetUp11() {
//    	ApiSetUp();
//    }
//    
//    // 매일 12시에 api 테이블 내용 변경
//    @Scheduled(cron = "0 0 12 * * *")   
//    public void ApiSetUp12() {
//    	ApiSetUp();
//    }
    
    public void ApiSetUp() {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(new Date());
        
    	managerController.insertTimePriceReal();
    	managerController.insertTimeDailyPrice(today);
    }

    public static void main(String[] args) {
        // 애플리케이션 실행 시 Spring 컨텍스트를 생성하여 스케줄링이 작동하도록 합니다.
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SchedulerConfig.class);
    }
}
