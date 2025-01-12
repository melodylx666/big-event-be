package com.lxbigdata.be.anno;

import com.lxbigdata.be.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotEmpty;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * ClassName: State
 * Package: com.lxbigdata.be.anno
 * Description: 自定义参数校验的注解，可以参考 NotNull
 *
 * @author lx
 * @version 1.0
 */
@Documented //元注解
@Constraint(validatedBy = {StateValidation.class}) //元注解，表示本注解可以校验的类,在validation包下定义
@Target({ FIELD}) //元注解，表示本注解可以作用的位置
@Retention(RUNTIME) //元注解，表示本注解可以作用的时间
public @interface State {
    //提供校验失败后的提示信息
    String message() default "{state参数的值只能是'已发布'或者'草稿'}";
    //指定分组
    Class<?>[] groups() default { };
    //附加信息，一般不用到
    Class<? extends Payload>[] payload() default { };


}
