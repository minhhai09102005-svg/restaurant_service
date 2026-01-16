package com.javaweb.api.staff;

import com.javaweb.customExceptions.DataNotFoundException;
import com.javaweb.model.response.itemResponse;
import com.javaweb.repository.itemRepository;
import com.javaweb.service.itemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class menuManager{
    private final itemService itemService;

    @GetMapping(value ="/public/item/all/")
    public List<itemResponse> findAll(){
        return itemService.findAll();
    }

    @GetMapping(value="/public/item/conditions/")
    public List<itemResponse> findByFilters(@RequestParam Map<String,Object> filters){
        return itemService.findByFilters(filters);
    }


}
