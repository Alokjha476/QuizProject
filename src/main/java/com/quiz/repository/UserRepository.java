package com.quiz.repository;

import com.quiz.constants.Constants;
import com.quiz.entity.User;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Environment env;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    public void saveUser(User user) {
        String query = "insert into user(username, password, roles) values(?, ?, ?)";
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator creator = con -> {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRoles());
            return ps;
        };
        jdbcTemplate.update(creator, keyHolder);
        user.setId(keyHolder.getKey().intValue());

    }

    public User findByUsername(String username) {
        String query = "select id as id, username as username, password as password, roles as roles from user where username = ?";
        return jdbcTemplate.queryForObject(
                query, (rs, rn) -> {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRoles(rs.getString("roles"));
                    return user;
                },
                username);
    }

    public User updateUser(User user){
        String query = "update user set username=?, password=?, roles= ?   id =?";
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        jdbcTemplate.update(query, user.getId(),user.getUsername(), user.getPassword(), user.getRoles());
        return user;
    }

    @PostConstruct
    public void addAdmin() {
        String username = env.getProperty(Constants.ADMIN_MASTER_USERNAME, "admin");
        String roles = env.getProperty(Constants.ADMIN_MASTER_ROLES, "ADMIN");
        String password = passwordEncoder.encode(env.getProperty(Constants.ADMIN_MASTER_PASSWORD, "admin"));
        String insertUser = "INSERT INTO user (username, password, roles) SELECT ?, ?, ? WHERE NOT EXISTS (Select username From user WHERE username = ?) LIMIT 1";
        int count = jdbcTemplate.update(insertUser, username, password, roles, username);
        LOGGER.info("Rows updated: {}", count);
    }

}
