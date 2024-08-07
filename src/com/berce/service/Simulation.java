package com.berce.service;

import com.berce.model.Enemy;
import com.berce.model.Hero;
import com.berce.model.Resource;

import java.util.List;

public class Simulation {
    private final Hero hero;
    private final List<Enemy> enemies;
    private final Resource resource;

    public Simulation(Hero hero, List<Enemy> enemies, Resource resource) {
        this.hero = hero;
        this.enemies = enemies;
        this.resource = resource;
    }

    public void start() {
        System.out.println("Hero started journey with " + hero.getHp() + " HP!");

        int heroPosition = 0;
        int resourceDistance = resource.getDistanceFromBase();
        if (resourceDistance < 0) {
            System.out.println("validation err");
            return;
        }
        if (hero.getHp() <= 0) {
            System.out.println("Hero is Dead!! Last seen at position " + heroPosition + "!!");
            return;
        }

        for (Enemy enemy : enemies) {
            if (enemy.getPosition() > resourceDistance) break;
            while (heroPosition < enemy.getPosition() && hero.getHp() > 0) {
                heroPosition++;
            }

            while (hero.getHp() > 0 && enemy.getHp() > 0) {
                hero.attack(enemy);

                enemy.attack(hero);

            }

            if (enemy.getHp() <= 0) {
                System.out.println("Hero defeated " + enemy.getName() + " with " + hero.getHp() + " HP remaining");
            }


            if (hero.getHp() > 0) {
                while (heroPosition < resource.getDistanceFromBase() && hero.getHp() > 0) {
                    heroPosition++;
                }

                if (heroPosition > resource.getDistanceFromBase()) {
                    System.out.println("Hero Survived");
                }
/*

            while (heroPosition < resource.getDistanceFromBase() && hero.getHp() > 0) {
                heroPosition++;
            }

            if (heroPosition > resource.getDistanceFromBase()) {
                System.out.println("Hero Survived");
            } else {
                System.out.println("Hero Survived but did not reach the resource. Distance remaining: " + (resource.getDistanceFromBase() - heroPosition));
            }
 */
            } else {
                System.out.println("Hero is Dead!! Last seen at position " + heroPosition + "!!");
                return;
            }
        }
    }
}
