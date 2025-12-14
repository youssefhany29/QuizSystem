package service;

import model.*;
import java.io.*;
import java.util.*;

public class QuestionLoader {

    public static List<Question> loadFromFile(String fileName) {
        List<Question> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {

                if (line.equalsIgnoreCase("MCQ")) {
                    String text = br.readLine();
                    int score = Integer.parseInt(br.readLine());
                    Difficulty diff = Difficulty.valueOf(br.readLine());

                    List<String> options = new ArrayList<>();
                    options.add(br.readLine());
                    options.add(br.readLine());
                    options.add(br.readLine());
                    options.add(br.readLine());

                    String correct = br.readLine();

                    questions.add(new MultipleChoiceQuestion(text, score, diff, options, correct));
                }

                else if (line.equalsIgnoreCase("TF")) {
                    String text = br.readLine();
                    int score = Integer.parseInt(br.readLine());
                    Difficulty diff = Difficulty.valueOf(br.readLine());
                    boolean correct = Boolean.parseBoolean(br.readLine());

                    questions.add(new TrueFalseQuestion(text, score, diff, correct));
                }
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        return questions;
    }
}
