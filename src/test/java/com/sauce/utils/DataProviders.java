package com.sauce.utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        String filePath = "src/test/resources/testdata.xlsx";
        String sheetName = "Login";
        Object[][] finalTestData=ExcelUtil.getTestData(filePath, sheetName);
        return finalTestData;
    }
}

