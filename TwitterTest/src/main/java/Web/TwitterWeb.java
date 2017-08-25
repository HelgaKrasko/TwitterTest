package Web;

import Api.Oauth;
import Api.TwitterAPI;
import Web.PageObjects.GlobalElements;
import Web.PageObjects.HomeTimeLinePage;
import Web.PageObjects.LoginPage;
import Web.PageObjects.TweetPostInTimeLine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import twitter4j.TwitterException;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class TwitterWeb {
    private static WebDriver driver;
    private LoginPage loginPage;
    private HomeTimeLinePage homeTimeLinePage;
    private PageNavigator pageNavigator;
    private GlobalElements globalElements;
    private TweetPostInTimeLine tweetPostInUserTimeLine;

    public void deleteTweet(int position) throws InterruptedException {
        tweetPostInUserTimeLine = new TweetPostInTimeLine(driver, position);
        tweetPostInUserTimeLine.getActionDropdown().click();
        tweetPostInUserTimeLine.getDropdownDeleteOption().click();
        TimeUnit.SECONDS.sleep(2);
        tweetPostInUserTimeLine.getDeleteButton().click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void goToUserTimeLineByAccountNameLink() throws InterruptedException {
        globalElements = new GlobalElements(driver);
        globalElements.getAccountNameLink().click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void createTweet(String tweetMessage) throws InterruptedException {
        homeTimeLinePage = new HomeTimeLinePage(driver);
        homeTimeLinePage.getCreateNewTweetField().sendKeys(tweetMessage);
        homeTimeLinePage.getTweetButton().click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void navigateToSite(String siteURL) {
        pageNavigator = new PageFactory().initElements(driver, PageNavigator.class);
        pageNavigator.setSiteURL(siteURL);
        pageNavigator.navigateToSite();
    }

    public void logIn(String login, String password) throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.getLoginField().sendKeys(login);
        TimeUnit.SECONDS.sleep(2);
        loginPage.getPasswordField().sendKeys(password);
        loginPage.getLogInButton().click();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setUpDriver() {
        File file = new File("C:/chromedriver_win32/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void setDownDriver() {
        driver.quit();
    }

    public void deleteAllStatusesByAPI() throws TwitterException {
        TwitterAPI twitterAPI = new TwitterAPI();
        Oauth oauth = new Oauth();
        twitter4j.Twitter twitter = oauth.getTwitterInstance();
        twitterAPI.deleteAllStatuses(twitter);
    }
}
