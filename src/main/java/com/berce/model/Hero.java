package com.berce.model;

public class Hero extends Character {
    public Hero(int hp, int attack) {
        super(hp, attack);
    }

    // If there were a different usage, enemy - hero classes would be an example of polymorphism.
    @Override
    public void attack(Character target) {
        target.setHp(target.getHp() - this.getAttack());
        this.setHp(this.getHp() - target.getAttack());
    }
}