package com.Eelections.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class loginController {
    @GetMapping("/login")
    public String Login(){
        log.info("user requested log in");
        return "login";
    }
}
