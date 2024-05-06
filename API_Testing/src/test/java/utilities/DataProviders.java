package utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class DataProviders {

    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir") + "//testData/Data.xlsx";
        XLUtility xl = new XLUtility(path);

        int rowNum = xl.getRowCount("Sheet1");
        int columCount = xl.getCellCount("Sheet1", 1);

        String[][] apidata = new String[rowNum][columCount];

        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < columCount; j++) {
                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
        return apidata;
    }

    @DataProvider(name = "UsersName")
    public String[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir") + "//testData/Data.xlsx";
        XLUtility xl = new XLUtility(path);

        int rowNum = xl.getRowCount("Sheet1");
        String[] apidata = new String[rowNum];

        for (int i = 1; i <= rowNum; i++) {
            apidata[i - 1] = xl.getCellData("Sheet1", i, 1);
        }
        return apidata;
    }

    @Test (dataProvider = "UsersName",dataProviderClass = DataProviders.class)
    void read() throws IOException {
        String[] a = getUserNames();
        System.out.println(Arrays.toString(a));
    }
}
