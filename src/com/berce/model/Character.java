package com.berce.model;

// Subclasses (Hero and Enemy) implement these methods in their own way.
// Because common behavior is shown. This is why it is abstract
public abstract class Character {
    private int hp;
    private final int attack;

    public Character(int hp, int attack) {
        this.hp = hp;
        this.attack = attack;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public abstract void attack(Character target);
}