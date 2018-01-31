package com.abin.lee.curator.test.common;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by abin on 2017/11/28 14:30.
 * curator-svr
 * com.abin.lee.curator.logic.test.enums
 */
public enum OrderEnum {
    ONE("ONE"),
    TWO("TWO"),
    THREE("THREE"),
    ;

    private String param;

    OrderEnum(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public static String find(String input) {
        if (StringUtils.isBlank(input))
            return "";
        for (OrderEnum sourceEnum : values()) {
            return sourceEnum.getParam();
        }
        return "";
    }
}
