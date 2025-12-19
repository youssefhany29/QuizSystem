package test;

import model.Difficulty;
import model.Question;
import model.Quiz;
import model.TrueFalseQuestion;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {

    @Test
    void maxScoreShouldBeSumOfQuestionScores() {
        Question q1 = new TrueFalseQuestion("Q1", 5, Difficulty.EASY, true);
        Question q2 = new TrueFalseQuestion("Q2", 10, Difficulty.MEDIUM, false);

        Quiz quiz = new Quiz(List.of(q1, q2), 30);

        assertEquals(15, quiz.getMaxScore());
    }

    @Test
    void scoreShouldAccumulateCorrectly() {
        Question q1 = new TrueFalseQuestion("Q1", 5, Difficulty.EASY, true);
        Question q2 = new TrueFalseQuestion("Q2", 10, Difficulty.MEDIUM, false);

        Quiz quiz = new Quiz(List.of(q1, q2), 30);

        quiz.addToScore(q1.getScore());
        quiz.addToScore(q2.getScore());

        assertEquals(15, quiz.calculateScore());
    }

    @Test
    void shuffleShouldNotChangeNumberOfQuestions() {
        Question q1 = new TrueFalseQuestion("Q1", 5, Difficulty.EASY, true);
        Question q2 = new TrueFalseQuestion("Q2", 10, Difficulty.MEDIUM, false);
        Question q3 = new TrueFalseQuestion("Q3", 7, Difficulty.HARD, true);

        Quiz quiz = new Quiz(List.of(q1, q2, q3), 30);

        int before = quiz.getQuestions().size();
        quiz.shuffleQuestions();
        int after = quiz.getQuestions().size();

        assertEquals(before, after);
    }
}
