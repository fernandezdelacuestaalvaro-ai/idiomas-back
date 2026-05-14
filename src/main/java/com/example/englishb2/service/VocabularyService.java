package com.example.englishb2.service;

import com.example.englishb2.dto.QuestionResponse;
import com.example.englishb2.dto.ScoreResponse;
import com.example.englishb2.model.Vocabulary;
import com.example.englishb2.model.VocabularySession;
import com.example.englishb2.repository.VocabularyRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class VocabularyService {

    private final VocabularyRepository vocabularyRepository;

    private VocabularySession session;

    public VocabularyService(VocabularyRepository vocabularyRepository) {
        this.vocabularyRepository = vocabularyRepository;
    }

    public QuestionResponse start(
            String nivel,
            String bloqueExamen,
            String categoria,
            String dificultad
    ) {
        if (session == null || session.isFinished()) {

            List<Vocabulary> vocabularyList;

            if (nivel != null) {
                vocabularyList =
                        vocabularyRepository.findByNivelOrderByIdAsc(nivel);
            } else {
                vocabularyList =
                        vocabularyRepository.findAllByOrderByIdAsc();
            }

            session =
                    new VocabularySession(vocabularyList);

            return buildResponse();
        }

        session.nextStartClick();

        return buildResponse();
    }

    public QuestionResponse answer(boolean correct) {
        checkSession();

        session.answer(correct);

        return buildResponse();
    }

    public ScoreResponse score() {
        checkSession();

        double score = Math.round(session.getScoreOverTen() * 100.0) / 100.0;

        return new ScoreResponse(
                session.getCorrectAnswers(),
                session.getTotalQuestions(),
                score,
                score >= 5
        );
    }

    private QuestionResponse buildResponse() {
        Vocabulary current = session.getCurrentQuestion();

        if (current == null) {
            return new QuestionResponse(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    session.getCurrentIndex(),
                    session.getTotalQuestions(),
                    true
            );
        }

        String text = session.isShowingSpanish()
                ? current.getCastellano()
                : current.getTraduccion();

        String languageShown = session.isShowingSpanish()
                ? "SPANISH"
                : "ENGLISH";

        return new QuestionResponse(
                current.getId(),
                text,
                languageShown,
                current.getCategoria(),
                current.getNivel(),
                current.getDificultad(),
                current.getBloqueExamen(),
                session.getCurrentIndex() + 1,
                session.getTotalQuestions(),
                false
        );
    }

    private void checkSession() {
        if (session == null) {
            throw new IllegalStateException("Primero pulsa START.");
        }
    }
}