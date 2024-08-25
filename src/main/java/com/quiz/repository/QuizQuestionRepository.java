package com.quiz.repository;

import com.quiz.entity.Question;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class QuizQuestionRepository {

    private final JdbcTemplate jdbcTemplate;

    public QuizQuestionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addQuestionInQuiz(int quizId, int questionId) {
        String insertQuery = "insert into quiz_question values(?, ?)";
        jdbcTemplate.update(insertQuery, quizId, questionId);
    }

    public List<Question> getQuestionListByQuizId(int quizId) {
        String getQuery = "select id as id, question, opt1, opt2, opt3, correct_answer as opt4  from question q inner join quiz_question qq on q.id = qq.question_id where qq.quiz_id = ?";
        return jdbcTemplate.query(getQuery, new BeanPropertyRowMapper<>(Question.class), quizId);
    }

}
