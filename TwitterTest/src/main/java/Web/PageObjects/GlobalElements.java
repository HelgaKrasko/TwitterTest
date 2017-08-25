package Web.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GlobalElements {
    private WebDriver driver;
    private String accountNameLink = "//a[@class='u-textInheritColor js-nav']";
    private String alertMessage = "//div[@class='alert-messages js-message-drawer-visible']//span[@class='message-text']";

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
