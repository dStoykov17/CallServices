package com.test1.art_test1.api;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Validated
public class ArtResource {

    @GetMapping("/svc/get")
    @PreAuthorize("hasAuthority('C')")
    public String get() {
        return "Hello";
    }

    @GetMapping("/svc/get2/{id}")
    @PreAuthorize("hasAuthority('C')")
    public String get2(@Length(max = 10) @PathVariable String id) {
        return "Hello";
    }

    @PostMapping("/svc/post")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody PostBody postBody) {

    }

    @GetMapping("/healthcheck")
    public String laod() {
        return "Ok";
    }
}
