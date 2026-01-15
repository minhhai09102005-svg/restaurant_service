package com.javaweb.model.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class itemRequest {
    private Integer id ;
    private String name ;
    private BigDecimal leftPrice ;
    private BigDecimal rightPrice ;
    private String img ;
    private String description;
    private String category;
    private String is_available ;

}
