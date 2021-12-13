package com.wang.dao;

import com.wang.pojo.Department;
import com.wang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
                                   
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
// 员工   dao
public class EmployeeDao {
    // 模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;
    // 员工所属的部门
    @Autowired
    private DepartmentDao departmentDao;
    static {
        employees = new HashMap<Integer, Employee>();

        employees.put(1001,new Employee(1001,"AA","A2375847849@qq.com",1,new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,"BB","B2375847849@qq.com",0,new Department(102,"市场部")));
        employees.put(1003,new Employee(1003,"CC","C2375847849@qq.com",1,new Department(103,"教研部")));
        employees.put(1004,new Employee(1004,"DD","D2375847849@qq.com",0,new Department(104,"运营部")));
        employees.put(1005,new Employee(1005,"EE","E2375847849@qq.com",1,new Department(105,"后勤部")));
    }


    // 主键自增
    private static Integer initId = 1006;
    // 增加员工
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));

        employees.put(employee.getId(),employee);
    }

    // 查看所有用户
    public Collection<Employee> getAll() {
        return employees.values();
    }

    // 通过id查询员工
    public Employee getEmployeeId(Integer id) {
        return employees.get(id);
    }

    // 删除指定用户
    public void delete(Integer id) {
        employees.remove(id);
    }
}
