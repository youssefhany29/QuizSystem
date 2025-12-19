package app;

import model.*;
import service.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your student ID: ");
        String id = scanner.nextLine();

        Student student = new Student(id, name);

        System.out.println("\nWelcome, " + student.getStudentName() + " (ID: " + student.getStudentId() + ")");
        System.out.println("====================================\n");

        while (true) {

            System.out.println("Please choose an exam (or type EXIT to quit):");
            System.out.println("1) Java Basics Quiz");
            System.out.println("2) Math Quiz");
            System.out.println("3) True/False Mixed Quiz");
            System.out.print("Your choice: ");

            String choiceInput = scanner.nextLine();

            if (choiceInput.equalsIgnoreCase("EXIT")) {
                System.out.println("\nGoodbye, " + student.getStudentName() + "!");
                break;
            }

            int choice;

            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please try again.\n");
                continue;
            }

            String fileName;
            int timeLimitSeconds;

            switch (choice) {
                case 1:
                    fileName = "data/java_quiz.txt";
                    timeLimitSeconds = 60;
                    break;
                case 2:
                    fileName = "data/math_quiz.txt";
                    timeLimitSeconds = 45;
                    break;
                case 3:
                    fileName = "data/mixed_tf_quiz.txt";
                    timeLimitSeconds = 30;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
                    continue;
            }

            List<Question> questions = QuestionLoader.loadFromFile(fileName);

            if (questions.isEmpty()) {
                System.out.println("No questions found for this exam. Check the file: " + fileName);
                continue;
            }

            Quiz quiz = new Quiz(questions, timeLimitSeconds);

            QuizEngine engine = new QuizEngine(quiz);

            System.out.println("\nStarting quiz for " + student.getStudentName() + "...");
            System.out.println("Time limit: " + quiz.getTimeLimitSeconds() + " seconds\n");

            engine.startWithTimer();

            System.out.println("\n====================================");
            System.out.println("Student: " + student);
            int maxScore = quiz.getMaxScore();
            int finalScore = quiz.calculateScore();
            double percentage = (maxScore == 0) ? 0 : (finalScore * 100.0 / maxScore);

            System.out.println("Your final score: " + finalScore + " out of " + maxScore);
            System.out.printf("Percentage: %.2f%%\n", percentage);
            System.out.println("====================================\n");

        }
    }
}
