package com.quiz.controller;

import com.quiz.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @PostMapping("user/progress/add")
    public ResponseEntity<Object> addScore(@RequestParam Integer quizId, @RequestParam Integer scoreObtain) {
        int count = progressService.addScore(quizId, scoreObtain);
        return new ResponseEntity<>("Data successfully inserted, row updated: " + count, HttpStatus.OK);
    }

    @GetMapping("/user/progress/get")
    public ResponseEntity<Object> getUserSpecificScore() {
        return new ResponseEntity<>(progressService.getUserSpecificScore(), HttpStatus.OK);
    }

}
