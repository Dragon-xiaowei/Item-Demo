package com.sky.annotation;


//自定义注解,表示标识的方法需要给功能字段进行自动填充
import com.sky.enumeration.OperationType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD}) //标记该注解只能在方法上面使用
@Retention(RetentionPolicy.RUNTIME) //标记该注解在什么环境下生效,运行环境或者测试环境
public @interface AutoFill {

    OperationType value();





}
