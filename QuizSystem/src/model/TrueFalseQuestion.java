package model;

public class TrueFalseQuestion extends Question {

    private final boolean correctAnswer;

    public TrueFalseQuestion(String text, int score, Difficulty difficulty, boolean correctAnswer) {
        super(text, score, difficulty);
        this.correctAnswer = correctAnswer;
    }

    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        if (userAnswer == null) return false;

        String normalized = userAnswer.trim().toLowerCase();

        return switch (normalized) {
            case "" -> false;
            case "true", "t", "yes", "y" -> correctAnswer == true;
            case "false", "f", "no", "n" -> correctAnswer == false;
            default -> false;
        };

    }
}
