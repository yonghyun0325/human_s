package com.human.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.human.hms.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{

}
