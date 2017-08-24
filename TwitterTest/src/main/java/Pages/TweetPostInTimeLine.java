package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TweetPostInTimeLine {
    private WebDriver driver;
    private int position;
    String createdAt = "//span[@class='_timestamp js-short-timestamp js-relative-timestamp']";
    String status = "//div[@class='js-tweet-text-container']";
    String retweetCount = "//span[@class='ProfileTweet-actionCountForPresentation']";
    String actionDropdown = "//li[contains(@class,'js-stream-item stream-item stream-item')]//div[@class='dropdown']";
    String dropdownDeleteOption = "//li[@class='js-actionDelete']";
    String deleteButton = "//button[@class='EdgeButton EdgeButton--danger delete-action']";

    public TweetPostInTimeLine(WebDriver driver, int position){
        this.driver = driver;
        this.position = position;
    }

    public WebElement getActionDropdown(){
        return driver.findElement(By.xpath("(" + actionDropdown + ")[" + position + "]"));
    }

    public WebElement getDropdownDeleteOption(){
        return driver.findElement(By.xpath("(" + dropdownDeleteOption + ")[" + position + "]"));
    }

    public WebElement getCreatedAt(){
        return driver.findElement(By.xpath("(" + createdAt + ")[" + position + "]"));
    }

    public WebElement getStatus(){
        return driver.findElement(By.xpath("(" + status + ")[" + position + "]"));
    }

    public WebElement getRetweetCount(){
        return driver.findElement(By.xpath("(" + retweetCount + ")[" + position + "]"));
    }

    public WebElement getDeleteButton(){
        return driver.findElement(By.xpath(deleteButton));
    }
}
