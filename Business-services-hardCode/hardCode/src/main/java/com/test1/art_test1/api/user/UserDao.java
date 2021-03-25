package com.test1.art_test1.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Repository
public class UserDao {
    private final NamedParameterJdbcOperations namedTemplate;

    @Autowired
    public UserDao(NamedParameterJdbcOperations namedTemplate) {
        this.namedTemplate = namedTemplate;
    }


    public String getUsername(String username) {
        final String sql = "SELECT username FROM users WHERE username = :username";

        final SqlParameterSource sp = new MapSqlParameterSource("username", username);

        return namedTemplate.queryForObject(sql, sp, String.class);
    }

    public String getUserDetails(String username) {
        final String sql = "SELECT * FROM users WHERE username = :username";
        final SqlParameterSource sp = new MapSqlParameterSource("username", username);
        return namedTemplate.queryForObject(sql, sp, String.class);
    }


}
