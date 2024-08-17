package com.quiz.entity;

import lombok.Data;

@Data
public class Quiz {
    private Integer id;
    private String title;
    private Integer topic_id;
    private Integer max_score;
}
