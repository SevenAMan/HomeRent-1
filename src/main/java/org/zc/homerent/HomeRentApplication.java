package org.zc.homerent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HomeRentApplication {

    @GetMapping("/")
    public String hello() {
        return "Hello, Sprint Boot Home Rent System!";
    }

    public static void main(String[] args) {
        SpringApplication.run(HomeRentApplication.class, args);
    }
}
