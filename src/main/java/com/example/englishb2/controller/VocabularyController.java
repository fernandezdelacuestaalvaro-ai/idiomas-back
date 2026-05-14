package com.example.englishb2.controller;

import com.example.englishb2.dto.AnswerRequest;
import com.example.englishb2.dto.QuestionResponse;
import com.example.englishb2.dto.RevealResponse;
import com.example.englishb2.dto.ScoreResponse;
import com.example.englishb2.service.VocabularyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vocabulary")
@CrossOrigin(origins = "*")
public class VocabularyController {

    private final VocabularyService vocabularyService;

    public VocabularyController(
            VocabularyService vocabularyService
    ) {

        this.vocabularyService =
                vocabularyService;
    }

    @PostMapping("/start")
    public QuestionResponse start(

            @RequestParam(required = false)
            String nivel,

            @RequestParam(required = false)
            String bloqueExamen
    ) {

        return vocabularyService
                .startSession(
                        nivel,
                        bloqueExamen
                );
    }

    @GetMapping("/current")
    public QuestionResponse current() {

        return vocabularyService
                .getCurrentQuestion();
    }

    @GetMapping("/reveal")
    public RevealResponse reveal() {

        return vocabularyService
                .reveal();
    }

    @PostMapping("/answer")
    public QuestionResponse answer(
            @RequestBody
            AnswerRequest request
    ) {

        return vocabularyService
                .answer(
                        request.correct()
                );
    }

    @GetMapping("/score")
    public ScoreResponse score() {

        return vocabularyService
                .score();
    }
}