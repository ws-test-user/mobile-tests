package selendroid.tests;

import mobile.tests.core.appium.Client;
import mobile.tests.core.base.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import selendroid.pages.HomePage;

/**
 * Smoke tests for Selendoid test app.
 */
public class SelendroidTests extends BaseTest {

    @Test
    public void checkBox() {
        HomePage seleniumDemo = new HomePage(Client.driver);
        this.log.info("Home page loaded");
        seleniumDemo.checkBox.tap(1, 500);
        this.log.info("Tap on checkBox");
        boolean isCecked = Boolean.valueOf(seleniumDemo.checkBox.getAttribute("checked"));
        Assert.assertEquals(isCecked, false, "Checkbox is still checked.");

        seleniumDemo.checkBox.tap(1, 500);
        isCecked = Boolean.valueOf(seleniumDemo.checkBox.getAttribute("checked"));
        Assert.assertEquals(isCecked, true, "Checkbox is unchecked.");
    }
}
