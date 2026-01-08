package utils;

import frameworkException.CRMException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class TestUtils {

    static FileInputStream ip;
    static Workbook wb;
    public static Object[][] getContactsDetails(String sheetName){
    try{
        ip = new FileInputStream(Objects.requireNonNull(TestUtils.class.getClassLoader().getResource("testdata/contacts.xlsx")).getFile());
        wb = WorkbookFactory.create(ip);
        Sheet sheet = wb.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        for(int i=0; i<sheet.getLastRowNum(); i++){
            for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++){
                    data[i][j] = sheet.getRow(i+1).getCell(j).toString();
            }
        }
        return data;
    } catch (IOException e) {
        throw new CRMException(e.getMessage());
    }
    }
}
