package reportportal.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ExcelReader {
    private final static String systemFilePath = System.getProperty("user.dir") + "/src/test/resources/user_files";

    private static Workbook wb = null;

    public static synchronized Workbook getWorkbook(String fileName) throws IOException {
        wb = WorkbookFactory.create(new File(systemFilePath + "/" + fileName));
        return wb;
    }

    public static List<Map<String, String>> readCells(String sheetName, String fileName) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();
        Sheet sheet = getWorkbook(fileName).getSheet(sheetName);
        Row headerRow = sheet.getRow(0);

        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            Map<String, String> dataMap = new LinkedHashMap<>();
            for (Cell cell : row) {
                Cell headerCell = headerRow.getCell(cell.getColumnIndex());
                DataFormatter dataFormatter = new DataFormatter();
                String key = dataFormatter.formatCellValue(headerCell);
                String value = dataFormatter.formatCellValue(cell);
                dataMap.put(key, value);
            }
            data.add(dataMap);
        }
        closeWorkBook();
        return data;
    }

    public static void closeWorkBook() throws IOException {
        wb.close();
    }
}
