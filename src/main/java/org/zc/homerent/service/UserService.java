package org.zc.homerent.service;

import org.zc.homerent.entity.User;

/**
 * @author FDws
 * Created on 2018/6/26 10:13
 */

public interface UserService {
    User getUserById(String email);

    User logIn(String email, String password);

    User changeInfo(String email, String name, String password);
}
