package com.berce.utils.handler;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReaderHandler {

    private static final Logger logger = Logger.getLogger(FileReaderHandler.class.getName());

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
            logger.log(Level.SEVERE, "File not found: " + filePath, e );
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO Exception when reading file: " + filePath, e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An unexpected error occurred while reading the file: " + filePath, e);
        }
        return null;
    }

}
