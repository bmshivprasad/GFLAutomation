package gflwishes.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils implements Configurations {

    public static int getRowsExcel(String fileName) {
        String fileLocation = TEST_DATA_LOCATION + File.separator + fileName + ".xlsx";
        try {
            FileInputStream file = new FileInputStream(fileLocation);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            return sheet.getPhysicalNumberOfRows();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getTestData(String fileName, int req_row, int col) {

        String req_data = "";
        String fileLocation = TEST_DATA_LOCATION + File.separator + fileName + ".xlsx";

        try {

            FileInputStream file = new FileInputStream(fileLocation);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            XSSFRow row = sheet.getRow(req_row);

            try {
                req_data = row.getCell(col).getStringCellValue();
            } catch (Exception e) {
                DataFormatter dateFormat = new DataFormatter();
                req_data = dateFormat.formatCellValue(sheet.getRow(req_row).getCell(col));
            } finally {
                workbook.close();
                file.close();
            }
        } catch (Exception e) {
            System.out.println("Failed to load file " + fileLocation);
        }
        return req_data;
    }

    public int lastRowNumber(String fileName, String sheetName) {

        int lastRow = 0;
        String fileLocation = TEST_DATA_LOCATION + File.separator + fileName + ".xlsx";

        try {
            FileInputStream file = new FileInputStream(fileLocation);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            lastRow = sheet.getLastRowNum();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastRow;
    }

    public String getTestData(String fileName, String sheetName, int req_row, int col) {

        String req_data = "";
        String fileLocation = TEST_DATA_LOCATION + File.separator + fileName + ".xlsx";

        try {

            FileInputStream file = new FileInputStream(fileLocation);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(req_row);

            try {
                req_data = row.getCell(col).getStringCellValue();
            } catch (Exception e) {
                DataFormatter dateFormat = new DataFormatter();
                req_data = dateFormat.formatCellValue(sheet.getRow(req_row).getCell(col));
            } finally {
                workbook.close();
            }
        } catch (Exception e) {
            System.out.println("Failed to load file " + fileLocation);
        }
        return req_data;
    }

    public void setTestData(String fileName, int req_row, int col, String data) {

        String fileLocation = TEST_DATA_LOCATION + File.separator + fileName + ".xlsx";

        try {
            FileInputStream file = new FileInputStream(fileLocation);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            XSSFCell cell = sheet.getRow(req_row).createCell(col);
            cell.setCellValue(data);

            FileOutputStream fo = new FileOutputStream(fileLocation);
            workbook.write(fo);
            workbook.close();
            file.close();
            fo.close();
        } catch (Exception e) {
            System.out.println("Failed to load file " + fileLocation);
        }
    }
}
	
