package test;

import model.Difficulty;
import model.MultipleChoiceQuestion;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultipleChoiceQuestionTest {

    @Test
    void correctAnswerReturnsTrue() {

        MultipleChoiceQuestion q = new MultipleChoiceQuestion(
                "Keyword for inheritance?",
                10,
                Difficulty.MEDIUM,
                List.of("extends", "implements", "import", "package"),
                "extends"
        );

        assertTrue(q.checkAnswer("extends"));
    }

    @Test
    void wrongAnswerReturnsFalse() {

        MultipleChoiceQuestion q = new MultipleChoiceQuestion(
                "Keyword for inheritance?",
                10,
                Difficulty.MEDIUM,
                List.of("extends", "implements", "import", "package"),
                "extends"
        );

        assertFalse(q.checkAnswer("import"));
    }
}
