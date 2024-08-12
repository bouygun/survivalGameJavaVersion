package com.berce.model;

public class Enemy extends Character {
    private final String name;
    private final int position;

    public Enemy(String name, int hp, int attack, int position) {
        super(hp, attack);
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    // If there were a different usage, enemy - hero classes would be an example of polymorphism.
    @Override
    public void attack(Character target) {
        target.setHp(target.getHp() - this.getAttack());
        this.setHp(this.getHp() - target.getAttack());
    }

}
