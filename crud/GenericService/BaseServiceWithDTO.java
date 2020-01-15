package com.generic.crud.GenericService;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.generic.crud.Exception.ObjectNotFoundException;



abstract public class BaseServiceWithDTO<S,T> implements IGenericServiceWithDTO<S,T>  {

	@Autowired
	private JpaRepository<S,Long> repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public S preSave(S entity) {
		return entity;
	}
	
	public S preUpdate(S entity) {
		return entity;
	}
	
	
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return (List<T>) repository.findAll().stream().map(entity -> 
		modelMapper.map(entity, new TypeToken<T>() {}.getType())).collect(Collectors.toList());
	}

	@Override
	public T save(T dto) {
		S entity = modelMapper.map(dto,new TypeToken<S>() {}.getType());
		preSave(entity);
		return modelMapper.map(repository.save(entity),new TypeToken<T>() {}.getType());
	}

	@Override
	public T findById(Long id) {
		S entity = get(id);
		return modelMapper.map(entity, new TypeToken<T>() {}.getType());
	}

	@Override
	public void delete(S entity) {
		repository.delete(entity);
		
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public T edit(T dto, Long id) {
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		S entity = get(id);
		modelMapper.map(dto,entity);
		preUpdate(entity);
		return modelMapper.map(repository.save(entity),new TypeToken<T>() {}.getType());
	}

	@Override
	public S get(Long id) {
		return repository.findById(id).orElseThrow(() -> 
		new ObjectNotFoundException("id de"+new TypeToken<S>() {}.getClass().getName()+" não encontrado"));
		
	}



}
