package com.test1.art_test1;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SecurityAuthDao {
    private final NamedParameterJdbcOperations namedTemplate;

    public SecurityAuthDao(NamedParameterJdbcOperations namedTemplate) {
        this.namedTemplate = namedTemplate;
    }

    public Authentication getAuthenticated(String username, String password) {
        final List<SimpleGrantedAuthority> list;
        try {
            namedTemplate.queryForObject("SELECT 1 FROM USERS WHERE login = :username", new MapSqlParameterSource("username", username), Integer.class);

            list =
                    namedTemplate.query("SELECT key FROM privilege WHERE id IN (SELECT id_privilege FROM users_privilege WHERE id_user IN (SELECT id FROM users WHERE login = :username))",
                            new MapSqlParameterSource("username", username), (rs, rowNum) -> new SimpleGrantedAuthority(rs.getString(1)));

        } catch (IncorrectResultSizeDataAccessException e) {
            throw new BadCredentialsException("External system authentication failed");
        }
        final SqlParameterSource sp = new MapSqlParameterSource("username", username);
        List<String> name = namedTemplate.query("SELECT username FROM users WHERE login= :username",
                sp, (resultSet, i) -> resultSet.getString("username"));
        List<String> pass = namedTemplate.query("SELECT password FROM users WHERE login= :username",
                sp, (resultSet, i) -> resultSet.getString("password"));
        if (name.contains(username) && pass.contains(password)) {
            return new UsernamePasswordAuthenticationToken
                    (username, password, list);
        } else {
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }


}
