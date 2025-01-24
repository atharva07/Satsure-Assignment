package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import resources.AbstractPage;
import java.util.List;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//span[contains(text(),'Account & Lists')]")
    private WebElement signInButton;

    @FindBy(xpath = "//input[@id='captchacharacters']")
    private WebElement captchaChar;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    private WebElement searchTextBox;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.signInButton));
        return this.signInButton.isDisplayed();
    }

    public void goTo(String url) {
        this.driver.get(url);
    }

    public void loginFunctionality(String username, String password) throws InterruptedException {
        Thread.sleep(1000);
        if (!driver.findElements(By.xpath("//input[@id='captchacharacters']")).isEmpty()) {
            System.out.println("CAPTCHA detected. Please solve it manually.");
            Thread.sleep(20000); // Wait for the tester to solve it
        }
        this.signInButton.click();
        this.emailField.sendKeys(username);
        this.submitButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
        this.passwordField.sendKeys(password);
        this.submitButton.click();
    }

    public boolean isLoginSuccessful() {
        // Check for logout button presence or error message
        return driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).isDisplayed();
    }
}