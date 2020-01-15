package com.generic.crud.paginacao;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DefaultPageable {
    private Integer size;
    
    private Integer page;
    
    private String sort;

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@JsonIgnore
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public PageRequest getPageable() {
		if(size == null) {
			size = 30;
		}
		if(page == null) {
			page = 0;
		}
		if(sort != null) {
			Sort sortBy = Sort.by(sort.substring(0,sort.indexOf(",")));
			if(sort.substring(sort.indexOf(",")+1 ,sort.length()).equals("asc")) {
				sortBy = sortBy.ascending();
			}
			else if(sort.substring(sort.indexOf(",")+1 ,sort.length()).equals("desc")) {
				sortBy = sortBy.descending();
			}
			System.out.println("Esse é o sort"+ sort.toString());
			return PageRequest.of(page, size,sortBy);
		}
		return PageRequest.of(page, size);
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
}
