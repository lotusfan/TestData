package com.test.excel.overtime;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhangfan on 2015/10/12.
 */
public class Meal {


    /**
     * 单人餐补表
     *
     * @param writableWorkbook
     * @param name
     * @param dateTime
     * @param page
     * @throws Exception
     */
    public static void execute(WritableWorkbook writableWorkbook, String name, Map<String, String> dateTime, int page) throws Exception {


        WritableSheet writableSheet = writableWorkbook.createSheet(name + "餐费", 0);
        writableSheet.mergeCells(0, 0, 8, 3);

        //表头格式
        WritableFont bold = new WritableFont(WritableFont.ARIAL,12);
        WritableCellFormat headFormat = new WritableCellFormat(bold);
        headFormat.setAlignment(Alignment.CENTRE);
        headFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

        Calendar calendar = Calendar.getInstance();

        //表头
        int month = calendar.get(Calendar.MONTH);
        Label head = new Label(0, 0, "加班餐费申请单（"+calendar.get(Calendar.YEAR)+"年"+(month>0?month:12)+"月份）", headFormat);
        writableSheet.addCell(head);

        WritableImage writableImage = new WritableImage(0.3, 1, 2.2, 1.76, new File("D://qrxd.png"));
        writableSheet.addImage(writableImage);
        //部门
        WritableFont departmentFont = new WritableFont(WritableFont.ARIAL, 11);
        WritableCellFormat departmentFormat = new WritableCellFormat(departmentFont);
        Label department = new Label(0, 4, "所属部门：事业一部", departmentFormat);
        writableSheet.addCell(department);
        //表头
        String[] tableHeads = new String[]{
                "序号",
                "加班申请日期",
                "加班人",
                "加班事由",
                "打卡时间",
                "应付餐费",
                "票据日期",
                "票据金额",
                "备注"
        };

        //数据表格式
        WritableFont writableFont = new WritableFont(WritableFont.ARIAL, 11);
        WritableCellFormat writableCellFormat = new WritableCellFormat(writableFont);
        writableCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
        writableCellFormat.setAlignment(Alignment.CENTRE);
        writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

        //表头写入
        for (int i = 0; i < tableHeads.length; i++) {
            Label tableHead = new Label(i, 5, tableHeads[i], writableCellFormat);
            writableSheet.addCell(tableHead);
        }

        //表单数据写入
        Set<String> date = dateTime.keySet();
        Iterator<String> iterator = date.iterator();
        int c = 1;
        while (iterator.hasNext()) {
            String key = iterator.next();
            int column = c + 5;
            Label tableContent = new Label(0, column, c + "", writableCellFormat);
            writableSheet.addCell(tableContent);
            Label tableContent1 = new Label(1, column, key, writableCellFormat);
            writableSheet.addCell(tableContent1);
            Label tableContent2 = new Label(2, column, name, writableCellFormat);
            writableSheet.addCell(tableContent2);
            Label tableContent3 = new Label(3, column, "开发", writableCellFormat);
            writableSheet.addCell(tableContent3);
            Label tableContent4 = new Label(4, column, dateTime.get(key), writableCellFormat);
            writableSheet.addCell(tableContent4);
            Label tableContent5 = new Label(5, column, "20", writableCellFormat);
            writableSheet.addCell(tableContent5);
            Label tableContent6 = new Label(6, column, "", writableCellFormat);
            writableSheet.addCell(tableContent6);
            Label tableContent7 = new Label(7, column, "", writableCellFormat);
            writableSheet.addCell(tableContent7);
            Label tableContent8 = new Label(8, column, "", writableCellFormat);
            writableSheet.addCell(tableContent8);

            c++;
        }

        //合计
        int columns = writableSheet.getColumns();
        for (int i = 0; i < columns; i++) {
            Label label = new Label(i, c+5, "", writableCellFormat);
            writableSheet.addCell(label);
        }
        Label summation = new Label(3, c + 5,"合计",writableCellFormat);
        writableSheet.addCell(summation);
        Label summationData = new Label(5, c + 5, ((c-1) * 20) + "", writableCellFormat);
        writableSheet.addCell(summationData);


        c = c + 6;
        Label handle = new Label(0, c, "经办人：张帆", departmentFormat);
        writableSheet.addCell(handle);
        Label handle1 = new Label(4, c, "部门负责人：", departmentFormat);
        writableSheet.addCell(handle1);
        Label handle2 = new Label(7, c, "行政负责人：", departmentFormat);
        writableSheet.addCell(handle2);

        c++;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd");
        Label dateSuffix = new Label(0, c, "日期："+simpleDateFormat.format(calendar.getTime()), departmentFormat);
        writableSheet.addCell(dateSuffix);
        Label dateSuffix1 = new Label(4, c, "日期：", departmentFormat);
        writableSheet.addCell(dateSuffix1);
        Label dateSuffix2 = new Label(7, c, "日期：", departmentFormat);
        writableSheet.addCell(dateSuffix2);

        //设置行高 列宽
        int rows = writableSheet.getRows();

        for (int i = 1; i < columns; i++) {
            writableSheet.setColumnView(i, 15);
        }
        for (int i = 0; i < rows; i++) {
            writableSheet.setRowView(i, 400, false);
        }

    }
}
