package com.lanou.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/20.
 */
public interface BaseDao<T> {

    /**
     *  根据主键 id 查询某个对象
     * @param id        要查询的主键id
     * @param tClass    返回对象的类声明
     * @return
     */
    T findById(Serializable id, Class<T> tClass);

    /**
     * 查询所有
     * @return
     */
    List<T> findAll(String hql);

    /**
     *  根据条件查询, 返回查询到的结果集合
     * @param hql    查询语句
     * @param params 参数列表
     * @return 查询到的结果集合
     */
    List<T> find(String hql, Map<String, Object> params);

    /**
     *  根据条件查询, 返回查询到的一个对象
     * @param hql    查询语句
     * @param params 参数列表
     * @return 查询到的结果集合
     */
    T findSingle(String hql, Map<String, Object> params);



}
