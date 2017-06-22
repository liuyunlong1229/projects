package com.lyl.base.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.lyl.base.dao.IBaseDao;
import com.lyl.base.utils.PageBean;

public class BaseDaoImpl implements IBaseDao {

	private static final Logger	log	= Logger.getLogger(BaseDaoImpl.class);

	SessionFactory				sessionFactory;

	HibernateTemplate			hibernateTemplate;

	JdbcTemplate				jdbcTemplate;

	public void save(Object entity) {
		hibernateTemplate.save(entity);
	}

	public void delete(Object entity) {
		hibernateTemplate.delete(entity);
	}

	@Override
	public void deleteAll(List<Object> objs) {
		hibernateTemplate.deleteAll(objs);

	}

	@Override
	public void update(Object entity) {
		hibernateTemplate.update(entity);
	}

	@Override
	public <T> T getById(Class<T> clazz, Serializable id) {
		return (T) hibernateTemplate.get(clazz, id);
	}

	@Override
	public <T> List<T> findAll(Class<T> clazz) {
		DetachedCriteria ca = DetachedCriteria.forClass(clazz);
		return hibernateTemplate.findByCriteria(ca);

	}

	@Override
	public void execSQL(String sql) {
		jdbcTemplate.execute(sql);

	}

	@Override
	public List  findBySQL(String sql, Map<String, Object> args) {
		return findPageBySQL(sql, args, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List findPageBySQL(String sql, Map<String, Object> args, PageBean page) {
		Query query = getSesion().createSQLQuery(sql);
		setParameters(query, args);
		if (page != null) {
			page.setTotalCount(findRowCountBySQL(sql, args));
			query.setFirstResult(page.getStartIndex()).setMaxResults(page.getPageSize());
		}
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findByHQL(String hql, Map<String, Object> args) {
		return findPageByHQL(hql, args, null);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findPageByHQL(String hql, Map<String, Object> args, PageBean page) {
		Query query = getSesion().createQuery(hql);
		setParameters(query, args);
		if (page != null) {
			page.setTotalCount(findRowCountByHQL(hql, args));
			query.setFirstResult(page.getStartIndex()).setMaxResults(page.getPageSize());
		}
		return query.list();
	}

	private Integer findRowCountBySQL(String hql, Map<String, Object> args) {
		StringBuffer strBuff = new StringBuffer("select count(id) ");
		strBuff.append(hql.substring(hql.indexOf("from")));
		Query query = getSesion().createSQLQuery(strBuff.toString());
		setParameters(query, args);
		Object obj = query.uniqueResult();
		Integer result = Integer.valueOf(obj.toString());

		return result;
	}

	private Integer findRowCountByHQL(String hql, Map<String, Object> args) {
		StringBuffer strBuff = new StringBuffer("select count(id) ");
		strBuff.append(hql.substring(hql.indexOf("from")));
		Query query = getSesion().createQuery(strBuff.toString());
		setParameters(query, args);
		Object obj = query.uniqueResult();
		Integer result = Integer.valueOf(obj.toString());

		return result;
	}

	// Query接口通用赋值
	@SuppressWarnings({ "rawtypes" })
	private void setParameters(Query query, Map<String, Object> args) {
		if (null == args || 0 == args.size()) {
			return;
		}
		String name = null;
		Object value = null;

		Iterator<String> it = args.keySet().iterator();
		while (it.hasNext()) {
			name = it.next();
			value = args.get(name);
			log.debug("name==" + name + "\t" + "value==" + value);
			if (value instanceof Collection) {// 仅:命名参数支持Collection集合或数组
				query.setParameterList(name, (Collection) value);
			} else if (value instanceof Object[]) {
				query.setParameterList(name, (Object[]) value);
			} else {
				query.setParameter(name, value);
			}
		}
	}

	private Session getSesion() {
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
