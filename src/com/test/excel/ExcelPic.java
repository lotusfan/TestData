package com.test.excel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;

/**
 * Created by zhangfan on 2015/10/10.
 */
public class ExcelPic {
    public static void main(String[] args) throws Exception {

        Workbook workbook = Workbook.getWorkbook(new File("D://work/33.xls"));
        Sheet sheet = workbook.getSheet(2);

        System.out.println(sheet.getNumberOfImages());
        for (int c = 0; c < sheet.getColumns(); c++) {

            for (int r = 0; r < sheet.getRows(); r++) {

                System.out.println("c:"+c + "   r:"+r);
                Cell cell = sheet.getCell(c, r);
                System.out.println(cell.getContents());
            }
        }
    }

    public static void writeExcel(File file) throws Exception {
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(file);
        WritableSheet writableSheet = writableWorkbook.createSheet(file.getName(), 0);




    }

}
