package com.iotstock.Warranty_tracking.controller;


import com.iotstock.Warranty_tracking.model.Warranty;
import com.iotstock.Warranty_tracking.service.WarrantyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/warranties")
public class WarrantyController {

    @Autowired
    private WarrantyService warrantyService;

    @PostMapping
    public ResponseEntity<Warranty> createWarranty(@RequestBody Warranty warranty) {
        try {
            warrantyService.checkAndUpdateWarrantyStatus(warranty);
            Warranty savedWarranty = warrantyService.saveWarranty(warranty);
            return ResponseEntity.ok(savedWarranty);
        } catch (Exception e) {
            System.err.println("Error creating warranty: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Warranty>> getAllWarranties() {
        try {
            List<Warranty> warranties = warrantyService.getAllWarranties();
            return ResponseEntity.ok(warranties);
        } catch (Exception e) {
            System.err.println("Error fetching warranties: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warranty> getWarrantyById(@PathVariable Long id) {
        try {
            Optional<Warranty> warranty = warrantyService.getWarrantyById(id);
            return warranty.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(404).body(null));
        } catch (Exception e) {
            System.err.println("Error fetching warranty by ID: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Warranty> updateWarranty(@PathVariable Long id, @RequestBody Warranty warranty) {
        try {
            Optional<Warranty> existingWarranty = warrantyService.getWarrantyById(id);
            if (existingWarranty.isPresent()) {
                warranty.setId(id);
                warrantyService.checkAndUpdateWarrantyStatus(warranty);
                Warranty updatedWarranty = warrantyService.saveWarranty(warranty);
                return ResponseEntity.ok(updatedWarranty);
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } catch (Exception e) {
            System.err.println("Error updating warranty: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarranty(@PathVariable Long id) {
        try {
            warrantyService.deleteWarranty(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.err.println("Error deleting warranty: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}
