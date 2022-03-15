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

		String query = "MATCH p = (i1:Item)-[:Crafted_With*]->(i2:Item) WHERE i1.name = 'Wooden Pickaxe' OR i1.name = 'Stone Pickaxe' WITH *, relationships(p) as r return i1, r, i2";

		Iterable<Item> items = 
			Neo4jSessionFactory
				.getInstance()
				.getNeo4jSession()
				.query(
					Item.class, 
					query,
					Collections.EMPTY_MAP
				);
		
		Map<String, Integer> output = new HashMap<>();
		output.put("Stone Pickaxe", 3);
		output.put("Wooden Pickaxe", 3);

		Map<String, Integer> res = new GraphProcessor().process(items, output);
		res.entrySet().stream().forEach((Entry<String, Integer> e) -> System.out.printf("%s: %d\n", e.getKey(), e.getValue()));
	}

}
