package service;

import model.*;
import java.io.*;
import java.util.*;

public class QuestionLoader {

    public static List<Question> loadFromFile(String fileName) {
        return loadFromFile(fileName, null);
    }

    public static List<Question> loadFromFile(String fileName, Difficulty difficultyFilter) {
        List<Question> questions = new ArrayList<>();

        // MIXED = no filter
        Difficulty effectiveFilter = difficultyFilter;
        if (difficultyFilter == Difficulty.MIXED) {
            effectiveFilter = null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                if (line.equalsIgnoreCase("MCQ")) {
                    String text = br.readLine();
                    int score = Integer.parseInt(br.readLine());
                    Difficulty diff = Difficulty.valueOf(br.readLine().trim().toUpperCase());

                    List<String> options = new ArrayList<>();
                    options.add(br.readLine());
                    options.add(br.readLine());
                    options.add(br.readLine());
                    options.add(br.readLine());

                    String correct = br.readLine();

                    Question q = new MultipleChoiceQuestion(text, score, diff, options, correct);

                    if (effectiveFilter == null || q.getDifficulty() == effectiveFilter) {
                        questions.add(q);
                    }
                }

                else if (line.equalsIgnoreCase("TF")) {
                    String text = br.readLine();
                    int score = Integer.parseInt(br.readLine());
                    Difficulty diff = Difficulty.valueOf(br.readLine().trim().toUpperCase());
                    boolean correct = Boolean.parseBoolean(br.readLine().trim());

                    Question q = new TrueFalseQuestion(text, score, diff, correct);

                    if (effectiveFilter == null || q.getDifficulty() == effectiveFilter) {
                        questions.add(q);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }
}
