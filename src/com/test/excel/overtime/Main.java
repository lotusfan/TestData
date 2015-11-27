package com.test.excel.overtime;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangfan on 2015/10/10.
 */
public class Main {

    public static String inputFile = "D://work/temp";
    public static String outputFile = "D://work/result.xls";


    public static void main(String[] args) throws Exception {


        WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File(outputFile));

        File file = new File(inputFile);
        File[] files = file.listFiles();

        WritableCellFormat greyBackground = new WritableCellFormat();
        greyBackground.setBackground(Colour.GRAY_25);//设置背景颜色为灰色

        for (int i = 0; i < files.length; i++) {
            WritableSheet writableSheet = writableWorkbook.createSheet(files[i].getName(), i);

            FileInputStream fileInputStream = new FileInputStream(files[i]);
            Workbook workbook = Workbook.getWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(0);

            //计算加班记录位置
            Map<String, String> coordinate = new HashMap<>();
            Map<String, String> dateTime = new LinkedHashMap<>();

            paseData(sheet, coordinate, dateTime);
            //复制excel
            copyData(sheet, writableSheet);
            //添加背景色
            addColor(coordinate, sheet, writableSheet);

            //餐费申请表
            Cell cell = sheet.getCell(2, 1);
            Meal.execute(writableWorkbook, cell.getContents(), dateTime, i);

        }


        //把创建的内容写入到输出流中，并关闭输出流
        writableWorkbook.write();
        writableWorkbook.close();
    }

    /**
     * 标注餐费记录条目
     *
     * @param map
     * @param sheet
     * @param writableSheet
     * @throws Exception
     */
    public static void addColor(Map<String, String> map, Sheet sheet, WritableSheet writableSheet) throws Exception{
        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();

        WritableCellFormat background = new WritableCellFormat();
        background.setBackground(Colour.GRAY_25);//设置背景颜色为灰色

        while (iterator.hasNext()) {
            String date = iterator.next();
            String temp = map.get(date);
            String[] strings = temp.split("\\|");
            int row = Integer.parseInt(strings[1]);

            for (int i = 0; i < sheet.getColumns(); i++) {
                Cell cell = sheet.getCell(i, row);
                Label label = new Label(i, row, cell.getContents(), background);
                writableSheet.addCell(label);
            }
        }
    }

    /**
     * 复制打卡记录
     *
     * @param sheet
     * @param writableSheet
     * @throws Exception
     */
    public static void copyData(Sheet sheet, WritableSheet writableSheet) throws Exception{
        //复制excel
        for (int r = 0; r < sheet.getRows(); r++) {

            for (int c = 0; c < sheet.getColumns(); c++) {
                Cell cell = sheet.getCell(c, r);
                Label label = null;
                label = new Label(c, r, cell.getContents());
                writableSheet.addCell(label);
            }
        }
        writableSheet.setColumnView(3, 20);
    }

    /**
     * 解析数据
     *
     * @param sheet
     * @param coordinate
     * @param dateTime
     * @throws Exception
     */
    public static void paseData(Sheet sheet, Map<String, String> coordinate, Map<String, String> dateTime) throws Exception {
        Pattern patternTime = Pattern.compile("\\d{0,2}:\\d{0,2}");
        Pattern patternDate = Pattern.compile("\\d{4}/\\d{0,2}/\\d{0,2}");

        for (int i = 0; i < sheet.getColumns(); i++) {
            if (sheet.getCell(i, 0).getContents().equals("出勤时间")) {
                for (int j = 1; j < sheet.getRows(); j++) {
                    Cell cell = sheet.getCell(i, j);
                    String temp = cell.getContents();
                    Matcher matcherTime = patternTime.matcher(temp);

                    String timeSub = "";
                    String dateSub = "";
                    while (matcherTime.find()) {
                        timeSub = matcherTime.group();
                    }
                    if (timeSub.compareTo("19:30") >= 0 && timeSub.length() > 4) {
                        Matcher matcherDate = patternDate.matcher(temp);
                        while (matcherDate.find()) {
                            dateSub = matcherDate.group();
                        }
                        coordinate.put(dateSub, i + "|" + j);
                        dateTime.put(dateSub, timeSub);
                    }
                }
            }
        }
    }

    public static void sequenceData(Map<String, String> dateTime) {
        List<Map.Entry<String, String>> entryList = new ArrayList<>(dateTime.entrySet());

        Collections.sort(entryList, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                String s1 = o1.getKey();
                String s2 = o2.getKey();

                String[] strings1 = s1.split("/");
                String[] strings2 = s2.split("/");

                int i1 = Integer.parseInt(strings1[2]);
                int i2 = Integer.parseInt(strings2[2]);

                return i1 - i2;
            }
        });
    }

}
