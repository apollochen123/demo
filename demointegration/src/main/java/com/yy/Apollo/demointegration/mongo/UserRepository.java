package com.yy.Apollo.demointegration.mongo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.yy.Apollo.demointegration.mongo.entity.User;

public interface UserRepository extends MongoRepository<User, String>,UserMongoTemplate<User> {
    void deleteById(String id);

    List<String> deleteByName(String name);

    List<User> findByNameAndPhone(String name, String phone);

    /**
     * Distinct + or
     */
    List<User> findDistinctUserByNameOrPhone(String name, String phone);

    /**
     * IgnoreCase
     * 
     * @param name
     * @return
     */
    List<User> findByNameIgnoreCase(String name);

    List<User> findByNameAndPhoneAllIgnoreCase(String lastname, String firstname);

    /**
     * order
     */
    List<User> findByNameOrderByPhoneAsc(String name);

    /**
     * A.B式变量查询
     */
    List<User> findByAddress_Home(String home);

    /**
     * Pageable
     */
    Page<User> findByName(String name, Pageable pageable);

    // List<User> findByName(String name, Pageable pageable);

    List<User> findByName(String name, Sort sort);

}
