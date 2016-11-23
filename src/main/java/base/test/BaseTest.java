package base.test;

import appium.Client;
import appium.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import settings.Settings;

import java.lang.reflect.Method;

/**
 * Base test.
 * TODO(dtopuzov): Add better docs
 */
public class BaseTest {

    public Settings settings;
    public Logger log = LogManager.getLogger(BaseTest.class.getName());

    private Exception failedToInitSettings = null;

    private void initSettings() {
        try {
            this.settings = new Settings();
        } catch (Exception e) {
            this.failedToInitSettings = e;
        }
    }

    private void verifySettings() throws Exception {
        if (this.failedToInitSettings != null) {
            this.log.fatal("Failed to init settings.");
            throw new Exception(this.failedToInitSettings);
        }
    }

    public BaseTest() {
        this.initSettings();
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() throws Exception {
        this.verifySettings();

        Server.startAppiumServer(this.settings);
        Client.startAppiumClient(this.settings);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeTest(Method method) {
        String testName = method.getName();
        this.log.info("Start: " + testName);
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        Client.stopAppiumClient();
        Server.stopAppiumServer();
    }
}
