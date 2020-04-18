package com.euro.currencyApi.conversion.Dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.euro.currencyApi.conversion.entity.CurrencyConversionEntity;

@Repository
public class CurrencyHistoryDaoImpl implements CurrencyHistoryDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public boolean saveHistioryData(List<CurrencyConversionEntity> currencyList)
	{
		try
		{
		Session session =sessionFactory.getCurrentSession();
		for( CurrencyConversionEntity ent : currencyList )
		{
			session.save(ent);
			
			System.out.println("in dao ---------------------");
		}
		}
		catch( Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
		
	}

	@Override
	@Transactional
	public Long getHisId() {
		String hql =" select max(id) from CurrencyConversionEntity";
		Long id = null;
		try
		{
		Session session =sessionFactory.getCurrentSession();
		 id =(Long) session.createQuery(hql).list().get(0);
		}
		catch( HibernateException e)
		{
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	@Transactional
	public List<BigDecimal> getCurrencyHistDetails(String base) {
		
		List<BigDecimal> entityList = null;
		try
		{
		Session session =sessionFactory.getCurrentSession();
		Query q = session.createQuery("select rate from CurrencyConversionEntity where base = :base");
		entityList = q.setParameter("base", base).list();
		}
		catch( HibernateException e)
		{
			e.printStackTrace();
		}
		
		return entityList;
	}

}
