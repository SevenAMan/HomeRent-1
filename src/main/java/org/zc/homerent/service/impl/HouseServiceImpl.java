package org.zc.homerent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zc.homerent.dao.HouseDao;
import org.zc.homerent.entity.House;
import org.zc.homerent.service.HouseService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author FDws
 * Created on 2018/6/26 11:16
 */
@Service
public class HouseServiceImpl implements HouseService {
    private final HouseDao houseDao;

    @Autowired
    public HouseServiceImpl(HouseDao houseDao) {
        this.houseDao = houseDao;
    }

    @Override
    public List<House> findAll(String email, int type) {
        return houseDao.findAllByEmailAndType(email, type);
    }

    @Override
    public List<House> findAllByArea(String email, int min, int max) {
        return houseDao.findAllByAreaBetweenAndType(min, max, House.ON_SALE);
    }

    @Override
    public List<House> find(int type) {
        return houseDao.findAll().stream().filter(house -> house.getType() == House.ON_SALE).collect(Collectors.toList());
    }

    @Override
    public List<House> findBedAndLiving(int bed, int living) {
        return houseDao.findAllByBedAndLivingAndType(bed, living, House.ON_SALE);
    }

    @Override
    public void add(House house) {
        houseDao.save(house);
    }

    @Override
    public House findById(int id) {
        return houseDao.getOne(id);
    }


}
