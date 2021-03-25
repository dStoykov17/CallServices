package com.test1.art_test1.api.email;

import com.test1.art_test1.api.Result;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    private final EmailDao emailDao;

    public EmailService(EmailDao emailDao) {
        this.emailDao = emailDao;
    }

    public String getEmailStatus(String id) {
        return emailDao.getEmailStatus(id);
    }

    public List<Result> getAllUserEmail(String username) {
        return emailDao.getAllUserEmail(username);
    }

    public void createEmail(@AuthenticationPrincipal Authentication authentication) {
        emailDao.createEmail(authentication);
    }
}
