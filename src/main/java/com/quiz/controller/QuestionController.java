package com.quiz.controller;

import com.quiz.entity.Question;
import com.quiz.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
        return new ResponseEntity<>("Question Created Successfully: " , HttpStatus.CREATED);

    }
    @PutMapping("/update")
    public ResponseEntity<Object> updateQuestion(@RequestBody Question question) {
        questionService.updateQuestion(question);
        return new ResponseEntity<>(question, HttpStatus.OK);

    }
}
