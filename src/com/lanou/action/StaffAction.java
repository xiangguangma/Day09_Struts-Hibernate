package com.lanou.action;

import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.lanou.domain.Staff;
import com.lanou.service.DepartmentService;
import com.lanou.service.PostService;
import com.lanou.service.StaffService;
import com.lanou.service.impl.DepartmentServiceImpl;
import com.lanou.service.impl.PostServiceImpl;
import com.lanou.service.impl.StaffServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.util.List;
import java.util.Set;

/**
 * Created by dllo on 17/10/20.
 */
public class StaffAction extends ActionSupport {

    private DepartmentService departmentService = new DepartmentServiceImpl();
    private PostService postService = new PostServiceImpl();
    private StaffService staffService = new StaffServiceImpl();


    private String departId;  // 二级联动中的部门id
    private Set<Post> postList;
    private String postId;
    private String department;
    private String post;

    private List<Department> departmentList;
    private List<Post> posts;
    private List<Staff> staffList;


    @Override
    public String execute() throws Exception {
        /*
            跳转到员工列表首页时, 需要进行的操作
            1. 获取所有员工
            2. 获取所有部门
         */


        // 获取部门集合
        List<Department> departments = departmentService.findAll();

        // 将部门集合放入 Request 对象的属性中
        ServletActionContext.getRequest().setAttribute("departments", departments);


        return SUCCESS;
    }

    /**
     *  JQ 写法 获取部门
     * @return
     */
    public String showDepartment(){
        departmentList = departmentService.findAll();
        return SUCCESS;
    }



    /**
     *  查询部门所对应的职务
     * @return
     */
    public String showPost(){

        System.out.println("选中的部门ID: " + departId);

        // 通过部门id查找当前选中的部门对象
        Department selectDepart = departmentService.findById(Integer.parseInt(departId));

        // 从部门对象中获取选中的职务集合
        postList = selectDepart.getPosts();

        return SUCCESS;
    }

    public String showStaff(){
        System.out.println(department);
        System.out.println(post);

        staffList = staffService.find(department, post);
        for (Staff staff : staffList) {
            System.out.println(staff);
        }
        ActionContext.getContext().getApplication().put("staffList", staffList);
        return SUCCESS;
    }

    public String findStaff(){
        System.out.println(department);
        System.out.println(post);

        List<Staff> staffs = staffService.find(department, post);
        for (Staff staff : staffs) {
            System.out.println(staff);
        }

        ActionContext.getContext().getApplication().put("staffs", staffs);

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

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }
}
