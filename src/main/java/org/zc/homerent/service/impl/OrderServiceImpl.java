package org.zc.homerent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zc.homerent.dao.OrderDao;
import org.zc.homerent.entity.Order;
import org.zc.homerent.service.OrderService;

import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/26 11:37
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> findByEmail(String email) {
        return orderDao.findAllByEmail(email);
    }

    @Override
    public List<Order> findByTime(String email, long begin, long end) {
        return orderDao.findAllByEmailAndBeginBetween(email, begin, end);
    }

    @Override
    public void add(Order order) {
        orderDao.save(order);
    }
}
