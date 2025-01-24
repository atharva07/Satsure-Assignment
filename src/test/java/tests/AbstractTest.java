package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class AbstractTest {

    protected WebDriver driver;
    // settting up the driver - here we will be using chrome driver
    @BeforeTest
    public void setDriver() {
        // driver setup
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    // quit driver
    @AfterTest
    public void quitDriver() {
        this.driver.close();
    }
}