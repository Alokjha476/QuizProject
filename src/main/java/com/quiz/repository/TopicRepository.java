package com.quiz.repository;

import com.quiz.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TopicRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate1;

    // add topic

    public int addTopic(Topic topic) {
        String sql = "insert into topic(id, name) VALUES (:id, :name)";
        Map<String, Object> param = new HashMap<>();
        param.put("id", topic.getId());
        param.put("name", topic.getName());
        return jdbcTemplate1.update(sql, param);

    }

    public Topic findTopicById(Integer id) {
        String sql = "SELECT * FROM topic WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbcTemplate1.queryForObject(sql, params, new RowMapper<Topic>() {
            @Override
            public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
                Topic topic = new Topic();
                topic.setId(rs.getInt("id"));
                topic.setName(rs.getString("name"));
                return topic;
            }
        });
    }

    public void updateTopic(Topic topic) {
        String sql = "update topic set name= :name where id = :id";
        Map<String, Object> param = new HashMap<>();
        param.put("id", topic.getId());
        param.put("name", topic.getName());
        jdbcTemplate1.update(sql, param);
    }

    public void deleteTopic(Integer id) {
        String sql = "delete from topic where id= :id";
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        jdbcTemplate1.update(sql, param);

    }

    public List<Topic> getAll(){
        String sql = "select * from topic";
       return jdbcTemplate1.query(sql, new BeanPropertyRowMapper<>(Topic.class));
    }


}


