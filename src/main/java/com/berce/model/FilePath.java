package com.berce.model;

public enum FilePath {
    HERO_SURVIVED_PATH("src/main/resources/input_hero_win.txt", "src/main/resources/output_hero_win.txt"),
    HERO_DEAD_PATH("src/main/resources/input_hero_lose.txt", "src/main/resources/output_hero_lose.txt");

    private final String inputFilePath;
    private final String outputFilePath;

    FilePath(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public static FilePath fromChoice(Choice choice) {
        return switch (choice) {
            case HERO_SURVIVED-> HERO_SURVIVED_PATH ;
            case HERO_DEAD -> HERO_DEAD_PATH;
        };
    }
}
