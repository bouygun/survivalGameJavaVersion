package com.berce.utils.handler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterHandler {

    public void writeToFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("Error while writing file");
            throw e;
        }
    }

    public void appendToFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.flush();
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("Error while appending to file");
            throw e;
        }
    }
}
