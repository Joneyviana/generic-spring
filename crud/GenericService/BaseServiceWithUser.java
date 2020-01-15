package com.generic.crud.GenericService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.generic.crud.Exception.ObjectNotFoundException;
import com.generic.crud.paginacao.HasUser;
import com.security.auth.User;
import com.security.auth.UserRepository;

public class BaseServiceWithUser<T extends HasUser> extends BaseService<T> {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> 
		new ObjectNotFoundException("Usuario não encontrado"));
		return user;
	}
	
	@Override
	public T save(T entity) {
		entity.setUser(getCurrentUser());
		return super.save(entity);
	}
	
	private Specification<T> getUserSpecification(Long userId){
		return (root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.equal(root.get("user").get("id"), userId);
	}
	
   public List<T> findByUser(){
	   return super.repository.findAll(getUserSpecification(getCurrentUser().getId()));
   }
   
   public List<T> searchByUser(String search){
	   if(search != null) {
		      return repository.findAll(getSearchSpecification(search)
		    		  .and(getUserSpecification(getCurrentUser().getId())));
		   }
		   return findByUser();
   }

}
