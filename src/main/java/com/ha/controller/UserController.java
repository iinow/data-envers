package com.ha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ha.response.DefaultResponse;
import com.ha.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "/revisions")
	public DefaultResponse getRevisions(
			@RequestParam(name = "withMeta", defaultValue = "false") boolean withMeta){
		return DefaultResponse.builder()
				.success(true)
				.data(withMeta ? userService.getRevisionsWithMeta() : userService.getRevisions())
				.build();
	}
}
