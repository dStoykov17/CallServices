package com.test1.art_test1.api.user;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }


}
