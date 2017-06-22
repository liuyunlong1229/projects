package com.lyl.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.lyl.base.utils.PageBean;

/**
 * DAO上层接口定义，包含基本实体操作
 * 	
*/
public interface IBaseDao {
	/**
	 * 保存实体对象
	 * @param entity
	 * @return
	 */
	public abstract void save(Object entity);

	/**
	 * 更新实体对象 
	 * @param entity
	 * @return
	 */
	public abstract void update(Object entity);

	/**
	 * 删除实体
	 * @param entity
	 */
	public abstract void delete(Object obj);

	/***
	 * 批量删除
	 */

	public abstract void deleteAll(List<Object> objs);

	/**
	 * 执行sq
	 * @param sql语句
	 */
	public abstract void execSQL(String sql);

	/**
	 * 根据ID获得实体
	 * @param <T>
	 * @param id
	 * @return
	 */
	public abstract <T> T getById(Class<T> clazz, Serializable id);

	/**
	 * 根据类对象查询所有
	 * @param <T>
	 */

	public abstract <T> List<T> findAll(Class<T> clazz);

	/**
	 * 根据配置参数的本地sql查询结果集
	 * 
	 * @param sql
	 * @param paramsMap 参数集合
	 * @return 返回数组集合
	 */
	public abstract List findBySQL(String sql, Map<String, Object> args);

	/**
	 * 根据配置参数的本地sql查询结果集
	 * 
	 * @param sql
	 * @param args 参数Map,没有则传入NULL
	 * @param page 分页参数，没有则传入NULL
	 * @return 返回数组集合
	 */
	public abstract List findPageBySQL(String sql, Map<String, Object> args, PageBean page);

	/**
	 * 根据hql查找实体对象集合
	 * @param hql 
	 * @param args 参数Map,没有则传入NULL
	 * @return 返回实体对象集合
	 */

	@SuppressWarnings("rawtypes")
	public abstract List findByHQL(String hql, Map<String, Object> args);

	/**
	 * @param hql
	 * @param args 传入的参数Map，没有则传入NULL
	 * @param page 分页参数，没有则传入NULL
	 * @return 对象集合
	 */
	@SuppressWarnings("rawtypes")
	public abstract List findPageByHQL(String hql, Map<String, Object> args, PageBean page);

}
