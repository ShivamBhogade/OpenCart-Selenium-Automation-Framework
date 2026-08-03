package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public String[][] getLoginData() throws Exception {

        String path = System.getProperty("user.dir") + "\\testData\\OpenCart_LoginData.xlsx";

        ExcelUtility excel = new ExcelUtility(path);// create object of ExcelUtility class

        int totalRows = excel.getRowCount("Sheet1");
        int totalCells = excel.getCellCount("Sheet1", 1);

        String loginData[][] = new String[totalRows][totalCells];

        for (int i = 1; i <= totalRows; i++) {

            for (int j = 0; j < totalCells; j++) {

                loginData[i - 1][j] = excel.getCellData("Sheet1", i, j); //1,0

            }
        }

        return loginData; // return 2D array to the test method
    }

}