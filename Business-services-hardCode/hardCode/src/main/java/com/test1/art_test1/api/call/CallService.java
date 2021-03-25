package com.test1.art_test1.api.call;

import com.test1.art_test1.api.Result;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallService {
    private final CallDao callDao;

    public CallService(CallDao callDao) {
        this.callDao = callDao;
    }

    public String getCallStatus(String id) {
        return callDao.getCallStatus(id);
    }


    public List<Result> getAllUserCall(String username) {
        return callDao.getAllUserCall(username);
    }

    public void createCall(@AuthenticationPrincipal Authentication authentication) {
        callDao.createCall(authentication);
    }

}
