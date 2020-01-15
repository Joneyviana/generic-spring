package com.generic.crud.GenericService;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.generic.crud.Exception.ObjectNotFoundException;
import com.generic.crud.GenericRepository.GenericRepository;
import com.generic.crud.rsql.CustomRsqlVisitor;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

public class BaseService<S>  implements IGenericService<S>{
	
	@Autowired
	protected GenericRepository<S> repository;
	
	public S preSave(S entity) {
		return entity;
	}
	
	public S preUpdate(S entity) {
		return entity;
	}
	
	public S postUpdate(S entity) {
		return entity;
	}
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public List<S> findAll() {
		return repository.findAll();
	}
	
   protected Specification<S> getSearchSpecification(String search){
	   Node rootNode = new RSQLParser().parse(search);
	   return rootNode.accept(new CustomRsqlVisitor<S>());
   }
	
   public List<S> search(String search){
	   if(search != null) {
	      return repository.findAll(getSearchSpecification(search));
	   }
	   return repository.findAll();
	}
   
	@Override
	public S save(S entity) {
		preSave(entity);
		return repository.save(entity);
	}

	@Override
	public S findById(Long id) {
		S entity = get(id);
		return entity;
	}

	@Override
	public void delete(S entity) {
		repository.delete(entity);
		
	}
	
	public Long count() {
		return repository.count();
	}
	

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public S edit(S entityUpdate, Long id) {
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		S entity = get(id);
		preSave(entity);
		preUpdate(entity);
		modelMapper.map(entityUpdate,entity);
		entity = repository.save(entity);
		postUpdate(entity);
		return entity;
	}

	@Override
	public S get(Long id) {
		return repository.findById(id).orElseThrow(() -> 
		new ObjectNotFoundException("Recurso não encontrado"));
		
	}

}
