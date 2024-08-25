package com.quiz.service;

import com.quiz.dto.UserProgressDto;
import com.quiz.repository.ProgressRepository;
import com.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    public int addScore(int quizId, int scoreObtain){
        return progressRepository.addScore(quizId, scoreObtain);
    }

    public List<UserProgressDto> getUserSpecificScore() {
        return progressRepository.getUserSpecificScore();
    }


}
