package com.human.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.human.hms.entity.NoticeEntity;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
	
	
}
