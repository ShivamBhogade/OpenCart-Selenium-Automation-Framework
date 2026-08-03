package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private FileInputStream fi;
    private FileOutputStream fo;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    private CellStyle style;
    private String path;

    public ExcelUtility(String path) {
        this.path = path;
    }

    // Get Row Count
    public int getRowCount(String sheetName) throws Exception {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            workbook.close();
            fi.close();
            throw new Exception("Sheet not found: " + sheetName);
        }

        int rowCount = sheet.getLastRowNum();

        workbook.close();
        fi.close();

        return rowCount;
    }

    // Get Cell Count
    public int getCellCount(String sheetName, int rowNum) throws Exception {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            workbook.close();
            fi.close();
            throw new Exception("Sheet not found: " + sheetName);
        }

        row = sheet.getRow(rowNum);

        if (row == null) {
            workbook.close();
            fi.close();
            return 0;
        }

        int cellCount = row.getLastCellNum();

        workbook.close();
        fi.close();

        return cellCount;
    }

    // Read Cell Data
    public String getCellData(String sheetName, int rowNum, int colNum) throws Exception {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            workbook.close();
            fi.close();
            throw new Exception("Sheet not found: " + sheetName);
        }

        row = sheet.getRow(rowNum);

        if (row == null) {
            workbook.close();
            fi.close();
            return "";
        }

        cell = row.getCell(colNum);

        if (cell == null) {
            workbook.close();
            fi.close();
            return "";
        }

        DataFormatter formatter = new DataFormatter();
        String data = formatter.formatCellValue(cell);

        workbook.close();
        fi.close();

        return data;
    }

    // Write Cell Data
    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws Exception {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        row = sheet.getRow(rowNum);

        if (row == null) {
            row = sheet.createRow(rowNum);
        }

        cell = row.getCell(colNum);

        if (cell == null) {
            cell = row.createCell(colNum);
        }

        cell.setCellValue(data);

        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }

    // Fill Green Color
    public void fillGreenColor(String sheetName, int rowNum, int colNum) throws Exception {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }

    // Fill Red Color
    public void fillRedColor(String sheetName, int rowNum, int colNum) throws Exception {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }
}