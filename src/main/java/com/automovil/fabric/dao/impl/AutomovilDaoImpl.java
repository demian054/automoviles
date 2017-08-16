package com.automovil.fabric.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl.CriterionEntry;

import com.automovil.fabric.beans.Automovil;
import com.automovil.fabric.beans.Opcional;
import com.automovil.fabric.dao.AutomovilDao;
import com.automovil.fabric.dao.hibernate.impl.DaoBasicHibernateImpl;


public class AutomovilDaoImpl extends DaoBasicHibernateImpl<Automovil> implements AutomovilDao {
	
	@Override
	public Automovil getAutomovilById(long id) {
		return getById(id);		
	}
	
	@Override
	public boolean saveAutomovil(Automovil automovil) {
		return save(automovil);
	}
	
	@Override
	public boolean updateAutomovil(Automovil automovil) {
		return update(automovil);
	}
	
	@Override
	public boolean deleteAutomovil(Automovil automovil) {
		return delete(automovil);
	}
	
	@Override
	public List<Automovil> findAllAutomovils() {
		return findAll();		
	}

	@Override
	public boolean existAutomovil(Automovil automovil) {
		try {
			
			Criteria criteria = getSession().createCriteria(Automovil.class);
			criteria.add(Restrictions.or(
					Restrictions.eq("id", automovil.getId()),
					Restrictions.eq("nombre", automovil.getNombre())
			))
			.setProjection(Projections.rowCount());
			return ((Long) criteria.uniqueResult() > 0L);
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		} finally {
			closeSession();
		}
	}
	
	
}
