package com.mall.searchservice.controller;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mall.searchservice.service.impl.test;

// Marking the class as a REST controller
@RestController
@RequestMapping("/search")  // Base URL for this controller
public class testController {

    // Handles GET requests to /api/test/hello

    @Autowired
    private test test;

    @GetMapping("/hello")
    public String hello() {
        test.test();
        System.out.println("asdasdasd");

        return "Hello, welcome to the search service!";
    }

    // Add more endpoints here...
}
