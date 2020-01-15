package com.generic.crud.GenericController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.generic.crud.GenericService.BaseServiceWithDTO;


public class BaseControllerWithDTO<S,T> {
	
	@Autowired
	private BaseServiceWithDTO<S,T> baseService;
	
	@GetMapping("/")
	public List<T> list(){
		return baseService.findAll();
	}
	
	@GetMapping("/{id}")
	public T getById(@PathVariable Long id) {
		return baseService.findById(id);
	}
	
	@PostMapping("/")
	public T save(@Valid @RequestBody T empresaDTO) {
		return baseService.save(empresaDTO);
	}
	
	@PutMapping("/{id}")
	public T edit(@PathVariable Long id,@Valid @RequestBody T empresaDTO) {
		return baseService.edit(empresaDTO, id);
	}
	
	@PatchMapping("/{id}")
	public T editPatch(@PathVariable Long id,@RequestBody T empresaDTO) {
		return baseService.edit(empresaDTO, id);
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<?> delete(@PathVariable Long id){
		baseService.deleteById(id);
		return new ResponseEntity<>(null,HttpStatus.OK);
	}

}
