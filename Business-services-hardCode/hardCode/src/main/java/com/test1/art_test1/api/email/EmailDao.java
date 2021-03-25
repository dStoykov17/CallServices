package com.test1.art_test1.api.email;

import com.test1.art_test1.api.Result;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmailDao {
    private final NamedParameterJdbcOperations namedTemplate;

    @Autowired
    public EmailDao(NamedParameterJdbcOperations namedTemplate) {
        this.namedTemplate = namedTemplate;
    }

    public String getEmailStatus(String id) {
        final String sql = "SELECT status FROM email WHERE id = :id";

        final SqlParameterSource sp = new MapSqlParameterSource("id", id);

        return namedTemplate.queryForObject(sql, sp, String.class);
    }


    public List<Result> getAllUserEmail(String username) {

        final SqlParameterSource sp = new MapSqlParameterSource("username", username);
        return namedTemplate.query("SELECT status,user_id FROM email WHERE user_id IN (SELECT id FROM users WHERE username= :username)",
                sp, (resultSet, i) -> {
                    Result result = new Result();
                    result.setUser_id(resultSet.getString("user_id"));
                    result.setStatus(resultSet.getString("status"));

                    return result;
                });
    }

    public void createEmail(@AuthenticationPrincipal Authentication authentication) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String status = "sending";
        List<String> ids = namedTemplate.query("SELECT id FROM users WHERE username= :username",
                new MapSqlParameterSource("username", username), (resultSet, i) -> resultSet.getString("id"));
        String user_id = ids.get(0);

        final String sql = "INSERT INTO email(id,user_id,status) VALUES (users_seq.NEXTVAL,:user_id,:status)";
        namedTemplate.update(sql, new MapSqlParameterSource("user_id", user_id)
                .addValue("status", status));

    }
}
