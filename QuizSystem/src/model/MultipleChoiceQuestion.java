package model;

import java.util.Collections;
import java.util.List;

public class MultipleChoiceQuestion extends Question {

    private final List<String> options;
    private final String correctAnswer;

    public MultipleChoiceQuestion(String text,
                                  int score,
                                  Difficulty difficulty,
                                  List<String> options,
                                  String correctAnswer) {

        super(text, score, difficulty);
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public List<String> getOptions() {
        return Collections.unmodifiableList(options);
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        if (userAnswer == null) {
            return false;
        }
        return correctAnswer.equalsIgnoreCase(userAnswer.trim());
    }
}
