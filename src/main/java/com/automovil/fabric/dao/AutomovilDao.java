package com.automovil.fabric.dao;


import java.util.List;
import com.automovil.fabric.beans.Automovil;

public interface AutomovilDao {
	
	Automovil getAutomovilById(long id);
	
	boolean saveAutomovil(Automovil automovil);
	
	boolean updateAutomovil(Automovil automovil);
	
	boolean deleteAutomovil(Automovil automovil);
	
	List<Automovil> findAllAutomovils();
	
	boolean existAutomovil(Automovil automovil);
	

}
