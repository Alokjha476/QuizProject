package com.quiz.repository;

import com.quiz.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class QuestionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addQuestion(Question question) {
        String query = "insert into question(question,opt1,opt2,opt3,correct_answer) values(?, ?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator creator = con -> {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, question.getQuestion());
            ps.setString(2, question.getOpt1());
            ps.setString(3, question.getOpt2());
            ps.setString(4, question.getOpt3());
            ps.setString(5, question.getCorrectAnswer());
            return ps;
        };

        jdbcTemplate.update(creator, keyHolder);
        question.setId(keyHolder.getKey().intValue());

//        jdbcTemplate.update(query, question.getQuestion(), question.getOpt1(), question.getOpt2(), question.getOpt3(), question.getCorrectAnswer());
    }

    public void updateQuestion(Question question) {
        String query = "UPDATE question set question = ?, opt1= ?, opt2= ?, opt3 = ?, correct_answer=? where id = ?";
        jdbcTemplate.update(query, question.getQuestion(),
                question.getOpt1(), question.getOpt2(), question.getOpt3(),
                question.getCorrectAnswer(), question.getId());

    }



}
