package com.iotstock.Warranty_tracking.repository;


import com.iotstock.Warranty_tracking.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}


