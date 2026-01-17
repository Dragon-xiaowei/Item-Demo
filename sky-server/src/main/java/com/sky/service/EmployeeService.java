package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    //新增员工
    void addEmployee(EmployeeDTO employeeinfo);

    //分页查询
    PageResult SearchEmployee(EmployeePageQueryDTO employeePageQueryDTO);

    //修改账号状态,启用禁用
    void update(Integer statue, Long id);

    //修改员工信息,1.查询回显2.更新数据
    Employee selectbyid(Long id);
    void updateEmpInfo(EmployeeDTO employeeDTO);



}
