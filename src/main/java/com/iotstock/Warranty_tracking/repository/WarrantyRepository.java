package com.iotstock.Warranty_tracking.repository;



import com.iotstock.Warranty_tracking.model.Warranty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
}

