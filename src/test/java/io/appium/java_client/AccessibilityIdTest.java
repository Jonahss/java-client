package io.appium.java_client;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test context-related features
 */
public class AccessibilityIdTest {

  private AppiumDriver driver;

  @Before
  public void setup() throws Exception {
    File appDir = new File("src/test/java/io/appium/java_client");
    File app = new File(appDir, "UICatalog.app.zip");
    String appOnGithub = "https://github.com/appium/java-client/raw/master/src/test/java/io/appium/java_client/UICatalog.app.zip";

    String sauceUsername = System.getenv("SAUCE_USERNAME");
    String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");
    URL serverUrl = new URL("http://" + sauceUsername + ":" + sauceAccessKey + "@ondemand.saucelabs.com:80/wd/hub");

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
    capabilities.setCapability(MobileCapabilityType.APP, appOnGithub);

    // sauce caps
    capabilities.setCapability("name", "java-client: AccessibilityIdTest");
    capabilities.setCapability("appium-version", "1.2.2");

    driver = new AppiumDriver(serverUrl, capabilities);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }

  @Test
  public void findElementTest() {
    WebElement element = driver.findElementByAccessibilityId("UICatalog");
    assertNotNull(element);
  }

  @Test
  public void findElementsTest() {
    List<WebElement> elements = driver.findElementsByAccessibilityId("UICatalog");
    assertTrue(elements.size() > 0);
  }

  @Test
  public void MobileElementByTest() {
    WebElement element = driver.findElement(MobileBy.AccessibilityId("UICatalog"));
    assertNotNull(element);
  }

  @Test
  public void MobileElementsByTest() {
    List<WebElement> elements = driver.findElements(MobileBy.AccessibilityId("UICatalog"));
    assertTrue(elements.size() > 0);
  }
}
