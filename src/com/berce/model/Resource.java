package com.berce.model;

public class Resource {
    private final int distance;

    public Resource(int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        this.distance = distance;
    }
// add after
   public int getDistanceFromBase() {
        return distance;
    }
}
