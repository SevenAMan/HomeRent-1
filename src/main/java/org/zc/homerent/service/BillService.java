package org.zc.homerent.service;

import org.zc.homerent.entity.Bill;

import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/26 10:26
 */

public interface BillService {
    List<Bill> getAll(String email);

    List<Bill> getByPrice(String email, long min, long max);

    List<Bill> getByTime(String email, long min, long max);

    void recharge(String email, long price);
}
