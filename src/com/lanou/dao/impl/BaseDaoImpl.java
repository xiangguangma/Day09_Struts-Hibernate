package com.lanou.dao.impl;

import com.lanou.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/20.
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public T findById(Serializable id, Class<T> tClass) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // 根据主键 id 查询某个对象
        T t = session.get(tClass, id);

        transaction.commit();
        return t;
    }

    @Override
    public List<T> findAll(String hql) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // 执行查询语句
        Query query = session.createQuery(hql);
        List<T> tList = query.list();

        transaction.commit();
        return tList; // 返回查询结果
    }

    @Override
    public List<T> find(String hql, Map<String, Object> params) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // 执行查询语句
        Query query = session.createQuery(hql);

        // 设置参数
        if (params != null && !params.isEmpty()){
            //  遍历参数
            for (String key : params.keySet()) {
                // 设置查询语句中的key 与 Value
                query.setParameter(key, params.get(key));
            }
        }

        List<T> tList = query.list();

        transaction.commit();
        return tList; // 返回查询结果
    }

    @Override
    public T findSingle(String hql, Map<String, Object> params) {
        // 调用条件查询
        List<T> tList = find(hql, params);
        if (tList.size() > 0){
            return tList.get(0);  // 返回查询结果集的第一个对象
        }
        return null; // 查询结果集为空时返回 null
    }
}
