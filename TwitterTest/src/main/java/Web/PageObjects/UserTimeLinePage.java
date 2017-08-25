package Web.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UserTimeLinePage {
    private WebDriver driver;
    private String listOfTweets = "//ol[@class='stream-items js-navigable-stream']//li[contains(@class,'js-stream-item stream-item stream-item')]";
    private String listOfStatuses = "//ol[@class='stream-items js-navigable-stream']//li[contains(@class,'js-stream-item stream-item stream-item')]//div[@class='js-tweet-text-container']";

    public UserTimeLinePage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getListOfTweets() {
        return driver.findElements(By.xpath(listOfTweets));
    }

    public List<WebElement> getListOfStatuses() {
        return driver.findElements(By.xpath(listOfStatuses));
    }

    public WebElement getStatusByPosition(int position) {
        return driver.findElement(By.xpath("(" + listOfStatuses + ")[" + position + "]"));
    }
}
