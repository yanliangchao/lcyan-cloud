package com.lcyan.basic.exception;

import org.springframework.util.StringUtils;

/**
 * @author Yan Liangchao
 * @version 1.0
 * @date 2020/4/20 14:03
 * @email liangchao.yan-ext@yanfeng.com
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, String field, String val) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " does not exist";
    }
}
