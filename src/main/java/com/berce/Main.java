package com.berce;

import com.berce.model.Enemy;
import com.berce.model.Hero;
import com.berce.model.Resource;
import com.berce.utils.handler.FileReaderHandler;
import com.berce.utils.handler.FileWriterHandler;
import com.berce.utils.parser.InputParser;
import com.berce.service.Simulation;
import com.berce.utils.validator.InputValidator;

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
        String input = " ";

        // validator
        if (choice == 1) {
            inputFilePath = "src/main/resources/input_hero_win.txt";
            outputFilePath = "src/main/resources/output_hero_win.txt";
        } else {
            inputFilePath = "src/main/resources/input_hero_lose.txt";
            outputFilePath = "src/main/resources/output_hero_lose.txt";
        }

        try {

            FileReaderHandler readerHandler = new FileReaderHandler();
            input = readerHandler.bufferedToString(inputFilePath);

            Resource resource = InputParser.parseResource(input);
            Hero hero = InputParser.parseHero(input);
            List<Enemy> enemies = InputParser.parseEnemies(input);

            if (!InputValidator.validateParser(resource, hero, enemies)) {
                System.out.println("Invalid input format.");
                return;
            }

            FileWriterHandler writerHandler = new FileWriterHandler();
            Simulation simulation = new Simulation(hero, enemies, resource, writerHandler, outputFilePath);
            simulation.start();

            System.out.println("Simulation is done. You can check the " + outputFilePath + " file.");

        } catch (Exception e) {
            System.out.println("An error occurred during the simulation. Please check your input or this code and try again.");
        }
    }
}
