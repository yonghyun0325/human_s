package com.human.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.human.hms.entity.AddressEntity;
import com.human.hms.entity.SellerEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {


}
