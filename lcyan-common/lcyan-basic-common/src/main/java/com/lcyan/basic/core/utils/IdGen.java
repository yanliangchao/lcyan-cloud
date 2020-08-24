package com.lcyan.basic.core.utils;

import java.util.UUID;

/**
 * id生成工具类
 * @author ayan2070
 *
 */
public class IdGen {

    /**
     * 封装JDK自带的UUID, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}