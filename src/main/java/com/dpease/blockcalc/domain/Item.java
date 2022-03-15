package com.dpease.blockcalc.domain;

import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Item extends Entity {
    public String name;

    @Relationship(type = "Crafted_With", direction = Relationship.INCOMING)
    public List<CraftedWith> ingredientOf;

    @Relationship(type = "Crafted_With", direction = Relationship.OUTGOING)
    public List<CraftedWith> ingredients;

    public Item() {
    }

    public boolean isLeaf() {
        return this.ingredients == null || this.ingredients.size() == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    
}
