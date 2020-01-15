package com.generic.crud.GenericService;

import java.util.List;

public interface IGenericService<S> {
	List<S> findAll();
    S save(S dto);
    S edit(S dto,Long id);
    S findById(Long id);
    S get(Long id);
    void delete(S dto);
    void deleteById(Long id);
}
