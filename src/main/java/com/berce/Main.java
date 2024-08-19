package com.berce;

import com.berce.model.*;
import com.berce.utils.handler.FileReaderHandler;
import com.berce.utils.handler.FileWriterHandler;
import com.berce.utils.parser.InputParser;
import com.berce.service.Simulation;
import com.berce.utils.validator.*;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        Properties properties = new Properties();
        String outputFilePath = " ";
        String inputFilePath = " ";
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.config.properties");
                return;
            }
            properties.load(input);

            Choice choice = InputValidator.validateUserChoice();

             inputFilePath = properties.getProperty(choice == Choice.HERO_SURVIVED ? "input.hero.win" : "input.hero.lose");
             outputFilePath = properties.getProperty(choice == Choice.HERO_SURVIVED ? "output.hero.win" : "output.hero.lose");

        } catch (IOException e) {
            System.out.println("An error occurred during the simulation. Please check your input or this code and try again.");
        }


        try {
            FileReaderHandler readerHandler = new FileReaderHandler();
            String input = readerHandler.bufferedToString(inputFilePath);

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
