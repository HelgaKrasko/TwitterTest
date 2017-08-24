package Pages;

import org.openqa.selenium.WebDriver;

public class PageNavigator extends MainDriver {

    String siteURL;

    PageNavigator(WebDriver webDriver) {
        super(webDriver);
    }

    public void navigateToSite(String siteURL) {
        webDriver.navigate().to(siteURL);
    }
}
