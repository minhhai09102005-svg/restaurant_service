package com.javaweb.repository;

import com.javaweb.enums.itemCategory;
import com.javaweb.repository.entity.itemEntity;

import java.math.BigDecimal;
import java.util.List;

public interface itemRepository {

    List<itemEntity> findAll();

}
