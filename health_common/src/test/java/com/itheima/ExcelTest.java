package com.itheima;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * POI入门案例-操作Excel
 */
public class ExcelTest {

    /**
     * 方式一：读取
     * @throws Exception
     */
    //@Test
    public void readExcel() throws Exception {
        //1：创建工作簿对象
        //HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); //xls
        XSSFWorkbook workbook = new XSSFWorkbook("E:\\study\\soft\\read.xlsx");
        //2：获得工作表对象
        //XSSFSheet sheet1 = workbook.getSheet("sheet1");
        XSSFSheet sheetAt = workbook.getSheetAt(0);
        //3：遍历工作表对象 获得行对象
        for (Row row : sheetAt) {
            System.out.println("start**********************************************");
            //4：遍历行对象 获得单元格（列）对象
            for (Cell cell : row) {
                //5：获得数据  cell.getStringCellValue()：
                System.out.println(cell.getStringCellValue());
            }
            System.out.println("end**********************************************");
        }
        // 6：关闭
        workbook.close();
    }

    //获取excel，获取最后一行
    @Test
    public void readExcel2() throws IOException {
        //创建工作簿
       XSSFWorkbook workbook = new XSSFWorkbook("E:\\study\\soft\\read.xlsx");
       //获取工作表，既可以根据工作表的顺序获取，也可以根据工作表的名称获取
       XSSFSheet sheet = workbook.getSheetAt(0);
       //获取当前工作表最后一行的行号，行号从0开始
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i <lastRowNum ; i++) {
            //根据行号获取行对象
            XSSFRow row = sheet.getRow(i);
            short lastCellNum = row.getLastCellNum();
            for (short j = 0; j <lastCellNum ; j++) {
                String value = row.getCell(j).getStringCellValue();
                System.out.println(value);
            }

        }
        workbook.close();
    }

    //创建excel
    @Test
    public void writeExcel() throws Exception {
        //1创建工作簿对象
         XSSFWorkbook workbook = new XSSFWorkbook();
         //2 创建工作表对象
         XSSFSheet sheet = workbook.createSheet();
         //3 创建行对象
        XSSFRow titleRow = sheet.createRow(0);
        //4 创建列（单元格）对象，设置内容
        titleRow.createCell(0).setCellValue("编号");
        titleRow.createCell(1).setCellValue("姓名");
        titleRow.createCell(2).setCellValue("年龄");

        XSSFRow dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue("001");
        dataRow.createCell(1).setCellValue("老王");
        dataRow.createCell(2).setCellValue("18");

        //5 通过输出流将workbook对象下载到磁盘
         FileOutputStream fileOutputStream = new FileOutputStream("E:\\study\\soft\\1.xlsx");
         workbook.write(fileOutputStream);
         fileOutputStream.flush();
         fileOutputStream.close();
         workbook.close();
    }

}
