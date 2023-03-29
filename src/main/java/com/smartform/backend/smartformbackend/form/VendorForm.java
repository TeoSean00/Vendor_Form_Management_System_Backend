package com.smartform.backend.smartformbackend.form;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;

@Document(collection = "forms")
public class VendorForm {
    @Id
    private String id;
    private String status;
    private String vendorName;
    private Date creationDate;
    private String vendorId;
    private String formNo;
    private int revNumber;
    private Date latestRejectionDate;
    private String latestRejector;
    private int rejectionCount;
    private Map<String, String> rejectionDetails;
    private Date latestCompletedDate;
    private String latestCompletor;
    private Map<String, Object> content;
    private Date deadline;
    private LocalDateTime reminderSentDate;

    @JsonCreator
    public VendorForm(String vendorName, Date creationDate, Map<String, Object> content, Date deadline,
            String vendorId) {
        this.vendorName = vendorName;
        this.creationDate = creationDate;
        this.vendorId = vendorId;
        this.content = content;
        this.deadline = deadline;
        this.latestRejectionDate = null;
        this.latestRejector = "";
        this.rejectionDetails = new HashMap<String, String>();
        this.rejectionDetails.put("admin", null);
        this.rejectionDetails.put("vendor", null);
        this.latestCompletedDate = null;
        this.latestCompletor = "";
    }

    // Check if deadline has exceeded
    public boolean hasExceededDeadline() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(deadline.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    public LocalDateTime getReminderSentDate() {
        return reminderSentDate;
    }

    public void setReminderSentDate(LocalDateTime reminderSentDate) {
        this.reminderSentDate = reminderSentDate;
    }

    public Date getLatestCompletedDate() {
        return latestCompletedDate;
    }

    public void setLatestCompletedDate(Date latestCompletedDate) {
        this.latestCompletedDate = latestCompletedDate;
    }

    public String getLatestCompletor() {
        return latestCompletor;
    }

    public void setLatestCompletor(String latestCompletor) {
        this.latestCompletor = latestCompletor;
    }

    public Date getLatestRejectionDate() {
        return latestRejectionDate;
    }

    public void setLatestRejectionDate(Date latestRejectionDate) {
        this.latestRejectionDate = latestRejectionDate;
    }

    public String getLatestRejector() {
        return latestRejector;
    }

    public void setLatestRejector(String latestRejector) {
        this.latestRejector = latestRejector;
    }

    public int getRejectionCount() {
        return rejectionCount;
    }

    public void setRejectionCount(int rejectionCount) {
        this.rejectionCount = rejectionCount;
    }

    public Map<String, String> getRejectionDetails() {
        return rejectionDetails;
    }

    public void setRejectionDetails(Map<String, String> rejectionDetails) {
        this.rejectionDetails = rejectionDetails;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public int getRevNumber() {
        return revNumber;
    }

    public void setRevNumber(int revNumber) {
        this.revNumber = revNumber;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendorName() {
        return this.vendorName;
    }

    public void getVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Date getCreateDate() {
        return this.creationDate;
    }

    public void setCreateDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(String id) {
        this.vendorId = id;
    }

}
