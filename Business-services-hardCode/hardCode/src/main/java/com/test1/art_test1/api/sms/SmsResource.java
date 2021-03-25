package com.test1.art_test1.api.sms;


import com.test1.art_test1.api.Result;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/svc/sms")
public class SmsResource {

    private final SmsService smsService;


    @Autowired
    public SmsResource(SmsService smsService) {
        this.smsService = smsService;

    }

    @GetMapping
    @PreAuthorize("hasAuthority('SMS_R') || hasAuthority('SMS_RW') ||hasAuthority('R')")
    public String getSmsStatus(String id) {
        return smsService.getSmsStatus(id);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('SMS_R') || hasAuthority('SMS_RW') ||hasAuthority('R')")
    public List<Result> getSms(String username) {
        return smsService.getAllUserSms(username);
    }

    @PostMapping("/user")
    @PreAuthorize("hasAuthority('SMS_RW') || hasAuthority('C')")
    public void createSms(@AuthenticationPrincipal Authentication authentication) {
        smsService.createSms(authentication);
    }
}


