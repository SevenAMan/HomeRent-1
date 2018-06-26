package org.zc.homerent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.zc.homerent.entity.Bill;

import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/26 10:04
 */
@Repository
@NoRepositoryBean
public interface BillDao extends JpaRepository<Bill, String> {
    List<Bill> findAllByEmail(String email);

    List<Bill> findAllByTimeBetweenAndEmail(Long begin, Long end, String email);

    List<Bill> findAllByPriceBetweenAndEmail(Long min, Long max, String email);
}
