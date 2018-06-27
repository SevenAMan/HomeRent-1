package org.zc.homerent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@RestController
public class HomeRentApplication {

    @GetMapping("/")
    public String hello(HttpServletResponse response) {
        response.setHeader(HttpHeaders.CONTENT_TYPE, "text/html");

        return "<script>\n" +
                "    window.location.href = window.location.href + \"/index.html\";\n" +
                "</script>";
    }

    public static void main(String[] args) {
        SpringApplication.run(HomeRentApplication.class, args);
    }
}
