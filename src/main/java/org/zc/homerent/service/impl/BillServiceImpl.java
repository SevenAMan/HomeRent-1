package org.zc.homerent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zc.homerent.dao.BillDao;
import org.zc.homerent.dao.UserDao;
import org.zc.homerent.entity.Bill;
import org.zc.homerent.entity.User;
import org.zc.homerent.service.BillService;

import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/26 11:07
 */
@Service
public class BillServiceImpl implements BillService {
    private final BillDao billDao;
    private final UserDao userDao;

    @Autowired
    public BillServiceImpl(BillDao billDao, UserDao userDao) {
        this.billDao = billDao;
        this.userDao = userDao;
    }

    @Override
    public List<Bill> getAll(String email) {
        return billDao.findAllByEmail(email);
    }

    @Override
    public List<Bill> getByPrice(String email, long min, long max) {
        return billDao.findAllByPriceBetweenAndEmail(min, max, email);
    }

    @Override
    public List<Bill> getByTime(String email, long min, long max) {
        return billDao.findAllByTimeBetweenAndEmail(min, max, email);
    }

    @Override
    public void recharge(String email, long price) {
        Bill b = new Bill();
        b.setId(System.currentTimeMillis() + email.split("@")[0]);
        b.setEmail(email);
        b.setPrice(price);
        b.setTime(System.currentTimeMillis());
        billDao.save(b);
        User u = userDao.getOne(email);
        u.setBalance(u.getBalance() + price);
        userDao.save(u);
    }
}
