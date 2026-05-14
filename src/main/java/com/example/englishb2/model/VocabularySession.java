package com.example.englishb2.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VocabularySession {

    private final List<Vocabulary> questions;
    private final Set<Long> answeredQuestionIds = new HashSet<>();

    private int currentIndex;
    private int correctAnswers;
    private boolean showingSpanish;
    private boolean finished;

    public VocabularySession(List<Vocabulary> questions) {
        this.questions = new ArrayList<>(questions);
        this.currentIndex = 0;
        this.correctAnswers = 0;
        this.showingSpanish = false;
        this.finished = questions.isEmpty();
    }

    public Vocabulary getCurrentQuestion() {
        if (finished || currentIndex >= questions.size()) {
            return null;
        }

        return questions.get(currentIndex);
    }

    public void nextStartClick() {
        if (finished) {
            return;
        }

        if (!showingSpanish) {
            showingSpanish = true;
            return;
        }

        showingSpanish = false;
        currentIndex++;

        if (currentIndex >= questions.size()) {
            finished = true;
        }
    }

    public void answer(boolean correct) {
        Vocabulary current = getCurrentQuestion();

        if (current == null) {
            return;
        }

        if (answeredQuestionIds.contains(current.getId())) {
            return;
        }

        answeredQuestionIds.add(current.getId());

        if (correct) {
            correctAnswers++;
        }
    }

    public boolean isShowingSpanish() {
        return showingSpanish;
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

        return ((double) correctAnswers / questions.size()) * 10;
    }
}