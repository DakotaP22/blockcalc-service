package com.dpease.blockcalc.domain;

public class ItemReference {
    private Long id;
    private String name;

    public ItemReference(Item i) {
        this.id = i.getId();
        this.name = i.name;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
