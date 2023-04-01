package com.smartform.backend.smartformbackend.vendor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.smartform.backend.smartformbackend.form.VendorForm;

@Repository
public class VendorDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    // get all topics
    public List<Vendor> findAll() {
        return mongoTemplate.findAll(Vendor.class);
    }

    public void saveAll(final List<Vendor> vendors) {
        mongoTemplate.insertAll(vendors);
    }

    // update function
    public void updateVendor(String id, Vendor vendor) {
        mongoTemplate.save(vendor);

    }

    // insert topic
    public void insertVendor(Vendor vendor) {
        mongoTemplate.insert(vendor);
    }

    // delete topic
    public void deleteVendor(String id) {
        Vendor deleteObject = getVendor(id);
        mongoTemplate.remove(deleteObject);
    }

    public Vendor getVendor(final String topicId) {
        return mongoTemplate.findById(topicId, Vendor.class);
    }

    public Map<String, Double> getAvgRejections() {
        List<VendorForm> forms = mongoTemplate.findAll(VendorForm.class);
        Map<String, Double> formsPerVendor = new HashMap<String, Double>();
        Map<String, Double> totalRejPerVendor = new HashMap<String, Double>();

        for (VendorForm form : forms) {
            var vendorName = form.getVendorName();
            var totalRej = form.getRejectionCount();

            totalRejPerVendor.put(vendorName, totalRejPerVendor.getOrDefault(vendorName, 0.0) + totalRej);
            formsPerVendor.put(vendorName, formsPerVendor.getOrDefault(vendorName, 0.0) + 1);
        }

        for (String key : totalRejPerVendor.keySet()) {
            totalRejPerVendor.put(key, Math.ceil(totalRejPerVendor.get(key) / formsPerVendor.get(key)));
        }
        return totalRejPerVendor;
    }

    public Map<Integer, JoinDateDTO> getJoinDates() {
        List<Vendor> vendors = mongoTemplate.findAll(Vendor.class);
        List<String> months = Arrays.asList("January", "Febuary", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
        Map<Integer, Map<String, Integer>> joinDates = new HashMap<>();
        for (Vendor vendor : vendors) {
            // Date joinDate = vendor.getJoinDate();
            // JoinDateDTO joinDateDTO = new JoinDateDTO();
            // Calendar calendar = Calendar.getInstance();
            // calendar.get(Calendar.YEAR);

            // int mthIdx = calendar.get(Calendar.MONTH);
            // String monthName = months.get(mthIdx);
            // int monthDay = calendar.get(Calendar.DAY_OF_MONTH);
            // joinDateDTO.getMonths().put(monthName, monthDay);
            // joinDates.add(joinDateDTO);
            Date joinDate = vendor.getJoinDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(joinDate);
            int year = calendar.get(Calendar.YEAR);
            int monthDay = calendar.get(Calendar.MONTH);
            String monthName = months.get(monthDay);

            Map<String, Integer> yearMonthMap = joinDates.getOrDefault(year, new HashMap<>());
            yearMonthMap.merge(monthName, 1, Integer::sum);
            joinDates.put(year, yearMonthMap);

        }
        Map<Integer, JoinDateDTO> result = new HashMap<>();
        for (Map.Entry<Integer, Map<String, Integer>> entry : joinDates.entrySet()) {
            JoinDateDTO joinDateDTO = new JoinDateDTO();
            Map<String, Integer> currYearMonthMap = entry.getValue();
            for (Map.Entry<String, Integer> monthEntry : currYearMonthMap.entrySet()) {
                String Name = monthEntry.getKey();
                int count = monthEntry.getValue();
                joinDateDTO.getMonths().put(Name, count);
            }
            result.put(entry.getKey(), joinDateDTO);
        }
        return result;

    }
}
