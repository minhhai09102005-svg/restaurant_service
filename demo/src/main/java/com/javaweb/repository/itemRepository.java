package com.javaweb.repository;

import com.javaweb.enums.itemCategory;
import com.javaweb.repository.entity.itemEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface itemRepository {

    List<itemEntity> findAll();
    List<itemEntity> findByFilters(Map<String, Object> filters);
}
