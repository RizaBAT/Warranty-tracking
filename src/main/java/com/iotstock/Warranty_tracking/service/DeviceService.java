package com.iotstock.Warranty_tracking.service;


import com.iotstock.Warranty_tracking.model.Device;
import com.iotstock.Warranty_tracking.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Device saveDevice(Device device) {
        try {
            return deviceRepository.save(device);
        } catch (Exception e) {         
            System.err.println("Error saving device: " + e.getMessage());
            return null;
        }
    }

    public List<Device> getAllDevices() {
        try {
            return deviceRepository.findAll();
        } catch (Exception e) {
            System.err.println("Error fetching devices: " + e.getMessage());
            return null;
        }
    }
    public Optional<Device> getDeviceById(Long id) {
        try {
            return deviceRepository.findById(id);
        } catch (Exception e) {
            System.err.println("Error fetching device by ID: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Device addDevice(Device device) {
        try {
            deviceRepository.save(device);
        } catch (Exception e) {
            System.err.println("Error add device: " + e.getMessage());
        }
        return device;
    }

    public Device updateDevice(Device device) {
        try {
            deviceRepository.save(device);
        } catch (Exception e) {
            System.err.println("Error updating device: " + e.getMessage());
        }
        return device;
    }

    public void deleteDevice(Long id) {
        try {
            deviceRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error deleting device: " + e.getMessage());
        }
    }
}
