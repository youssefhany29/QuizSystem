package app;

import model.*;
import service.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Question> questions = QuestionLoader.loadFromFile("questions.txt");

        Quiz quiz = new Quiz(questions, 30); // 30 sec timer

        QuizEngine engine = new QuizEngine(quiz);

        engine.startWithTimer();

        System.out.println("\nYour final score: " + quiz.calculateScore());
    }
}
