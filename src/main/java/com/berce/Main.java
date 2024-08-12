package com.berce;

import com.berce.model.*;
import com.berce.utils.handler.FileReaderHandler;
import com.berce.utils.handler.FileWriterHandler;
import com.berce.utils.parser.InputParser;
import com.berce.service.Simulation;
import com.berce.utils.validator.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Choice choice = InputValidator.validateUserChoice();

        FilePath filePath = FilePath.fromChoice(choice);
        String inputFilePath = filePath.getInputFilePath();
        String outputFilePath = filePath.getOutputFilePath();

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
