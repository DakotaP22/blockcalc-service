package com.dpease.blockcalc.controller;

import java.util.Collections;
import java.util.Map;

import com.dpease.blockcalc.service.BlockCalcService;

import org.springframework.beans.factory.annotation.Autowired;
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

}
