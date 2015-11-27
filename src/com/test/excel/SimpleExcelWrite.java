package com.test.excel;

/**
 * Created by zhangfan on 2015/10/9.
 */

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.*;
import jxl.format.Colour;
import jxl.write.*;

public class SimpleExcelWrite {

    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("D://work/aa2.xls");
      /*  FileInputStream fileInputStream = new FileInputStream("D://work/aa.xls");


        writeExcel(fileInputStream, fileOutputStream);
        fileInputStream.close();
        fileOutputStream.close();*/

        MutiStyleExcelWrite.createExcel(fileOutputStream);

//        FileInputStream fileInputStream = new FileInputStream("D://aa.xls");
        /*FileInputStream fileInputStream = new FileInputStream("D://work/aa.xls");
        readExcel(fileInputStream);
        fileInputStream.close();*/
    }


    public static void createExcel(OutputStream os) throws WriteException, IOException {
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        Label xuexiao = new Label(0, 0, "学校");
        sheet.addCell(xuexiao);
        Label zhuanye = new Label(1, 0, "专业");
        sheet.addCell(zhuanye);
        Label jingzhengli = new Label(2, 0, "专业竞争力");
        sheet.addCell(jingzhengli);

        Label qinghua = new Label(0, 1, "清华大学");
        sheet.addCell(qinghua);
        Label jisuanji = new Label(1, 1, "计算机专业");
        sheet.addCell(jisuanji);
        Label gao = new Label(2, 1, "高");
        sheet.addCell(gao);

        Label beida = new Label(0, 2, "北京大学");
        sheet.addCell(beida);
        Label falv = new Label(1, 2, "法律专业");
        sheet.addCell(falv);
        Label zhong = new Label(2, 2, "中");
        sheet.addCell(zhong);

        Label ligong = new Label(0, 3, "北京理工大学");
        sheet.addCell(ligong);
        Label hangkong = new Label(1, 3, "航空专业");
        sheet.addCell(hangkong);
        Label di = new Label(2, 3, "低");
        sheet.addCell(di);

        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }

    public static void readExcel(InputStream inputStream) throws Exception {

        Map<String, String> coordinate = new HashMap<>();


        Workbook wb = Workbook.getWorkbook(inputStream);//从文件流中取得Excel工作区对象
        Sheet sheet = wb.getSheet(0);//从工作区中取得页，取得这个对象的时候既可以用名称来获得，也可以用序号。

        Pattern patternTime = Pattern.compile("\\d{0,2}:\\d{0,2}");
        Pattern patternDate = Pattern.compile("\\d{4}/\\d{0,2}/\\d{0,2}");

        for (int i = 0; i < sheet.getColumns(); i++) {
            if (sheet.getCell(i, 0).getContents().equals("出勤时间")) {
                System.out.println("date");
                for (int j = 1; j < sheet.getRows(); j++) {
                    Cell cell = sheet.getCell(i, j);
                    String temp = cell.getContents();
                    Matcher matcherTime = patternTime.matcher(temp);

                    String timeSub = "";
                    String dateSub = "";
                    while (matcherTime.find()) {
                        timeSub = matcherTime.group();
                    }
                    if (timeSub.compareTo("20:00") > 0 && timeSub.length() > 4) {
                        Matcher matcherDate = patternDate.matcher(temp);
                        while (matcherDate.find()) {
                            dateSub = matcherDate.group();
                        }
                        coordinate.put(dateSub, i + "|" + j);
                    }
                }
            }
        }


        Set<String> set = coordinate.keySet();
        Iterator<String> iterator = set.iterator();

        while (iterator.hasNext()) {
            String temp = iterator.next();
            System.out.print(temp);
            System.out.println("\t" + coordinate.get(temp));
        }

    }

    public static void writeExcel(InputStream inputStream, OutputStream outputStream) throws Exception {

        Workbook rwb = Workbook.getWorkbook(inputStream);
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(outputStream);

        Sheet sheet = rwb.getSheet(0);
        WritableSheet writableSheet = writableWorkbook.createSheet("dd", 0);

        WritableCellFormat greyBackground = new WritableCellFormat();
        greyBackground.setBackground(Colour.GRAY_25);//设置背景颜色为灰色

        for (int i = 0; i < sheet.getRows(); i++) {

            for (int j = 0; j < sheet.getColumns(); j++) {

                Cell cell = sheet.getCell(j, i);
                Label label = null;
                if (i == 39) {
                    System.out.println(j);
                    label = new Label(j, i, cell.getContents(), greyBackground);

                } else {
                    label = new Label(j, i, cell.getContents());
                }

                writableSheet.addCell(label);
            }
        }

        //把创建的内容写入到输出流中，并关闭输出流
        writableWorkbook.write();
        writableWorkbook.close();
    }

}