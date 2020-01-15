package com.generic.crud.GenericController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.generic.crud.GenericService.BaseService;
import com.generic.crud.GenericService.BaseServiceWithUser;
import com.generic.crud.paginacao.HasUser;

public class BaseControllerWithUser<S extends HasUser>{
	
	@Autowired
	private BaseServiceWithUser<S> baseService;
	
	@GetMapping("/")
	public List<S> list(){
		return baseService.findAll();
	}
	
	@GetMapping("/{id}")
	public S getById(@PathVariable Long id) {
		return baseService.findById(id);
	}
	
	@GetMapping("/myList")
	public List<S> listByUser(){
		return baseService.findByUser();
	}
	
	@GetMapping("/searchMyList")
	public List<S> searchByUser(@RequestParam(value = "search",required=false) String search) {
		return baseService.searchByUser(search);
	}
	
	
	@GetMapping("/search")
	public List<S> search(@RequestParam(value = "search",required=false) String search) {
		return baseService.search(search);
	}
	
	@PostMapping("/")
	public S save(@Valid @RequestBody S empresaDTO) {
		return baseService.save(empresaDTO);
	}
	
	@PutMapping("/{id}")
	public S edit(@PathVariable Long id,@Valid @RequestBody S empresaDTO) {
		return baseService.edit(empresaDTO, id);
	}
	
	@PatchMapping("/{id}")
	public S editPatch(@PathVariable Long id,@RequestBody S empresaDTO) {
		return baseService.edit(empresaDTO, id);
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<?> delete(@PathVariable Long id){
		baseService.deleteById(id);
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
}
