package com.quiz.entity;

import lombok.Data;

import java.util.List;

@Data
public class Topic {
    private Integer id;
    private String name;
    private List<Quiz> quizList;
}
