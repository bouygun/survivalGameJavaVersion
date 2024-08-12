package com.berce.service;

import com.berce.model.Enemy;
import com.berce.model.Hero;
import com.berce.model.Resource;
import com.berce.utils.handler.FileWriterHandler;

import java.io.IOException;
import java.util.List;

public class Simulation {
    private final Hero hero;
    private final List<Enemy> enemies;
    private final Resource resource;
    private  final String outputFilePath;
    private final FileWriterHandler writerHandler;

    public Simulation(Hero hero, List<Enemy> enemies, Resource resource, FileWriterHandler writerHandler, String outputFilePath) {
        this.hero = hero;
        this.enemies = enemies;
        this.resource = resource;
        this.writerHandler = writerHandler;
        this.outputFilePath = outputFilePath;

    }

    public void start() throws IOException {
        writerHandler.writeToFile(outputFilePath, "Hero started journey with " + hero.getHp() + " HP!\n");

        int heroPosition = 0;

        for (Enemy enemy : enemies) {
            // hero moves to enemy position
            heroPosition = enemy.getPosition();

            // fight
            while (hero.getHp() > 0 && enemy.getHp() > 0) {
                hero.attack(enemy);
                if (enemy.getHp() > 0) {
                    enemy.attack(hero);
                }
            }

            // enemy dead
            if (enemy.getHp() <= 0) {
                writerHandler.appendToFile(outputFilePath,"Hero defeated " + enemy.getName() + " with " + hero.getHp() + " HP remaining\n");
            }

            // hero dead
            if (hero.getHp() <= 0) {
                writerHandler.appendToFile(outputFilePath,"Hero is Dead!! Last seen at position " + heroPosition + "!!\n");
                return;
            }
        }

        // hero reach resource & survived
        if (hero.getHp() > 0) {
            writerHandler.appendToFile(outputFilePath, "Hero Survived!\n");
        } else {
            writerHandler.appendToFile(outputFilePath, "Hero is Dead!! Last seen at position " + heroPosition + "!!\n");
        }

    }
}
