package com.quiz.repository;

import com.quiz.dto.UserProgressDto;
import com.quiz.entity.CustomUserDetails;
import com.quiz.utils.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProgressRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addScore(int quizId, int scoreObtain) {
        CustomUserDetails userDetails = UserUtility.getUserDetails();
        String addScoreQuery = "insert into user_progress (score_obtain, user_id, quiz_id) values (?, ?, ?)";
        return jdbcTemplate.update(addScoreQuery, scoreObtain, userDetails.getUserId(), quizId);
    }

    public List<UserProgressDto> getUserSpecificScore() {
        CustomUserDetails userDetails = UserUtility.getUserDetails();
        String query = "Select q.id as quizId, q.title as quizName, up.score_obtain as scoreObtain from user_progress as up inner join quiz q on up.quiz_id = q.id where up.user_id = ?";
        return jdbcTemplate.query(query, (rs, rn) -> {
            UserProgressDto userProgressDto = new UserProgressDto();
            userProgressDto.setQuizId(rs.getInt("quizId"));
            userProgressDto.setQuizName(rs.getString("quizName"));
            userProgressDto.setScoreObtain(rs.getInt("scoreObtain"));
            return userProgressDto;
        }, userDetails.getUserId());
    }


}
