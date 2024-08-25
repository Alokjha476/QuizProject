package com.quiz.service;

import com.quiz.entity.Question;
import com.quiz.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void addQuestion(Question question) {
        questionRepository.addQuestion(question);
    }

    public void updateQuestion(Question question) {
        questionRepository.updateQuestion(question);

    }

}
