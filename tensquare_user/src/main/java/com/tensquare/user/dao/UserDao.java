package com.tensquare.user.dao;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{
    public User findByMobile(String mobile);

    @Query(value = "UPDATE tb_user SET followcount=followcount+? WHERE id = ?",nativeQuery = true)
    @Modifying
    public void updateFllow(int x,String userId);

    @Query(value = "UPDATE tb_user SET fanscount=fanscount+? WHERE id = ?",nativeQuery = true)
    @Modifying
    public void updateFans(int x,String friendId);
}
