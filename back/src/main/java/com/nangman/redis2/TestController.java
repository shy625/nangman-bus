package com.nangman.redis2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController("api/redis")
public class TestController {

    @RequestMapping("/test")
    public String test1(HttpSession session) {
        session.setAttribute("email", "test");
        return "test";
    }
}
