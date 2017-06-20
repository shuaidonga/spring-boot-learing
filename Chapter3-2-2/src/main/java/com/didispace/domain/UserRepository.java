package com.didispace.domain;

import java.util.Map;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.didispace.domain.entity.User;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/23 下午2:34.
 * @blog http://blog.didispace.com
 */
@CacheConfig(cacheNames = "users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public interface UserRepository extends JpaRepository<User, Long> {

	@Cacheable
	@Query(nativeQuery = true)
	Page<User> findByName(String name, Pageable page);
	
	@Query("from User u")
	Page<User> load(Pageable page);

	@Cacheable
	User findByName(String name);

	User findByNameAndAge(String name, Integer age);
	@Transactional
	@Modifying
	@Query("UPDATE User u SET age=22 WHERE u.name=:name")
	void update(@Param("name") String name);

	@Query("from User u where u.name=:name")
	User findUser(@Param("name") String name);

}
