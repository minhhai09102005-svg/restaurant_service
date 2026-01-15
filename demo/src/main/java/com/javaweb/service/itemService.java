package com.javaweb.service;

import com.javaweb.enums.itemCategory;
import com.javaweb.model.response.itemResponse;


import java.math.BigDecimal;
import java.util.List;

public interface itemService {
    List<itemResponse> findAll();
    List<itemResponse> findByCategory(itemCategory category);
    List<itemResponse> findByName(String name);
    List<itemResponse> findByCost(BigDecimal left,BigDecimal right);

}
