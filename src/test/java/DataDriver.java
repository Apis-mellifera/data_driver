import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriver {

    public ArrayList<String> getData(String testCaseName) throws IOException {
        FileInputStream fls = new FileInputStream("C:\\Users\\Dell\\Documents\\DataDemo.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fls);

        ArrayList<String> data = new ArrayList<>();

        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            if(workbook.getSheetName(i).equalsIgnoreCase("Data")) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                Iterator<Row> rows = sheet.iterator();
                Row firstRaw = rows.next();
                Iterator <Cell> cell = firstRaw.cellIterator();

                int k = 0;
                int column = 0;
                while(cell.hasNext()) {
                    Cell value = cell.next();
                    if(value.getStringCellValue().equals("TestCases")) {
                        column = k;
                    }
                    k++;
                }
                System.out.println(column);

                while(rows.hasNext()) {
                    Row r = rows.next();
                    if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
                        Iterator<Cell> cellIterator = r.cellIterator();
                        while (cellIterator.hasNext()) {
                            Cell c = cellIterator.next();
                            if(c.getCellType()== CellType.STRING) {
                                data.add(c.getStringCellValue());
                            }
                            else {
                                data.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }

                        }
                    }
                }
            }
        }
        return data;
    }
}
