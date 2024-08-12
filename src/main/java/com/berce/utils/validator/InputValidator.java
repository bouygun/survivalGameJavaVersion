package com.berce.utils.validator;

import com.berce.model.Choice;
import com.berce.model.Enemy;
import com.berce.model.Hero;
import com.berce.model.Resource;

import java.util.List;
import java.util.Scanner;


public class InputValidator {
    public static boolean validateParser(Resource resource, Hero hero, List<Enemy> enemies) {
        return resource != null && hero != null && !enemies.isEmpty();
    }

    public static Choice validateUserChoice() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Which one is?");
                System.out.println("1. Hero survived!");
                System.out.println("2. Hero is dead!");
                int userInput = scanner.nextInt();

                return Choice.fromInt(userInput);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // delete because of unnecessary

            }
        }
    }
}
