package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomeTimeLinePage {
    private WebDriver driver;

    public HomeTimeLinePage(WebDriver driver) {
        this.driver = driver;
    }

    private String createNewTweetField = "//div[@id='tweet-box-home-timeline']";
    private String tweetButton = "//button[@class='tweet-action EdgeButton EdgeButton--primary js-tweet-btn']";
    private String listOfTweets = "//ol[@id='stream-items-id']//li[contains(@class,'js-stream-item stream-item stream-item')]";
    private String listOfStatuses = "//ol[@id='stream-items-id']//li[contains(@class,'js-stream-item stream-item stream-item')]//div[@class='js-tweet-text-container']";

    public WebElement getCreateNewTweetField() {
        return driver.findElement(By.xpath(createNewTweetField));
    }

    public WebElement getTweetButton() {
        return driver.findElement(By.xpath(tweetButton));
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
