package com.example;

import java.util.List;

public interface CrudService<E> {

	public <T> List<E> findAll();
	public <T> Object findOne(Integer id);
	public <T> Object save(E entity);
	public void delete(E entity);
	
}
