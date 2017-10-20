package com.lanou.action;

import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.lanou.service.DepartmentService;
import com.lanou.service.impl.DepartmentServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.util.List;
import java.util.Set;

/**
 * Created by dllo on 17/10/20.
 */
public class StaffAction extends ActionSupport {

    private String departId;  // 二级联动中的部门id
    private Set<Post> postList;

    @Override
    public String execute() throws Exception {
        /*
            跳转到员工列表首页时, 需要进行的操作
            1. 获取所有员工
            2. 获取所有部门
         */

        DepartmentService departmentService = new DepartmentServiceImpl();

        // 获取部门集合
        List<Department> departments = departmentService.findAll();

        // 将部门集合放入 Request 对象的属性中
        ServletActionContext.getRequest().setAttribute("departments", departments);


        return SUCCESS;
    }

    /**
     *  查询部门所对应的职务
     * @return
     */
    public String showPost(){

        System.out.println("选中的部门ID: " + departId);

        // 通过部门id查找当前选中的部门对象
        DepartmentService departmentService = new DepartmentServiceImpl();
        Department selectDepart = departmentService.findById(Integer.parseInt(departId));


        // 从部门对象中获取选中的职务集合
        postList = selectDepart.getPosts();



        return SUCCESS;
    }

    public Set<Post> getPostList() {
        return postList;
    }

    public void setPostList(Set<Post> postList) {
        this.postList = postList;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

}
