package com.smartform.backend.smartformbackend.mail;

import org.springframework.beans.factory.annotation.Autowired;

public class FormReminderScheduler {
    
    // @Autowired

    // public FormReminderScheduler(FormService formService, EmailService emailService, List<Long> excludedVendorIds) {
    //     this.formService = formService;
    //     this.emailService = emailService;
    //     this.excludedVendorIds = excludedVendorIds;
    // }

    // @Scheduled(cron = "0 0 0 * * *") // run every day at midnight
    // public void sendReminderEmailsForForms() {
    //     List<Form> forms = formService.getAllForms();
    //     for (Form form : forms) {
    //         if (form.getDeadline().isBefore(LocalDateTime.now()) && !excludedVendorIds.contains(form.getVendor().getId())) {
    //             LocalDate deadlineDate = form.getDeadline().toLocalDate();
    //             LocalDate currentDate = LocalDate.now();
    //             int daysOverdue = (int) ChronoUnit.DAYS.between(deadlineDate, currentDate);
    //             if (daysOverdue <= 7 && daysOverdue % 3 == 0) {
    //                 emailService.sendEmail(form.getVendor().getEmail(), "Reminder: Form is overdue", "Please submit your form as soon as possible.");
    //             } else if (daysOverdue > 7) {
    //                 emailService.sendEmail(form.getVendor().getEmail(), "Urgent Reminder: Form is overdue", "Your form is now " + daysOverdue + " days overdue. Please submit it immediately.");
    //             }
    //         }
    //     }
    // }
}
