package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private String logInField = "//input[@id='signin-email']";
    private String passwordField = "//input[@id='signin-password']";
    private String logInButton = "//td[@class='flex-table-secondary']/button";


    public WebElement getLoginField() {
        return driver.findElement(By.xpath(logInField));
    }

    public WebElement getPasswordField() {
        return driver.findElement(By.xpath(passwordField));
    }

    public WebElement getLogInButton() {
        return driver.findElement(By.xpath(logInButton));
    }
}
