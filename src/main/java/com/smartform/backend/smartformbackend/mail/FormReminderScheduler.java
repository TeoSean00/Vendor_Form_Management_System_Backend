package com.smartform.backend.smartformbackend.mail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.smartform.backend.smartformbackend.form.VendorForm;
import com.smartform.backend.smartformbackend.form.VendorFormDAO;
import com.smartform.backend.smartformbackend.vendor.Vendor;

public class FormReminderScheduler {
    
    @Autowired
    private VendorFormDAO vendorFormDAO;

    @Autowired
    private EmailService emailService;


    // @Scheduled(cron = "0 0 0 * * *") // run every day at midnight
    // public void sendReminderEmailsForForms() {
    //     List<VendorForm> forms = vendorFormDAO.findAll();
    //     LocalDate currentDate = LocalDate.now();
    //     for (VendorForm form : forms) {
    //         LocalDate deadlineDate = form.getDeadline().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    //         if (deadlineDate.isBefore(currentDate)) {
    //             int daysOverdue = (int) ChronoUnit.DAYS.between(deadlineDate, currentDate);
    //             if (daysOverdue <= 7 && daysOverdue % 3 == 0) {
    //                 emailService.sendEmail(form.getEmail(), "Reminder: Form is overdue", "Please submit your form as soon as possible.");
    //             } else if (daysOverdue > 7) {
    //                 emailService.sendEmail(form.getEmail(), "Urgent Reminder: Form is overdue", "Your form is now " + daysOverdue + " days overdue. Please submit it immediately.");
    //             }
    //         }
    //     }
    // }
}
