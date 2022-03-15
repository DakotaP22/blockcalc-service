package com.dpease.blockcalc.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "Crafted_With")
public class CraftedWith extends Entity {

    @StartNode
    public Item start;

    @Property
    public int output;

    @EndNode
    public Item end;

    @Property
    public int input;

    public CraftedWith() {
    }
}
