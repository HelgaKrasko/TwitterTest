import Pages.HomeTimeLinePage;
import Pages.LoginPage;
import Core.PageNavigator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class TwitterTestWeb {
    private static WebDriver driver;
    private LoginPage loginPage;
    private HomeTimeLinePage homeTimeLinePage;

    private static String siteURL = "https://twitter.com/";
    private static String loginValue = "orkgirl";
    private static String passwordValue = "twittertest123";
    private static String tweetMessage = "Test tweet";


    @BeforeSuite
    public static void setUp() {
        File file = new File("C:/chromedriver_win32/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @BeforeTest(description = "Navigate to target site and log in")
    public void navigateToSiteAndLogin() {
        navigateToSite();
        logIn();
    }

    @Test (description = "Create tweet and check it in HomeTimeLine")
    public void checkCreatedTweet() {
        homeTimeLinePage = new HomeTimeLinePage(driver);
        homeTimeLinePage.getCreateNewTweetField().sendKeys(tweetMessage);
        homeTimeLinePage.getTweetButton().click();
    }

    @AfterSuite
    public static void setDown() {
        driver.quit();
    }

    private void navigateToSite() {
        PageNavigator pageNavigator = new PageFactory().initElements(driver, PageNavigator.class);
        pageNavigator.setSiteURL(siteURL);
        pageNavigator.navigateToSite();
    }

    private void logIn() {
        loginPage = new LoginPage(driver);
        loginPage.getLoginField().sendKeys(loginValue);
        loginPage.getPasswordField().sendKeys(passwordValue);
        loginPage.getLogInButton().click();
    }
}
