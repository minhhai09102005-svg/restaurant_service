package com.javaweb.api.staff;

import com.javaweb.customExceptions.DataNotFoundException;
import com.javaweb.model.response.itemResponse;
import com.javaweb.repository.itemRepository;
import com.javaweb.service.itemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class menuManager{
    private final itemService itemService;

    @GetMapping(value ="/public/item/findAll/")
    public List<itemResponse> findAll(){
        return itemService.findAll();
    }
    @GetMapping(value ="/public/item/findByName")
    public List<itemResponse> findByName(String name) throws DataNotFoundException {
        return itemService.findByName(name);
    }

}
