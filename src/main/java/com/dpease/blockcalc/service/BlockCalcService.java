package com.dpease.blockcalc.service;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import com.dpease.blockcalc.domain.Item;
import com.dpease.blockcalc.util.GraphProcessor;
import com.dpease.blockcalc.util.Neo4jSessionFactory;

import org.springframework.stereotype.Service;

@Service
public class BlockCalcService {
    

    public Map<String, Integer> calculate(Map<String, Integer> desiredOutput) {
        GraphProcessor processor = new GraphProcessor();

        String query = 
            "MATCH p = (i1:Item)-[:Crafted_With*]->(i2:Item) "+
            "WHERE " + desiredOutput.keySet().stream().map(key -> String.format("i1.name = '%s'", key)).collect(Collectors.joining(" OR ")) + " " + 
            "WITH *, relationships(p) as r " + 
            "return i1, r, i2;";

        Iterable<Item> items = Neo4jSessionFactory
                .getInstance()
                .getNeo4jSession()
                .query(Item.class, query, Collections.EMPTY_MAP);

        return processor.process(items, desiredOutput);
    }

}
