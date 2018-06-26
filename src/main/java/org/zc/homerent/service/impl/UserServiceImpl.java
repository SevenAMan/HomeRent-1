package org.zc.homerent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zc.homerent.dao.UserDao;
import org.zc.homerent.entity.User;
import org.zc.homerent.service.UserService;
import org.zc.homerent.util.hash.Hash;

/**
 * @author FDws
 * Created on 2018/6/26 11:30
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final Hash hash;

    @Autowired
    public UserServiceImpl(UserDao userDao, Hash hash) {
        this.userDao = userDao;
        this.hash = hash;
    }


    @Override
    public User getUserById(String email) {
        return userDao.findById(email).orElse(null);
    }

    @Override
    public User logIn(String email, String password) {
        User u = getUserById(email);
        if (u == null) return null;

        String pass = u.getPassword();
        if (!pass.equals(hash.hashPassword(password, pass))) {
            return null;
        }

        return u;
    }

    @Override
    public User changeInfo(String email, String name, String password) {
        User u = getUserById(email);
        if (u == null) return null;
        u.setName(name);
        u.setPassword(hash.hashPassword(password));
        userDao.save(u);
        return u;
    }

    @Override
    public User register(String email, String name, String password) {
        User u = getUserById(email);
        if (u != null) {
            return null;
        }
        u = new User();
        u.setId(email);
        u.setPassword(hash.hashPassword(password));
        u.setName(name);
        u.setBalance(0);
        return userDao.save(u);
    }
}
