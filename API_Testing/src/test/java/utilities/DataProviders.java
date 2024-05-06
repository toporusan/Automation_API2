package utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class DataProviders {

    @DataProvider(name = "Data")
    public Object[][] getAllData() {
        String path = System.getProperty("user.dir") + "//testData/Data.xlsx";
        XLUtility xl = new XLUtility(path);

        String[][] apidata = new String[0][];
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

/*    @DataProvider(name = "UsersName")
    public Object[][] getUserNames() {
        String path = System.getProperty("user.dir") + "//testData/Data.xlsx";
        XLUtility xl = new XLUtility(path);

        Object[][] apidata = new Object[0][];
        try {
            int rowNum = xl.getRowCount("Sheet1");
            apidata = new Object[rowNum][];

            for (int i = 1; i <= rowNum; i++) {
                apidata[i - 1][0] = xl.getCellData("Sheet1", i, 1);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return apidata;
    }*/

    @Test/*(name = "UsersName")*/
    public void getUserNames() {
        String path = System.getProperty("user.dir") + "//testData/Data.xlsx";
        XLUtility xl = new XLUtility(path);

        String[][] apidata;
        try {
            int rowNum = xl.getRowCount("Sheet1");

            System.out.println(rowNum);

            apidata = new String[rowNum][];

            for (int i = 1; i <= rowNum; i++) {
                apidata[i - 1][0] = xl.getCellData("Sheet1", i, 1);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Arrays.toString(apidata));
    }



   // @Test(dataProvider = "UsersName",dataProviderClass = DataProviders.class)
    /*void read() throws IOException {
        Object[][] a = getUserNames();
        System.out.println(Arrays.toString(a));
    }*/
}
