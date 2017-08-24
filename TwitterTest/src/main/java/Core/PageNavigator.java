package Core;

import org.openqa.selenium.WebDriver;

public class PageNavigator extends MainDriver {
    private String siteURL;

    public PageNavigator(WebDriver webDriver) {
        super(webDriver);
    }

    public void navigateToSite() {
        webDriver.navigate().to(siteURL);
    }

    public void setSiteURL(String siteURL) {
        this.siteURL = siteURL;
    }
}
