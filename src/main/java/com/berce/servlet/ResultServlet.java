package com.berce.servlet;

import com.berce.model.Enemy;
import com.berce.model.Hero;
import com.berce.model.Resource;
import com.berce.service.Simulation;
import com.berce.utils.handler.FileWriterHandler;
import com.berce.utils.parser.InputParser;
import com.berce.utils.validator.InputValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Properties;

@WebServlet("/simulation")
public class ResultServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read choice from request
        String choiceParam = request.getParameter("choice");
        int choice = Integer.parseInt(choiceParam);

        // Determine file paths based on choice
        Properties properties = new Properties();
        try (InputStream input = getServletContext().getResourceAsStream("/WEB-INF/config.properties")) {
            if (input == null) {
                throw new ServletException("Sorry, unable to find config.properties");
            }
            properties.load(input);
        }

        String inputFilePath;
        String outputFilePath;

         inputFilePath = properties.getProperty(choice == 1 ? "input.hero.win" : "input.hero.lose");
         outputFilePath = properties.getProperty(choice == 1 ? "output.hero.win" : "output.hero.lose");


        // Read file content using getResourceAsStream
        StringBuilder fileContent = new StringBuilder();

        try (InputStream fileInput = getClass().getClassLoader().getResourceAsStream(inputFilePath)) {
            if (fileInput == null) {
                throw new FileNotFoundException("Dosya bulunamadı: " + inputFilePath);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileInput))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
            }catch (FileNotFoundException e) {
                System.out.println("Dosya bulunamadı. ");

            } catch (IOException e) {
                System.out.println("Dosya içeriği okunamıyor. ");

            }
        }

        String input = fileContent.toString();

        Resource resource = InputParser.parseResource(input);
        Hero hero = InputParser.parseHero(input);
        List<Enemy> enemies = InputParser.parseEnemies(input);

        if (!InputValidator.validateParser(resource, hero, enemies)) {
            request.setAttribute("result", "Invalid input format.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/result.jsp");
            dispatcher.forward(request, response);
            return;
        }

        FileWriterHandler writerHandler = new FileWriterHandler();
        Simulation simulation = new Simulation(hero, enemies, resource, writerHandler, outputFilePath);
        simulation.start();

        String result = "Simulation completed. Check the " + outputFilePath + " file.";

        request.setAttribute("result", result);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/result.jsp");
        dispatcher.forward(request, response);

    }
}
