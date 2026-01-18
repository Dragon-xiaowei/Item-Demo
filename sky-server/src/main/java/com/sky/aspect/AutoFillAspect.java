package com.sky.aspect;


import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import org.aspectj.lang.reflect.MethodSignature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.time.LocalDateTime;


//自定义切面
@Component
@Slf4j
@Aspect
public class AutoFillAspect {

    //定义切点,监听mapper的所有方法
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){}

    //定义前置通知方法方法,实现自动填充功能
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) throws NoSuchMethodException {
        log.info("开始进行数据填充");

        //获取当前使用当前注解的value值
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = methodSignature.getMethod().getAnnotation(AutoFill.class);
        OperationType value = autoFill.value();

        //获取当前被拦截的方法的所有参数,即实体对象
        Object[] args = joinPoint.getArgs();
        Object arg = args[0];

        LocalDateTime localDateTime = LocalDateTime.now();

        long userid = BaseContext.getCurrentId();

        //判断方法的参数是否为空
        if(args == null || args.length==0){
            return;
        }

        //判断方法的类型,然后进赋值
        if (value == OperationType.INSERT){
            Method setCreateTime = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
            Method setCreateUser = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
            Method setUpdateTime = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
            Method setUpdateUser = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
            try {
                setCreateTime.invoke(arg,localDateTime);
                setCreateUser.invoke(arg,userid);
                setUpdateTime.invoke(arg,localDateTime);
                setUpdateUser.invoke(arg,userid);
            } catch (Exception e) {
              e.printStackTrace();
            }
        }else {
            Method setUpdateUser = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
            Method setUpdateTime = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
            try {
                setUpdateUser.invoke(arg,userid);
                setUpdateTime.invoke(arg,localDateTime);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }







    }

}
