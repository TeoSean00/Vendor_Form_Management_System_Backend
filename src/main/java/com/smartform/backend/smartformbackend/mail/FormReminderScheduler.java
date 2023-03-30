package com.smartform.backend.smartformbackend.mail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.smartform.backend.smartformbackend.auth.User;
import com.smartform.backend.smartformbackend.auth.repository.UserRepository;
import com.smartform.backend.smartformbackend.form.VendorForm;
import com.smartform.backend.smartformbackend.form.VendorFormDAO;
import com.smartform.backend.smartformbackend.vendor.Vendor;
import com.smartform.backend.smartformbackend.vendor.VendorDAO;

@Component
public class FormReminderScheduler {
    public List<String> excludeVendorIds = new ArrayList<>();

    @Autowired
    private VendorFormDAO vendorFormDAO;

    @Autowired
    private VendorDAO vendorDAO;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 8 * * *") // run every day at midnight
    public void sendReminderEmailsForForms() {
        System.out.print("---------------------RUNNING SCHEDULE EMAIL----------------------------------");
        List<Vendor> vendors = vendorDAO.findAll();
        LocalDate currentDate = LocalDate.now();
        for (Vendor vendor : vendors) {
            if (vendor.getReminderStatus()) {
                List<VendorForm> forms = vendorFormDAO.findAllVendorForms(vendor.getId());
                for (VendorForm form : forms) {
                    System.out.print("Checking form");
                    LocalDate deadlineDate = form.getDeadline().toInstant().atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    if (deadlineDate.isBefore(currentDate)) {
                        int daysOverdue = (int) ChronoUnit.DAYS.between(deadlineDate, currentDate);
                        // if (daysOverdue <= 7 && daysOverdue % 3 == 0) {
                        if (daysOverdue <= 7) {
                            System.out.print("FORM IS OVERDUE");
                            List<User> users = userRepository.findUsersByVendor(form.getVendorId());
                            for (User user : users) {
                                emailService.sendSimpleMessage(user.getEmail(), "Reminder: Form is overdue",
                                        "Please submit your form as soon as possible.");
                            }
                        } else if (daysOverdue > 7) {
                            List<User> users = userRepository.findUsersByVendor(form.getVendorId());
                            for (User user : users) {
                                emailService.sendSimpleMessage(user.getEmail(), "Reminder: Form is overdue",
                                        "Please submit your form as soon as possible.");
                            }
                        }
                    }

                }
            }
        }

    }
}
