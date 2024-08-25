package com.quiz.service;

import com.quiz.entity.Question;
import com.quiz.repository.QuizQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizQuestionService {
    private final QuizQuestionRepository repository;

    public QuizQuestionService(QuizQuestionRepository repository) {
        this.repository = repository;
    }

    public void addQuestionInQuiz(int quizId, int questionId) {
        repository.addQuestionInQuiz(quizId, questionId);
    }

    public List<Question> getQuestionListByQuizId(int quizId) {
        return repository.getQuestionListByQuizId(quizId);

    }


}
