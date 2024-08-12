package com.berce.model;

public enum Choice {
    HERO_SURVIVED(1),
    HERO_DEAD(2);

    private final int value;

    Choice(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Choice fromInt(int value) {
        for (Choice choice : Choice.values()) {
            if (choice.getValue() == value) {
                return choice;
            }
        }
        throw new IllegalArgumentException("Invalid choice: " + value + ". Please select 1 or 2.");
    }
}
