package com.dpease.blockcalc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.dpease.blockcalc.domain.Item;
import com.dpease.blockcalc.util.GraphProcessor;
import com.dpease.blockcalc.util.Neo4jSessionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlockcalcApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockcalcApplication.class, args);
	}

}
