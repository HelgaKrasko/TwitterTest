package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GlobalElements {
    private WebDriver driver;
    private String accountNameLink = "//a[@class='u-textInheritColor js-nav']";
    private String alertMessage = "//div[@id='message-drawer']";

    public GlobalElements(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getAccountNameLink() {
        return driver.findElement(By.xpath(accountNameLink));
    }

    public WebElement getAlertMessage() {
        return driver.findElement(By.xpath(alertMessage));
    }
}
