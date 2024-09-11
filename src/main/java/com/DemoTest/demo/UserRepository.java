package com.DemoTest.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.DemoTest.demo.Entity.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

}