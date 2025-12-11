package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz implements Gradable {

    private final List<Question> questions;
    private final int timeLimitSeconds;
    private int score;

    public Quiz(List<Question> questions, int timeLimitSeconds) {
        this.questions = new ArrayList<>(questions);
        this.timeLimitSeconds = timeLimitSeconds;
        this.score = 0;
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public int getTimeLimitSeconds() {
        return timeLimitSeconds;
    }

    public void shuffleQuestions() {
        Collections.shuffle(questions);
    }

    public void addToScore(int value) {
        this.score += value;
    }

    @Override
    public int calculateScore() {
        return score;
    }

    public int getMaxScore() {
        int total = 0;
        for (Question q : questions) {
            total += q.getScore();
        }
        return total;
    }
}
