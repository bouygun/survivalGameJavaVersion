package com.berce;

import com.berce.model.Enemy;
import com.berce.model.Hero;
import com.berce.model.Resource;
import com.berce.parser.InputParser;
import com.berce.service.Simulation;
import com.berce.validator.InputValidator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);

        System.out.println("Which one is?");
        System.out.println("1. Hero survived!");
        System.out.println("2. Hero is dead!");
        int choice = scanner.nextInt();

        String inputFilePath;
        String outputFilePath;
        if (choice == 1) {
            inputFilePath = "resources/input_hero_win.txt";
            outputFilePath = "resources/output_hero_win.txt";
        } else {
            inputFilePath = "resource/input_hero_lose.txt";
            outputFilePath = "resource/output_hero_lose.txt";
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            StringBuilder inputBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                inputBuilder.append(line).append("\n");
            }
            String input = inputBuilder.toString();

            Resource resource = InputParser.parseResource(input);
            Hero hero = InputParser.parseHero(input);
            List<Enemy> enemies = InputParser.parseEnemies(input);

            if (!InputValidator.validateParser(resource, hero, enemies)) {
                System.out.println("Invalid input format.");
                return;
            }

            Simulation simulation = new Simulation(hero, enemies, resource);

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            simulation.start(writer);
            writer.close();
            System.out.println("Simulation is done. Check the " + outputFilePath + " file.");

        } catch (Exception e) {
            System.out.println("An error occurred during the simulation. Please check your input and try again.");
        }
    }
}
