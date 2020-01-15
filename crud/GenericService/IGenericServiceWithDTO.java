package com.generic.crud.GenericService;

import java.util.List;

public interface IGenericServiceWithDTO<S,T> {    
    List<T> findAll();
    T save(T dto);
    T edit(T dto,Long id);
    T findById(Long id);
    S get(Long id);
    void delete(S dto);
    void deleteById(Long id);
}
