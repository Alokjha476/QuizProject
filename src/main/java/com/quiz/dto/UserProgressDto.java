package com.quiz.dto;

import lombok.Data;

@Data
public class UserProgressDto {

    private int quizId;
    private String quizName;
    private int scoreObtain;

}
