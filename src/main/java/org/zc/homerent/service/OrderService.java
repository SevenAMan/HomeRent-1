package org.zc.homerent.service;

import org.zc.homerent.entity.Order;

import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/26 10:39
 */

public interface OrderService {
    List<Order> findByEmail(String email);

    List<Order> findByTime(String email, long begin, long end);
}
