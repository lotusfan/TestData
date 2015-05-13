package com.test.enumt;

/**
 * Created by zhangfan on 2015/4/14.
 */
public class OrderFMS {

    private static final int SIZE = 10;
    public static final StatusEnum[][][] FMS = new StatusEnum[SIZE][SIZE][SIZE];


    static {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    FMS[i][j][k] = null;
                }
            }

        }
        FMS[1][2][3] = StatusEnum.INIT;
    }

    public static void main(String[] args) {

        StatusEnum dd = FMS[1][2][3];
        System.out.println(dd.toString());;
    }
}
