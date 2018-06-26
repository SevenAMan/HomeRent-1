package org.zc.homerent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.zc.homerent.entity.User;

/**
 * @author FDws
 * Created on 2018/6/26 9:28
 */
@NoRepositoryBean
@Repository
public interface UserDao extends JpaRepository<User, String> {
}
