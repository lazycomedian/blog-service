package com.lazycomedian.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/test")
//    @PreAuthorize("hasAuthority('hello')")
    public Object hello() {

        return "hello world!";
    }

}
