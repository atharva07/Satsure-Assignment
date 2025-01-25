package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import resources.ExcelUtils;
import java.io.IOException;

public class DataDriverTesting extends AbstractTest {

    @Test(dataProvider = "loginData")
    public void runSatsureTests(String Sr, String userEmail, String password, String expectedRes, String actualRes) throws InterruptedException, IOException {
        HomePage homepage = new HomePage(driver);
        String path = "src/main/java/resources/testData.xlsx";
        ExcelUtils excelUtil = new ExcelUtils(path);

        String actualResult = "Fail"; // Default to Fail

        try {
            homepage.goTo("https://www.amazon.in/");
            this.driver.manage().window().maximize();
            homepage.loginFunctionality(userEmail, password);

            boolean isLoginSuccessful = homepage.isLoginSuccessful();
            actualResult = isLoginSuccessful ? "Pass" : "Fail";
        } catch (Exception e) {
            System.out.println("Login attempt failed for " + userEmail + ": " + e.getMessage());
            actualResult = "Fail";
        } finally {
            System.out.println(actualResult);

            // Write the result back to Excel
            int srno = Integer.parseInt(Sr);
            excelUtil.setCellData("Sheet1", srno, 4, actualResult);

            // Assert the result matches the expected outcome
            Assert.assertEquals(actualResult, expectedRes, "Test failed for username: " + userEmail);
        }
    }

    @DataProvider(name = "loginData")
    public String [][] getData() throws IOException {
        // Get the data from Excel
        String path = "src/main/java/resources/testData.xlsx";
        ExcelUtils excelutil = new ExcelUtils(path);
        int totalRows = excelutil.getRowCount("Sheet1");
        int totalColumns = excelutil.getCellCount("Sheet1",1);

        String loginData[][] = new String[totalRows][totalColumns];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                loginData[i-1][j] = excelutil.getCellData("Sheet1",i,j);
            }
        }
        return loginData;
    }


}