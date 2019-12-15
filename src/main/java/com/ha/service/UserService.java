package com.ha.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ha.common.RevisionQuery;
import com.ha.entity.User;
import com.ha.repository.UserRepository;
import com.ha.vo.RevisionWithMeta;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RevisionQuery revisionQuery;
	
	@Transactional
	public User add(User user) {
		return repository.save(user);
	}
	
	public User get(Long id) {
		return repository.findById(id).get();
	}
	
	@Transactional
	public void updateUsername(Long id, String username) {
		User u = get(id);
		u.setUsername(username);
	}
	
	public List<?> getRevisions(){
		List<User> users = revisionQuery.getRevisions(User.class);
		return users;
	}
	
	public List<RevisionWithMeta<User>> getRevisionsWithMeta(){
		List<RevisionWithMeta<User>> users = revisionQuery.getRevisionsWithMeta(User.class);
		return users;
	}
}
