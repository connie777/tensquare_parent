package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    @Query(value = "SELECT * FROM tb_problem,tb_pl " +
            "WHERE id=problemid AND labelid=? " +
            "order by replytime desc ",nativeQuery = true)
	public Page<Problem> newlist(String labelid, Pageable pageable);

    @Query(value = "SELECT * FROM tb_problem,tb_pl WHERE id=problemid AND labelid=? order by reply desc ",nativeQuery = true)
	public Page<Problem> hotlist(String labelid, Pageable pageable);

    @Query(value = "SELECT * FROM tb_problem,tb_pl WHERE id=problemid AND labelid=? AND reply='0' order by createtime desc ",nativeQuery = true)
	public Page<Problem> waitlist(String labelid, Pageable pageable);
}
