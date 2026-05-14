package com.example.englishb2.dto;

public record ScoreResponse(

        int correctAnswers,

        int totalQuestions,

        double scoreOverTen,

        boolean approved

) {
}