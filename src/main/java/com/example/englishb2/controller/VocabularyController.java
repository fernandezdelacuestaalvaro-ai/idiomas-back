package com.example.englishb2.controller;

import com.example.englishb2.dto.AnswerRequest;
import com.example.englishb2.dto.QuestionResponse;
import com.example.englishb2.dto.ScoreResponse;
import com.example.englishb2.service.VocabularyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vocabulary")
@CrossOrigin(origins = "*")
public class VocabularyController {

    private final VocabularyService vocabularyService;

    public VocabularyController(VocabularyService vocabularyService) {
        this.vocabularyService = vocabularyService;
    }

    @PostMapping("/start")
    public QuestionResponse start(
            @RequestParam(required = false) String nivel,
            @RequestParam(required = false) String bloqueExamen,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String dificultad
    ) {
        return vocabularyService.start(
                nivel,
                bloqueExamen,
                categoria,
                dificultad
        );
    }

    @PostMapping("/answer")
    public QuestionResponse answer(@RequestBody AnswerRequest request) {
        return vocabularyService.answer(request.correct());
    }

    @GetMapping("/score")
    public ScoreResponse score() {
        return vocabularyService.score();
    }
}