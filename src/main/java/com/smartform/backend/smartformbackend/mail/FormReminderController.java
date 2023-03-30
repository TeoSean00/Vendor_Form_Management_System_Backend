package com.smartform.backend.smartformbackend.mail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/form-reminder")
public class FormReminderController {
    @Autowired
    private FormReminderScheduler formReminderScheduler;

    @PostMapping("/exclude-vendor")
    public ResponseEntity<String> excludeVendor(@RequestParam String vendorId) {
        formReminderScheduler.excludeVendorIds.add(vendorId);
        return ResponseEntity.ok("Vendor " + vendorId + " excluded from form reminder emails.");
    }

    @GetMapping("/all")
    public List<String> getAllExcludedVendors() {
        return formReminderScheduler.excludeVendorIds;
    }
}