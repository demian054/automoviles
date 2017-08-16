package com.automovil.fabric.service;


import java.util.List;
import com.automovil.fabric.beans.Automovil;

public interface AutomovilService {
	
	Automovil getById(long id);
	
	boolean saveAutomovil(Automovil automovil);
	
	boolean updateAutomovil(Automovil automovil);
	
	boolean deleteAutomovil(Automovil automovil);
	
	List<Automovil> findAll();
	
	boolean existAutomovil(Automovil automovil);

}
