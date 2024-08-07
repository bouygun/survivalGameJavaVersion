package com.berce;

import com.berce.model.Enemy;
import com.berce.model.Hero;
import com.berce.service.Simulation;
import com.berce.model.Resource;
import com.berce.utils.InputParser;
import com.berce.utils.InputValidator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Pls enter the text input: ");

            StringBuilder inputBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) break;
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

            simulation.start();
        } catch(Exception e) {
            System.out.println("An error occurred during the simulation. Please check your input and try again.");
        }
    }
}
