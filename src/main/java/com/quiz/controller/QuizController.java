package com.quiz.controller;

import com.quiz.entity.Quiz;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/add")
    public ResponseEntity<Object> addQuiz(@RequestBody Quiz quiz) {
        quizService.createQuiz(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteQuiz(@PathVariable Integer id) {
        quizService.deleteQuizById(id);
        return new ResponseEntity<>("Quiz Deleted", HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<Object> getAllQuiz(){
        List<Quiz> quizList = quizService.getAllQuiz();
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Quiz quiz){
        quizService.updateQuiz(quiz);
        return new ResponseEntity<>("Quiz updated" , HttpStatus.OK);
    }



}
