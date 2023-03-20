package com.smartform.backend.smartformbackend.vendor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/vendor")
public class VendorController {
    @Autowired
    private VendorDAO vendorDAO;

    // anything you return is automatically coverted to JS
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Vendor> getAllVendors() {
        return vendorDAO.findAll();
    }

    // need to tell spring to send the id to the method
    @RequestMapping("/{id}")
    public Vendor getVendor(@PathVariable String id) {
        return vendorDAO.getVendor(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    @PreAuthorize("hasRole('ADMIN')")
    // getting the the request payload
    public void addVendor(@RequestBody Vendor vendor) {
        vendor.setJoinDate(new Date());
        vendorDAO.insertVendor(vendor);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateVendor(@RequestBody Vendor vendor, @PathVariable String id) {
        vendorDAO.updateVendor(id, vendor);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteVendor(@PathVariable String id) {
        vendorDAO.deleteVendor(id);
    }

    @GetMapping("/getJoinDates")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<Integer, JoinDateDTO> getJoinDates() {
        return vendorDAO.getJoinDates();
    }

}
