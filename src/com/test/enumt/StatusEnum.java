package com.test.enumt;

/**
 * Created by zhangfan on 2015/4/14.
 */
public enum StatusEnum {
    INIT(1, "初始", 3),
    PAYSUCCESS(2, "已支付", 4),
    ;


    private int value;
    private String expr;
    private int state;

    StatusEnum(int value, String expr, int state) {
        this.value = value;
        this.expr = expr;
        this.state = state;
    }


}
