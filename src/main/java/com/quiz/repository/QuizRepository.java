package com.quiz.repository;

import com.quiz.entity.Quiz;
import com.quiz.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class QuizRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int addQuiz(Quiz quiz){
        String query = "insert into quiz(id,title,topic_id, max_score) values(:id,:title,:topic_id, :max_score)";
        Map<String , Object> param = new HashMap<>();
        param.put("id", quiz.getId());
        param.put("title", quiz.getTitle());
        param.put("topic_id", quiz.getTopic_id());
        param.put("max_score", quiz.getMax_score());

       return jdbcTemplate.update(query,param);

    }
}
