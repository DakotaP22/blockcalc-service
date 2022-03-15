package com.dpease.blockcalc.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import com.dpease.blockcalc.domain.Item;

public class GraphProcessor {

    Queue<Item> unprocessed = new LinkedList<>();
    Set<Item> resolved = new HashSet<>();
    Map<Item, Integer> need = new HashMap<>();

    public Map<String, Integer> process(Iterable<Item> items, Map<String, Integer> output) {
        this.init(items, output);

        while(unprocessed.size() > 0) {

            Item i = unprocessed.remove();
            if(!this.isProcessable(i, resolved)) {
                this.unprocessed.add(i);
                continue;
            }

            if(!i.isLeaf()) {
                i.ingredients.forEach(r -> {
                    Item parent = r.start;
                    Item child = r.end;
                    int x = (int) Math.ceil((double)need.get(parent)/(double)r.output);
                    need.compute(child, (k, v) -> v + (x*r.input));
                    if(!unprocessed.contains(child)) unprocessed.add(child);
                });
            }
            resolved.add(i);

        }
        return this.need
                .entrySet()
                .stream()
                .filter(e -> e.getKey().isLeaf())
                .collect(Collectors.toMap(e -> e.getKey().name, e -> e.getValue()));
    }

    private void init(Iterable<Item> items, Map<String, Integer> output) {
        items.forEach(item -> {

            if(output.containsKey(item.name)) {
                unprocessed.add(item);
                need.put(item, output.get(item.name));
            } else {
                need.put(item, 0);
            }


        });
    }

    private boolean isProcessable(Item i, Set<Item> resolved) {
        if(i == null) return false;
        else if (i.ingredientOf == null) return true;
        else 
            return i.ingredientOf.stream().filter(ing -> this.isUnresolved(ing.start, resolved)).count() == 0;
        
    }

    private boolean isUnresolved(Item i, Set<Item> resolved) {
        return !resolved.contains(i);
    }
}
