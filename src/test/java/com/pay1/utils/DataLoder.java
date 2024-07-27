package com.pay1.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;

public class DataLoder {
    public static ArrayList<String> Read_Excel_Data(String File_Name, String Sheet_Name, String TestCase_Name)
            throws IOException {
        ArrayList<String> ArrayData = new ArrayList<>();

        // Step 1: Locate the Excel file
        FileInputStream FIS = new FileInputStream(File_Name);
        XSSFWorkbook wb = new XSSFWorkbook(FIS);

        // Step 2: Access the desired sheet
        XSSFSheet sheet = wb.getSheet(Sheet_Name);

        // Step 3: Iterate over rows
        for (Row row : sheet) {
            // Step 4: Check if the first cell value matches the desired test case name
            Cell firstCell = row.getCell(0);
            if (firstCell != null && firstCell.getCellType() == CellType.STRING && firstCell.getStringCellValue().equals(TestCase_Name)) {
                // Step 5: Iterate over cells in the current row and add their values to ArrayData
                for (Cell cell : row) {
                    String cellValue = "";

                    if (cell.getCellType() == CellType.NUMERIC) {
                        // Convert numeric cell value to BigDecimal and then to plain string to avoid scientific notation
                        cellValue = BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();

                        // Remove unnecessary ".0" from the end of the number if present
                        if (cellValue.endsWith(".0")) {
                            cellValue = cellValue.substring(0, cellValue.length() - 2);
                        }
                    } else if (cell.getCellType() == CellType.STRING) {
                        cellValue = cell.getStringCellValue();
                    }

                    ArrayData.add(cellValue);
                }
                break; // Exit the loop once the desired test case data is found
            }
        }

        wb.close();
        return ArrayData;
    }
}
