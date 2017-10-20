package com.lanou.dao;

import com.lanou.domain.Department;

import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
public interface DepartmentDao extends BaseDao<Department> {

    /**
     *  根据id查询部门
     * @param departId
     * @return
     */
    Department findById(int departId);
}
