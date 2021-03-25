package com.test1.art_test1.api.email;

import com.test1.art_test1.api.Result;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/svc/email")
public class EmailResource {
    private final EmailService emailService;

    public EmailResource(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CALL_R') || hasAuthority('CALL_RW') ||hasAuthority('R')")
    public String getEmailStatus(String id) {
        return emailService.getEmailStatus(id);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('CALL_R') || hasAuthority('CALL_RW') ||hasAuthority('R')")
    public List<Result> getAllUserEmail(String username) {
        return emailService.getAllUserEmail(username);
    }

    @PostMapping("/user")
    @PreAuthorize("hasAuthority('EMAIL_RW') || hasAuthority('C')")
    public void createEmail(@AuthenticationPrincipal Authentication authentication) {
        emailService.createEmail(authentication);
    }


}
