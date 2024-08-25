package com.quiz.repository;

import com.quiz.entity.Topic;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@Transactional
public class TopicRepository {

    private final JdbcTemplate jdbcTemplate;

    public TopicRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // add topic

    public void addTopic(Topic topic) {
        String sql = "insert into topic(name) VALUES (?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, topic.getName());
            return ps;
        }, keyHolder);
        topic.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());

    }

    public Topic findTopicById(Integer id) {
        String sql = "SELECT * FROM topic WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Topic.class), id);
    }

    public void updateTopic(Topic topic) {
        String sql = "update topic set name= ? where id = ?";
        jdbcTemplate.update(sql, topic.getName(), topic.getId());
    }

    public void deleteTopic(Integer id) {
        unlinkQuiz(id);
        String sql = "delete from topic where id= ?";
        jdbcTemplate.update(sql, id);

    }

    public List<Topic> getAll() {
        String sql = "select * from topic";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Topic.class));
    }

    public void unlinkQuiz(Integer id) {
        String query = "UPDATE  quiz set topic_id =null where topic_id = ? ";
        jdbcTemplate.update(query, id);

    }

}


