package com.example.englishb2.model;

import java.util.ArrayList;
import java.util.List;

public class VocabularySession {

    private final List<Vocabulary> questions;

    private int currentIndex;

    private int correctAnswers;

    private boolean finished;

    public VocabularySession(
            List<Vocabulary> questions
    ) {

        this.questions =
                new ArrayList<>(questions);

        this.currentIndex = 0;

        this.correctAnswers = 0;

        this.finished =
                questions.isEmpty();
    }

    public Vocabulary getCurrentQuestion() {

        if (finished ||
                currentIndex >= questions.size()) {

            return null;
        }

        return questions.get(currentIndex);
    }

    public void answer(boolean correct) {

        if (correct) {
            correctAnswers++;
        }

        currentIndex++;

        if (currentIndex >= questions.size()) {
            finished = true;
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public boolean isFinished() {
        return finished;
    }

    public double getScoreOverTen() {

        if (questions.isEmpty()) {
            return 0;
        }

        return ((double) correctAnswers
                / questions.size()) * 10;
    }
}