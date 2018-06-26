package org.zc.homerent.service;

import org.zc.homerent.entity.House;

import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/26 10:30
 */

public interface HouseService {
    List<House> findAll(String email, int type);

    List<House> findAllByArea(String email, int min, int max);

    List<House> find(int type);

    List<House> findBedAndLiving(int bed, int living);
}
