package com.lcyan.basic.core.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean  // 该接口不是一个Repository，不需要生成代理实现
public interface BaseRepository <T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor <T>
																				, QuerydslPredicateExecutor<T>  //如果使用了QueryDSL还可以实现这个接口
{

}