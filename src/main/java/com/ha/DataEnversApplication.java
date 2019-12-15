package com.ha;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ha.entity.Memo;
import com.ha.entity.User;
import com.ha.service.MemoService;
import com.ha.service.UserService;

@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class) 
//@EnableJpaAuditing
@SpringBootApplication
public class DataEnversApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataEnversApplication.class, args);
	}

	@Autowired
	private UserService userService;
	
	@Autowired
	private MemoService memoService;
	
	@Transactional
	@PostConstruct
	private void init() {
		User user = initUser();
		Memo m = initMemo(user);
//		user.getMemos().add(m);
		
		updateMemo(m.getId());
		updateUser(user.getId());
	}
	
	private User initUser() {
		User user = new User();
		user.setEmail("test@test.com");
		user.setUsername("테스트!");
		return userService.add(user);
	}
	
	private Memo initMemo(User user) {
		Memo m = new Memo();
		m.setTitle("t11");
		m.setRegisterUser(user);
		return memoService.post(m);
	}
	
	private void updateMemo(Long id) {
		memoService.update(id, "changeTitle");
	}
	
	private void updateUser(Long id) {
		userService.updateUsername(id, "changeUsername");
	}
}
