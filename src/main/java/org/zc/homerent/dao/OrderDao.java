package org.zc.homerent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.zc.homerent.entity.Order;

import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/26 10:01
 */
@Repository
@NoRepositoryBean
public interface OrderDao extends JpaRepository<Order, String> {
    List<Order> findAllByEmail(String email);

    List<Order> findAllByEmailAndBeginBetween(String email, Long begin1, Long begin2);
}
