package com.example.englishb2.dto;

public record QuestionResponse(

        Long id,

        String englishText,

        String spanishText,

        String category,

        String level,

        String difficulty,

        String examBlock,

        int currentQuestion,

        int totalQuestions,

        boolean finished

) {
}