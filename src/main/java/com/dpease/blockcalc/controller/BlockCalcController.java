package com.dpease.blockcalc.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.dpease.blockcalc.domain.ItemReference;
import com.dpease.blockcalc.service.BlockCalcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlockCalcController {
    
    @Autowired
    BlockCalcService calcSvc;

    @PostMapping("/calculate")
    public Map<String, Integer> calculate(@RequestBody Map<String, Integer> desiredOutput) {
        return calcSvc.calculate(desiredOutput);
    }

    @GetMapping("/items")
    public List<ItemReference> getAllItems() {
       return StreamSupport.stream(calcSvc.getAllItems().spliterator(), true).map(ItemReference::new).collect(Collectors.toList());
    }

}
