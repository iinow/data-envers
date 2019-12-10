package com.ha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.ha.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long>, RevisionRepository<Memo, Long, Integer>{

}
