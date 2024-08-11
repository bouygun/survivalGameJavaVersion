package com.berce.service;

import com.berce.model.Enemy;
import com.berce.model.Hero;
import com.berce.model.Resource;

import java.io.BufferedWriter;
import java.io.IOException;
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

    public void start(BufferedWriter writer) throws IOException {
        writer.write("Hero started journey with " + hero.getHp() + " HP!\n");

        int heroPosition = 0;
        int resourceDistance = resource.getDistanceFromBase();
        if (resourceDistance < 0) {
            writer.write("Validation error: Invalid resource distance.\n");
            return;
        }
        if (hero.getHp() <= 0) {
            writer.write("Hero is Dead!! Last seen at position " + heroPosition + "!!\n");
            return;
        }

        for (Enemy enemy : enemies) {
            if (enemy.getPosition() > resourceDistance) break;
            // hero moves based enemy's position
            while (heroPosition < enemy.getPosition() && hero.getHp() > 0) {
                heroPosition++;
            }

            // fight
            if (heroPosition == enemy.getPosition() && hero.getHp() > 0) {
                while (hero.getHp() > 0 && enemy.getHp() > 0) {
                    hero.attack(enemy);
                    if (enemy.getHp() > 0) {
                        enemy.attack(hero);
                    }
                }

                //if enemy lose
                if (enemy.getHp() <= 0) {
                    writer.write("Hero defeated " + enemy.getName() + " with " + hero.getHp() + " HP remaining\n");
                }

                // if hero dead
                if (hero.getHp() <= 0) {
                    writer.write("Hero is Dead!! Last seen at position " + heroPosition + "!!\n");
                    return;
                }
            }
        }

        // check resoruce distance
        if (hero.getHp() > 0 && heroPosition > resourceDistance) {
            writer.write("Hero Survived!\n");
        } else {
            writer.write("Hero is Dead!! Last seen at position " + heroPosition + "!!\n");
        }

        writer.flush();
    }
}
