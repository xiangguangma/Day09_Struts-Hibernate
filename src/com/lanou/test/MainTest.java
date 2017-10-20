package com.lanou.test;

import com.lanou.dao.DepartmentDao;
import com.lanou.dao.PostDao;
import com.lanou.dao.StaffDao;
import com.lanou.dao.impl.DepartmentDaoImpl;
import com.lanou.dao.impl.PostDaoImpl;
import com.lanou.dao.impl.StaffDaoImpl;
import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.lanou.domain.Staff;
import com.lanou.service.DepartmentService;
import com.lanou.service.impl.DepartmentServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/20.
 */
public class MainTest {

    private SessionFactory sessionFactory;

    @Before
    public void init(){
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    /**
     *  插入数据
     */
    @Test
    public void save(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Department department = new Department("教学部");
        Post post = new Post("教学总监");
        Post post1 = new Post("Java主管");
        Post post2 = new Post("Java讲师");

        department.getPosts().add(post);
        department.getPosts().add(post1);
        department.getPosts().add(post2);

        session.save(department);  // 保存教学部

        Department department1 = new Department("职规部");
        Post post3 = new Post("职规主管");
        Post post4 = new Post("班主任");

        department1.getPosts().add(post3);
        department1.getPosts().add(post4);

        session.save(department1); // 保存职规部

        Staff staff = new Staff("大表姐");
        staff.setPost(post2); // Java讲师
        staff.setDepartment(department);
        session.save(staff); // 保存

        Staff staff1 = new Staff("欣欣姐");
        staff1.setPost(post4);
        staff1.setDepartment(department1);
        session.save(staff1);

        transaction.commit();


    }


    /**
     *  dao 层的单元测试
     */
    @Test
    public void testDao(){
        //查询部门集合
        DepartmentDao departmentDao = new DepartmentDaoImpl();
        List<Department> departments = departmentDao.findAll("from Department");

        for (Department department : departments) {
            System.out.println(department);
        }

        // 查询职务表中的所有集合
        PostDao postDao = new PostDaoImpl();
        List<Post> posts = postDao.find("from Post", null);

        for (Post post : posts) {
            System.out.println(post);
        }

        //查询员工表中的所有集合
        StaffDao staffDao = new StaffDaoImpl();
        List<Staff> staffs = staffDao.find("from Staff", null);
        for (Staff staff : staffs) {
            System.out.println(staff);
        }

        // 查询名字叫 大表姐的员工
        String hql = "from Staff where sname=:name";
        Map<String, Object> params = new HashMap<>();
        params.put("name", "大表姐");

        Staff staff = staffDao.findSingle(hql, params);
        System.out.println(staff);
    }

    @Test
    public void testService(){

        DepartmentService departmentService = new DepartmentServiceImpl();
        List<Department> departments = departmentService.findAll();
        for (Department department : departments) {
            System.out.println("基础属性: " + department);

            for (Post post : department.getPosts()) {
                System.out.println("职务: " + post);
            }
        }
    }
}
