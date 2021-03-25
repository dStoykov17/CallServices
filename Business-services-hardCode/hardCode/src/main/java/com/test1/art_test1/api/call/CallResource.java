package com.test1.art_test1.api.call;

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
@RequestMapping("/svc/call")
public class CallResource {
    private final CallService callService;

    public CallResource(CallService callService) {
        this.callService = callService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CALL_R') || hasAuthority('CALL_RW') ||hasAuthority('R')")
    public String getCallStatus(String id) {
        return callService.getCallStatus(id);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('CALL_R') || hasAuthority('CALL_RW') ||hasAuthority('R')")
    public List<Result> getAllUserCall(String username) {
        return callService.getAllUserCall(username);
    }

    @PostMapping("/user")
    @PreAuthorize("hasAuthority('CALL_RW') || hasAuthority('C')")
    public void createCall(@AuthenticationPrincipal Authentication authentication) {
        callService.createCall(authentication);
    }
}
