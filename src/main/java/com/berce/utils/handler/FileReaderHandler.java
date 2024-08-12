package com.berce.utils.handler;
import java.io.*;

public class FileReaderHandler {

    public String bufferedToString(String filePath) {
        try (Reader ireader = new FileReader(filePath);
             BufferedReader bufferReader = new BufferedReader(ireader)) {
            StringBuilder inputBuilder = new StringBuilder();
            String line;
            while ((line = bufferReader.readLine()) != null) {
                inputBuilder.append(line).append("\n");
            }
            return inputBuilder.toString();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IO Exception when reading file: " + filePath);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while reading the file: " + filePath);
            e.printStackTrace();
        }
        return null;
    }

}
