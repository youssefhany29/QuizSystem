package test;

import model.Difficulty;
import model.TrueFalseQuestion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrueFalseQuestionTest {

    @Test
    void correctTrueShouldReturnTrue() {
        TrueFalseQuestion q = new TrueFalseQuestion(
                "Java is a compiled language.",
                5,
                Difficulty.EASY,
                true
        );

        assertTrue(q.checkAnswer("true"));
        assertTrue(q.checkAnswer("t"));
        assertTrue(q.checkAnswer("yes"));
        assertTrue(q.checkAnswer("Y"));
    }

    @Test
    void wrongAnswerShouldReturnFalse() {
        TrueFalseQuestion q = new TrueFalseQuestion(
                "Java is a compiled language.",
                5,
                Difficulty.EASY,
                true
        );

        assertFalse(q.checkAnswer("false"));
        assertFalse(q.checkAnswer("no"));
        assertFalse(q.checkAnswer("f"));
    }

    @Test
    void invalidInputShouldReturnFalse() {
        TrueFalseQuestion q = new TrueFalseQuestion(
                "Java is a compiled language.",
                5,
                Difficulty.EASY,
                true
        );

        assertFalse(q.checkAnswer("maybe"));
        assertFalse(q.checkAnswer(""));
        assertFalse(q.checkAnswer(null));
    }
}
