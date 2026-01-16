package com.javaweb.repository.impl;

import com.javaweb.repository.itemRepository;
import com.javaweb.repository.entity.itemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<itemEntity> findByFilters(Map<String, Object> filters) { // tìm kiếm đa tiêu chí
        StringBuilder sql=new StringBuilder("select * from menu_items where 1=1 ");
        Map<String, Object> params = new HashMap<>();
        StringBuilder conditions =new StringBuilder("");
        String name =  asString(filters.get("name"));//tìm kiếm theo tên

        if(!name.isEmpty()){
            params.put("name","%"+name.trim().toLowerCase()+"%");
            conditions.append(" and name like :name");
        }

        String category =  asString(filters.get("category"));//theo thể loại
        if(!category.isEmpty()){
            params.put("category",category.trim().toLowerCase());
            conditions.append(" and category = :category");
        }

        BigDecimal left = toBigDecimal(filters.get("leftPrice"));//giá tiền cận min
        if(left!=null){
            params.put("leftPrice",left);
            conditions.append(" and price >= :leftPrice");
        }

        BigDecimal right = toBigDecimal(filters.get("rightPrice"));// giá tiền max
        if(right!=null){
            params.put("rightPrice",right);
                conditions.append(" and price <= :rightPrice");
        }

        String availability = asString(filters.get("isAvailable"));
        if(!availability.isEmpty()){
            params.put("isAvailable",availability);
            conditions.append(" and availability = :isAvailable");
        }

        sql.append(conditions);
        sql.append(" order by id desc ");
        return namedJdbc.query(sql.toString(),params,itemMapper);
    }

    private String asString(Object o) {
        if (o==null) return "";
        return o.toString();
    }

    private BigDecimal toBigDecimal(Object o) {
        if (o == null) return null;
        String s = o.toString().trim();
        if (s.isEmpty()) return null;

        try {
            return new BigDecimal(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
