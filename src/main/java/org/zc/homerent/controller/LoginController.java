package org.zc.homerent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zc.homerent.controller.util.Format;
import org.zc.homerent.controller.util.ReturnStatus;
import org.zc.homerent.entity.User;
import org.zc.homerent.service.UserService;
import org.zc.homerent.util.StringUtil;

import javax.servlet.http.HttpSession;

/**
 * @author FDws
 * Created on 2018/6/26 8:19
 */
@RestController
public class LoginController {
    private final UserService service;

    @Autowired
    public LoginController(UserService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public Format register(@RequestParam String email,
                           @RequestParam String password) {
        User u = service.register(email, "", password);
        if (u == null) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.USER_ALREADY_EXISTS);
        }
        return new Format().code(ReturnStatus.SUCCESS).addData("user", u);
    }

    @PutMapping("/user")
    public Format changeInfo(@RequestParam String email,
                             @RequestParam String password) {
        User u = service.changeInfo(email, "", password);
        if (u == null) {
            return new Format().code(ReturnStatus.FAILURE);
        } else {
            return new Format().code(ReturnStatus.SUCCESS).addData("user", u);
        }
    }

    @GetMapping("/user")
    public Format getUser(HttpSession session) {
        String email = (String) session.getAttribute("user");
        if (email == null) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.NO_SUCH_USER);
        } else {
            return new Format().code(ReturnStatus.SUCCESS).addData("user", service.getUserById(email));
        }
    }

    @PostMapping("/session")
    public Format signIn(@RequestParam String email,
                         @RequestParam String password,
                         HttpSession session) {
        User u = service.logIn(email, password);
        if (u == null) {
            return new Format().code(ReturnStatus.FAILURE).message("Password illegal");
        } else {
            session.setAttribute("user", email);
            return new Format().code(ReturnStatus.SUCCESS).addData("user", u);
        }
    }

    @DeleteMapping("/session")
    public Format signOut(HttpSession session) {
        session.removeAttribute("user");
        return new Format().code(ReturnStatus.SUCCESS);
    }

    @GetMapping("/session/status")
    public Format status(HttpSession session) {
        return new Format().code(ReturnStatus.SUCCESS)
                .addData("status", session.getAttribute("user") != null)
                .addData("email", session.getAttribute("user"));
    }
}
