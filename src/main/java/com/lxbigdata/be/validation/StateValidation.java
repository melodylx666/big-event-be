package com.lxbigdata.be.validation;

import com.lxbigdata.be.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * ClassName: StateValidation
 * Package: com.lxbigdata.be.validation
 * Description:
 *
 * @author lx
 * @version 1.0
 */
public class StateValidation implements ConstraintValidator<State, String> {
    /**
     *
     * @param value object to validate
     * @param context context in which the constraint is evaluated
     *
     * @return 校验情况;如果是true,则校验通过;否则校验不通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //提供校验规则
        if(value == null){
            return false;
        }
        if(!(value.equals("已发布") | value.equals("草稿"))){
            return false;
        }
        return true;
    }
}
