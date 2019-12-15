package com.ha.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.order.AuditOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ha.commonp.domain.DomainMapper;
import com.ha.entity.RevisionInfo;
import com.ha.vo.RevisionWithMeta;

@Component
public class RevisionQuery {
	
	@Autowired
	private DomainMapper domainMapper;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private AuditReader createAuditReader() {
		return AuditReaderFactory.get(entityManager);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getRevisions(Class<?> classTemplate){
		return createAuditReader()
			.createQuery()
			.forRevisionsOfEntity(classTemplate, true, true)
			.getResultList();
	}
	
	/**
	 * @return List<Object[]>
	 * */
	@SuppressWarnings("unchecked")
	public <T> List<RevisionWithMeta<T>> getRevisionsWithMeta(Class<T> classTemplate){
		return (List<RevisionWithMeta<T>>) createAuditReader()
			.createQuery()
			.forRevisionsOfEntity(classTemplate, false, true)
//			.addOrder(AuditEntity.property)
			.getResultList()
			.stream()
			.map(objs -> {
				RevisionWithMeta<T> model = new RevisionWithMeta<>();
				for(Object obj: (Object[])objs) {
					if(obj instanceof org.hibernate.envers.RevisionType) {
						org.hibernate.envers.RevisionType t = (org.hibernate.envers.RevisionType)obj;
						model.setRevType(RevisionType.findRevisionType(t));
					}else if(obj instanceof RevisionInfo) {
						continue;
					}else {
						model.setValue((T)obj);
					}
				}
				return model;
			}).collect(Collectors.toList());
	}
	
	/**
	 * <T> Target Class,
	 * <E> Endpoint Class
	 * */
	public <T, E> List<RevisionWithMeta<E>> getRevisionsWithMeta(Class<T> targetClass, Class<E> endClass){
		List<RevisionWithMeta<T>> res = getRevisionsWithMeta(targetClass);
		return res.stream()
			.map(meta -> {
				E end = domainMapper.convertToDomain(meta.getValue(), endClass);
				RevisionWithMeta<E> m = new RevisionWithMeta<>();
				m.setValue(end);
				m.setRevType(meta.getRevType());
				return m;
			}).collect(Collectors.toList());
	}
}
