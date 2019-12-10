package com.ha.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ha.entity.Memo;
import com.ha.repository.MemoRepository;

@RequestMapping(value = "/memos")
@RestController
class MemoController {
	
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
	public ResponseEntity<?> revisions(){
		return ResponseEntity.ok(service.revision());
	}
}

@Service
class MemoService {
	
	@Autowired
	private MemoRepository memoRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Memo get(Long id) {
		return memoRepository.findById(id).get();
	}
	
	@Transactional
	public Memo post(Memo m) {
		return memoRepository.save(m);
	}
	
	@Transactional
	public Memo update(Memo m, Long id) {
		Memo memo = get(id);
		memo.setTitle(m.getTitle());
		return memo;
	}
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public List revision() {
		AuditReader reader = AuditReaderFactory.get(entityManager);
		List list = reader.createQuery()
			.forRevisionsOfEntity(Memo.class, true, true)
//			.add(AuditEntity.property("title").eq(""))
//			.add(AuditEntity.revisionType().eq(RevisionType.MOD))
//			.setFirstResult(0)
//			.setMaxResults(10)
//			.addOrder(AuditEntity.property("title").desc())
			.getResultList();
		return list;
	}
}
