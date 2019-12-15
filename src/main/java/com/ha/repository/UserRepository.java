package com.ha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.ha.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, RevisionRepository<User, Long, Long>{

}
