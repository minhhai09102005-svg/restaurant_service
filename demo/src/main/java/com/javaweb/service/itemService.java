package com.javaweb.service;

import com.javaweb.enums.itemCategory;
import com.javaweb.model.response.itemResponse;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface itemService {
    List<itemResponse> findAll();
    List<itemResponse> findByFilters(Map<String, Object> filters);
}
