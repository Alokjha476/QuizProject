package com.quiz.repository;

import com.quiz.entity.Quiz;
import com.quiz.utils.UserUtility;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class QuizRepository {

    private final JdbcTemplate jdbcTemplate;

    public QuizRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addQuiz(Quiz quiz) {
        String query = "insert into quiz(title,topic_id, max_score) values(?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator creator = con -> {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, quiz.getTitle());
            ps.setInt(2, quiz.getTopicId());
            ps.setInt(3, quiz.getMaxScore());
            return ps;
        };
        jdbcTemplate.update(creator, keyHolder);
        quiz.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
    }

    public void updateQuiz(Quiz quiz) {
        String query = "update quiz set name= ? where id =?";
        jdbcTemplate.update(query, quiz.getTitle(), quiz.getMaxScore(), quiz.getTopicId());
    }

    public List<Quiz> quizListByTopicId(Integer id) {
        String query = "select id as id, title as title from quiz where topic_id = ?";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Quiz.class));
    }

    public void deleteQuizById(Integer id) {
        String query = "delete from quiz where id = ?";
        jdbcTemplate.update(query, id);

    }

    public List<Quiz> getAllQuiz() {
        String query = "SELECT * from quiz";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Quiz.class));
    }

}

