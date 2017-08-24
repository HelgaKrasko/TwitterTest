package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomeTimeLinePage {
    private WebDriver driver;

    public HomeTimeLinePage(WebDriver driver) {
        this.driver = driver;
    }
    private String createNewTweetField = "//div[@id='tweet-box-home-timeline']";
    private String tweetButton = "//button[@class='tweet-action EdgeButton EdgeButton--primary js-tweet-btn']";

    public WebElement getCreateNewTweetField() {
        return driver.findElement(By.xpath(createNewTweetField));
    }

    public WebElement getTweetButton() {
        return driver.findElement(By.xpath(tweetButton));
    }
}
