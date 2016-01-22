package com.test.cardata;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by zhangfan on 16/1/21.
 */
public class MultiThreadWrite extends Thread {

    public Integer row = 0;
    public Integer page = 0;
    private int fileNum;

    public MultiThreadWrite(int fileNum) {
        this.fileNum = fileNum;
    }

    public static void writeExcel(int threadNum) {

        for (int i = 0; i < threadNum; i++) {
            (new MultiThreadWrite(i)).start();
        }
    }


    @Override
    public void run() {
        try {
            System.out.println("解析数据");
            FileOutputStream fos = new FileOutputStream(new File("/Users/zhangfan/webdata/suiyibei" + fileNum + ".xls"));
            WritableWorkbook workbook = Workbook.createWorkbook(fos);
            WritableSheet sheet = workbook.createSheet("data-" + (page + 1), page);
            while (true) {

                if(SuiYiBeiData.queueData.isEmpty()) break;
                String data = SuiYiBeiData.queueData.poll();
                Thread.sleep(1000);
                if (data == null) continue;
                if ("end".equals(data)) break;

                if (row > 100) {
                    page++;
                    sheet = workbook.createSheet("data-" + (page + 1), page);
                    row = 0;
                }
                SuiYiBeiData.parseSuiYiBeiData(data, sheet, this);
                System.out.println(row);
            }
            workbook.write();
            workbook.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
