package com.ha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ha.entity.Memo;
import com.ha.response.DefaultResponse;
import com.ha.service.MemoService;

@RequestMapping(value = "/memos")
@RestController
public class MemoController {
	
	@Autowired
	private MemoService service;

	@GetMapping("/{id}")
	public Memo get(@PathVariable(required = true, name = "id") Long id) {
		return service.get(id);
	}
	
	@PostMapping("")
	public Memo post(@RequestBody Memo m) {
		return service.post(m);
	}
	
	@PatchMapping("/{id}")
	public Memo patch(@PathVariable(required = true, name = "id") Long id, @RequestBody Memo m) {
		return service.update(m, id);
	}
	
	@GetMapping("/revisions")
	public DefaultResponse revisions(
			@RequestParam(name = "withMeta", defaultValue = "false") boolean withMeta){
		return DefaultResponse.builder()
				.success(true)
				.data(withMeta ? service.revisionWithMeta() : service.revision())
				.build();
	}
}