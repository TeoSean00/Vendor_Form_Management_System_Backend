package com.smartform.backend.smartformbackend.vendor;

import java.util.HashMap;
import java.util.Map;

public class JoinDateDTO {
    public Map<String, Integer> getMonths() {
        return months;
    }

    public void setMonths(Map<String, Integer> months) {
        this.months = months;
    }

    private Map<String, Integer> months = new HashMap<>();
}
