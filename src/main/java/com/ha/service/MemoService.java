package com.ha.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ha.common.RevisionQuery;
import com.ha.commonp.domain.DomainMapper;
import com.ha.entity.Memo;
import com.ha.repository.MemoRepository;
import com.ha.vo.RevisionWithMeta;

@Service
public class MemoService {
	
	@Autowired
	private MemoRepository memoRepository;
	
	@Autowired
	private RevisionQuery revisionQuery;
	
	@Autowired
	private DomainMapper domainMapper;
	
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
	
	@Transactional 
	public void update(Long id, String title){
		Memo m = get(id);
		m.setTitle(title);
	}
	
	private ModelMapper modelMapper = new ModelMapper();
	
	/**
	 * @return List<Object[]>
	 * not Serialize
	 * */
//	List list = revisionQuery
//			.createAuditReader()
//			.createQuery()
//			.forRevisionsOfEntity(Memo.class, true, true)
//			.add(AuditEntity.property("title").eq(""))
//			.add(AuditEntity.revisionType().eq(RevisionType.MOD))
//			.setFirstResult(0)
//			.setMaxResults(10)
//			.addOrder(AuditEntity.property("title").desc()).getResultList();
	public List<com.ha.response.Memo> revision() {
		List<Memo> list = revisionQuery.getRevisions(Memo.class);
		List<com.ha.response.Memo> res = new ArrayList<>();
		for(Object obj: list) {
			com.ha.response.Memo memo = domainMapper.convertToDomain(obj, com.ha.response.Memo.class);
			res.add(memo);
		} 
		return res;
	}
	
	public List<RevisionWithMeta<com.ha.response.Memo>> revisionWithMeta(){
		return revisionQuery.getRevisionsWithMeta(Memo.class, com.ha.response.Memo.class);
	}
}
