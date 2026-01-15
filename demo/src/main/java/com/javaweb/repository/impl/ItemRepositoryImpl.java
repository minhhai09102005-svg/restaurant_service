package com.javaweb.repository.impl;

import com.javaweb.enums.itemCategory;
import com.javaweb.repository.itemRepository;
import com.javaweb.repository.entity.itemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements itemRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbc;

    private final RowMapper<itemEntity> itemMapper =(result,index)->{
        itemEntity item = new itemEntity();
        item.setId(result.getInt("id"));
        item.setName(result.getString("name"));
        item.setPrice(result.getBigDecimal("price"));
        item.setImg(result.getString("img"));
        item.setDescription(result.getString("description"));
        item.setCategory(result.getString("category"));
        item.setIsAvailable(result.getString("is_available"));
        return item;
    };

    @Override
    public List<itemEntity> findAll() {
        String sql="select * from menu_items";
        return jdbcTemplate.query(sql,itemMapper);
    }




}
