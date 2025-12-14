package service;

import model.Question;
import model.Quiz;
import java.util.List;
import java.util.Scanner;

public class QuizEngine {

    private Quiz quiz;
    private Scanner scanner = new Scanner(System.in);

    public QuizEngine(Quiz quiz) {
        this.quiz = quiz;
    }

    public void start() {
        quiz.shuffleQuestions();

        List<Question> questions = quiz.getQuestions();

        System.out.println("Quiz Started...");
        System.out.println("------------------");

        for (Question q : questions) {
            System.out.println("\nQuestion: " + q.getText());
            System.out.print("Your answer: ");

            String userAnswer = scanner.nextLine();

            if (q.checkAnswer(userAnswer)) {
                quiz.addToScore(q.getScore());
            }
        }
    }
}
