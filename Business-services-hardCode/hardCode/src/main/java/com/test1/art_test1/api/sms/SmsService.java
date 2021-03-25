package com.test1.art_test1.api.sms;

import com.test1.art_test1.api.Result;
import org.hibernate.validator.constraints.Length;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Service
public class SmsService {
    private final SmsDao smsDao;


    public SmsService(SmsDao smsDao) {
        this.smsDao = smsDao;
    }

    public List<Result> getAllUserSms(String username) {
        return smsDao.getAllUserSms(username);
    }

    public String getSmsStatus(String id) {
        return smsDao.getSmsStatus(id);
    }

    public void createSms(@AuthenticationPrincipal Authentication authentication) {
        smsDao.createSms(authentication);

    }
}

