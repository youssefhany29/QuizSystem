package model;

public class TrueFalseQuestion extends Question{

    private final boolean correctAnswer;

    public TrueFalseQuestion(String text, int score, Difficulty difficulty, boolean correctAnswer){
        super(text,score,difficulty);
        this.correctAnswer = correctAnswer;
    }

    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        if (userAnswer == null ) {
            return false;
        }
        String normalized = userAnswer.trim().toLowerCase();

        boolean userChoice;
        if (normalized.equals("true") || normalized.equals("t")
                || normalized.equals("yes") || normalized.equals("y")){
            userChoice = true;
        } else if (normalized.equals("false") || normalized.equals("f")
                || normalized.equals("no") || normalized.equals("n")) {
            userChoice = false;
        } else  {
            userChoice = false;
        }
        return userChoice ==  correctAnswer;
    }
}
