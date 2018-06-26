package org.zc.homerent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.zc.homerent.entity.House;

import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/26 9:54
 */
@Repository
@NoRepositoryBean
public interface HouseDao extends JpaRepository<House, Integer> {
    List<House> findAllByEmailAndType(String email, Integer type);

    List<House> findAllByEmail(String email);

    List<House> findAllByAreaBetweenAndType(Integer min, Integer max, Integer type);

    List<House> findAllByBedAndLivingAndType(Integer bed, Integer living, Integer type);

    List<House> findAllByPriceBetweenAndType(Integer min, Integer max, Integer type);

    List<House> findAllByAreaBetween(Integer min, Integer max);
}
