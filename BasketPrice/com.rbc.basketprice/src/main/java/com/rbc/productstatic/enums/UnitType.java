package com.rbc.productstatic.enums;

public enum UnitType {

    BY_WEIGHT("By Weight"),
    BY_UNIT("By Unit");

    private final String name;

    UnitType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
