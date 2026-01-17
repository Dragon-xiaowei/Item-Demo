package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对,
        //对前端传来的明文密码进行加密
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    @Override
    public void addEmployee(EmployeeDTO employeeinfo) {

        //数据完善
        Employee employee = new Employee();
        //对象属性拷贝
        BeanUtils.copyProperties(employeeinfo,employee);
        //设置账号状态,0锁定,1正常
        employee.setStatus(StatusConstant.ENABLE);
        //设置账号密码 默认123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        //设置创建时间修改时间为当前时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        //设置当前记录创建人和修改人
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());

        //数据校验
        employeeMapper.addEmployee(employee);

    }

    @Override
    public PageResult SearchEmployee(EmployeePageQueryDTO employeePageQueryDTO) {
       //分页插件,分页查询
        PageHelper.startPage(employeePageQueryDTO.getPage(),employeePageQueryDTO.getPageSize());
        Page<Employee> emps =  employeeMapper.SearchEmployee(employeePageQueryDTO);

        //获取总条数
        long total = emps.getTotal();
        //获取全部的查询数据
        List<Employee> records = emps.getResult();

        return  new PageResult(total,records);

    }

    @Override
    public void update(Integer statue, Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setStatus(statue);
        employee.setUpdateTime(LocalDateTime.now());

        employeeMapper.update(employee);
    }


    //根据id查询员工信息并返回
    @Override
    public Employee selectbyid(Long id) {

       Employee emp =  employeeMapper.selectbyid(id);
       return emp;
    }

    //修改员工信息
    @Override
    public void updateEmpInfo(EmployeeDTO employeeDTO) {
        //补全数据
        Employee epm = new Employee();
        epm.setId(employeeDTO.getId());
        epm.setName(employeeDTO.getName());
        epm.setIdNumber(employeeDTO.getIdNumber());
        epm.setUsername(employeeDTO.getUsername());
        epm.setSex(employeeDTO.getSex());
        epm.setPhone(employeeDTO.getPhone());
        epm.setUpdateTime(LocalDateTime.now());
        epm.setUpdateUser(BaseContext.getCurrentId());
        employeeMapper.update(epm);
    }


}
