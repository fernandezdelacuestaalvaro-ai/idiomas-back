package com.example.englishb2.service;

import com.example.englishb2.dto.QuestionResponse;
import com.example.englishb2.dto.RevealResponse;
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

    public VocabularyService(
            VocabularyRepository vocabularyRepository
    ) {

        this.vocabularyRepository =
                vocabularyRepository;
    }

    public QuestionResponse startSession(
            String nivel,
            String bloqueExamen
    ) {

        List<Vocabulary> vocabularyList;

        if (nivel != null &&
                bloqueExamen != null) {

            vocabularyList =
                    vocabularyRepository
                            .findByNivelAndBloqueExamen(
                                    nivel,
                                    bloqueExamen
                            );

        } else if (nivel != null) {

            vocabularyList =
                    vocabularyRepository
                            .findByNivel(nivel);

        } else if (bloqueExamen != null) {

            vocabularyList =
                    vocabularyRepository
                            .findByBloqueExamen(
                                    bloqueExamen
                            );

        } else {

            vocabularyList =
                    vocabularyRepository
                            .findAll();
        }

        Collections.shuffle(vocabularyList);

        session =
                new VocabularySession(
                        vocabularyList
                );

        return getCurrentQuestion();
    }

    public QuestionResponse getCurrentQuestion() {

        Vocabulary current =
                session.getCurrentQuestion();

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

        return new QuestionResponse(

                current.getId(),

                current.getTraduccion(),

                null,

                current.getCategoria(),

                current.getNivel(),

                current.getDificultad(),

                current.getBloqueExamen(),

                session.getCurrentIndex() + 1,

                session.getTotalQuestions(),

                false
        );
    }

    public RevealResponse reveal() {

        Vocabulary current =
                session.getCurrentQuestion();

        return new RevealResponse(

                current.getId(),

                current.getTraduccion(),

                current.getCastellano()
        );
    }

    public QuestionResponse answer(
            boolean correct
    ) {

        session.answer(correct);

        return getCurrentQuestion();
    }

    public ScoreResponse score() {

        double score =
                session.getScoreOverTen();

        return new ScoreResponse(

                session.getCorrectAnswers(),

                session.getTotalQuestions(),

                score,

                score >= 5
        );
    }
}