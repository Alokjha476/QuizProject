package com.quiz.controller;

import com.quiz.entity.Question;
import com.quiz.service.QuizQuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuizQuestionController {
    private final QuizQuestionService quizQuestionService;

    public QuizQuestionController(QuizQuestionService quizQuestionService) {
        this.quizQuestionService = quizQuestionService;
    }

    @PostMapping("/add/{quizId}")
    public ResponseEntity<Object> addQuestionInQuiz(@PathVariable Integer quizId, @RequestParam Integer questionId) {
        quizQuestionService.addQuestionInQuiz(quizId, questionId);
        return new ResponseEntity<>(" Question add in quiz successfully", HttpStatus.OK);
    }

    @GetMapping("/by-quizId/{quizId}")
    public ResponseEntity<Object> getQuestionListByQuizId(@PathVariable int quizId) {
        List<Question> questionList = quizQuestionService.getQuestionListByQuizId(quizId);
        return new ResponseEntity<>(questionList, HttpStatus.OK);

    }
}
