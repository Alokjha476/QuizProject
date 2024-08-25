package com.quiz.service;

import com.quiz.entity.Quiz;
import com.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public void createQuiz(Quiz quiz){
        quizRepository.addQuiz(quiz);
    }
    public List<Quiz> quizListByTopicId(Integer id){
       return quizRepository.quizListByTopicId(id);
    }

    public void  deleteQuizById(Integer id){
        quizRepository.deleteQuizById(id);

    }
    public List<Quiz> getAllQuiz(){
        return quizRepository.getAllQuiz();
    }

}
