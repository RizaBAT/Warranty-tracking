package com.iotstock.Warranty_tracking.service;

import com.iotstock.Warranty_tracking.model.Warranty;
import com.iotstock.Warranty_tracking.repository.WarrantyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WarrantyService {

    @Autowired
    private WarrantyRepository warrantyRepository;

    public Warranty saveWarranty(Warranty warranty) {
        try {
            return warrantyRepository.save(warranty);
        } catch (Exception e) {
            System.err.println("Error saving warranty: " + e.getMessage());
            return null;
        }
    }

    public List<Warranty> getAllWarranties() {
        try {
            return warrantyRepository.findAll();
        } catch (Exception e) {
            System.err.println("Error fetching warranties: " + e.getMessage());
            return null;
        }
    }

    public Optional<Warranty> getWarrantyById(Long id) {
        try {
            return warrantyRepository.findById(id);
        } catch (Exception e) {
            System.err.println("Error fetching warranty by ID: " + e.getMessage());
            return Optional.empty();
        }
    }

    public void deleteWarranty(Long id) {
        try {
            warrantyRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error deleting warranty: " + e.getMessage());
        }
    }

    public void checkAndUpdateWarrantyStatus(Warranty warranty) {
        try {
            LocalDate now = LocalDate.now();
            LocalDate purchaseDate = warranty.getPurchaseDate();
            if (purchaseDate.plusYears(2).isBefore(now)) {
                warranty.setWarrantyStatus("Expired");
            } else {
                warranty.setWarrantyStatus("Valid");
            }
            warrantyRepository.save(warranty);
        } catch (Exception e) {
            System.err.println("Error checking and updating warranty status: " + e.getMessage());
        }
    }
}
