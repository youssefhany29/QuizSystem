package service;

import model.Question;
import model.Quiz;
import model.MultipleChoiceQuestion;

import java.util.List;
import java.util.Scanner;

public class QuizEngine {

    private final Quiz quiz;
    private final Scanner scanner = new Scanner(System.in);
    private volatile boolean timeIsUp = false;

    public QuizEngine(Quiz quiz) {
        this.quiz = quiz;
    }

    public void start() {
        quiz.shuffleQuestions();
        runQuizLoop();
    }

    public void startWithTimer() {
        quiz.shuffleQuestions();

        Thread timerThread = new Thread(() -> {
            int secondsLeft = quiz.getTimeLimitSeconds();
            try {
                while (secondsLeft > 0 && !timeIsUp) {
                    Thread.sleep(1000);
                    secondsLeft--;
                }
                if (!timeIsUp) {
                    timeIsUp = true;
                    System.out.println("\n⏳ Time is up!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        timerThread.start();

        runQuizLoop();

        timeIsUp = true;
        System.out.println();
    }

    private void runQuizLoop() {

        List<Question> questions = quiz.getQuestions();

        System.out.println("Quiz Started...");
        System.out.println("------------------");

        for (Question q : questions) {

            if (timeIsUp) {
                break;
            }

            System.out.println("\nQuestion: " + q.getText());

            String userAnswerText;

            if (q instanceof MultipleChoiceQuestion) {
                MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) q;
                List<String> options = mcq.getOptions();

                for (int i = 0; i < options.size(); i++) {
                    System.out.println((i + 1) + ") " + options.get(i));
                }

                System.out.print("Your answer (1-" + options.size() + " or text): ");
                String userInput = scanner.nextLine();

                String mappedAnswer = userInput;
                try {
                    int idx = Integer.parseInt(userInput.trim());
                    if (idx >= 1 && idx <= options.size()) {
                        mappedAnswer = options.get(idx - 1);
                    }
                } catch (NumberFormatException ignored) {
                }

                userAnswerText = mappedAnswer;

            }  else {
                System.out.println("(This is a True/False question)");
                System.out.println("Type: true / false");
                System.out.print("Your answer: ");
                userAnswerText = scanner.nextLine();
            }

            if (q.checkAnswer(userAnswerText)) {
                quiz.addToScore(q.getScore());
            }
        }
    }
}
