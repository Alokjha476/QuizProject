package com.quiz.entity;

import lombok.Data;

@Data
public class Question {

    private Integer id;
    private String question;
    private String correctAnswer;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;
}
