package com.automovil.fabric.dao.hibernate.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.automovil.fabric.beans.Automovil;
import com.automovil.fabric.dao.DaoBasic;

public class DaoBasicHibernateImpl<T> implements DaoBasic<T> {
	
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public DaoBasicHibernateImpl(){
		ParameterizedType p = (ParameterizedType) getClass().getGenericSuperclass();
		clazz = (Class<T>)p.getActualTypeArguments()[0];
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	
	public Session getSession() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		return session;
	}
	
	public void closeSession() {
		try {
			session.flush();
	        session.clear();
	        transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		
	}
	
	@SuppressWarnings("unchecked")
	public T getById(Serializable id){
		try {			
			return (T) getSession().get(clazz, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeSession();
		}
	}
	
	@Override
	public boolean save(T object) {
		try {
			getSession().saveOrUpdate(object);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeSession();
		}
	}
	
	@Override
	public boolean update(T object) {
		try {
			getSession().update(object);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeSession();
		}
	}
	
	@Override
	public boolean delete(T object) {
		try {
			getSession().delete(object);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeSession();
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		try {
			return (List<T>) getSession().createCriteria(clazz)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.addOrder(Order.asc("id")).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeSession();
		}
		
	}
}
