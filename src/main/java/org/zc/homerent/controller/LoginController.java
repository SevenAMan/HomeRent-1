package org.zc.homerent.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zc.homerent.controller.util.Format;
import org.zc.homerent.controller.util.ReturnStatus;

/**
 * @author FDws
 * Created on 2018/6/26 8:19
 */
@RestController
public class LoginController {
    @PostMapping("/user")
    public Format register(@RequestParam String email,
                           @RequestParam String name,
                           @RequestParam String password){

        return new Format().code(ReturnStatus.SUCCESS);
    }
}
