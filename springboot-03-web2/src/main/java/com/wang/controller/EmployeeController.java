package com.wang.controller;

import com.wang.dao.DepartmentDao;
import com.wang.dao.EmployeeDao;
import com.wang.pojo.Department;
import com.wang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
                                 
import java.util.Collection;
                 
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }
    @GetMapping("/emp")
    public String toAdd(Model model) {
        // 查出部门所有信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments );
        return "/emp/add";
    }               

    @PostMapping("/emp")
    public String Add(Employee employee) {
        System.out.println("employee==>" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toUpdate(@PathVariable("id") int id,Model model) {
        Employee employee = employeeDao.getEmployeeId(id);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("emp",employee);
        model.addAttribute("departments",departments);
        return "/emp/update";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }                     

    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable("id") int id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }


}
