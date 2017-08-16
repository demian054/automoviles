package com.automovil.fabric.dao;

import java.io.Serializable;
import java.util.List;

import com.automovil.fabric.beans.Automovil;

public interface DaoBasic<T> {
	
	T getById(Serializable id);
	
	boolean save(T object);
	
	boolean update(T object);
	
	boolean delete(T object);
	
	List<T> findAll();
	
}
