package com.automovil.fabric.service;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.automovil.fabric.beans.Automovil;
import com.automovil.fabric.dao.AutomovilDao;


public class AutomovilServiceImpl implements AutomovilService{
	
	@Autowired
	private AutomovilDao automovilDao;
		
	public void setAutomovilDao(AutomovilDao automovilDao) {
		this.automovilDao = automovilDao;
	}
	
	@Override
	public Automovil getById(long id) {
		Automovil automovil = automovilDao.getAutomovilById(id);
		if (automovil!=null) {
			automovil.getPrecioTotal();
		}
		return automovil;
	
	}
	
	@Override
	public boolean saveAutomovil(Automovil automovil) {
		return automovilDao.saveAutomovil(automovil);
	}
	
	@Override
	public boolean updateAutomovil(Automovil automovil) {
		return automovilDao.updateAutomovil(automovil);
	}
	
	@Override
	public boolean deleteAutomovil(Automovil automovil) {
		return automovilDao.deleteAutomovil(automovil);
	}
	
	@Override
	public List<Automovil> findAll() {
		List<Automovil> listAutomovils = automovilDao.findAllAutomovils();
		for(Automovil automovil: listAutomovils ) {
			automovil.getPrecioTotal();
		}
		return listAutomovils;		
	}

	@Override
	public boolean existAutomovil(Automovil automovil) {
		return automovilDao.existAutomovil(automovil);
	}
	
	
}
