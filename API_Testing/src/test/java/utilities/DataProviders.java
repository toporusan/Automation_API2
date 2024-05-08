package utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;

public class DataProviders {

    @DataProvider(name = "Data")
    public Object[][] getAllData() {
        String path = System.getProperty("user.dir") + "//testData/Data.xlsx";
        XLUtility xl = new XLUtility(path);

        String[][] apidata = null;
        try {
            int rowNum = xl.getRowCount("Sheet1");
            int columCount = xl.getCellCount("Sheet1", 1);

            apidata = new String[rowNum][columCount];

            for (int i = 1; i <= rowNum; i++) {
                for (int j = 0; j < columCount; j++) {
                    apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return apidata;
    }


    @DataProvider(name = "UsersName")
    public Object[][] getUserNames() {
        String path = System.getProperty("user.dir") + "//testData/Data.xlsx";
        XLUtility xl = new XLUtility(path);

        String[][] apidata = null;
        try {
            int rowNum = xl.getRowCount("Sheet1");
            apidata = new String[rowNum][1]; // Размер внешнего массива 7, а размер внутренних массивов 1
                                             //[[testUser1], [testUser2], [testUser3], [testUser4], [testUser5]]

            for (int i = 1; i <= rowNum; i++) {
                apidata[i - 1][0] = xl.getCellData("Sheet1", i, 1);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return apidata;
    }

}
