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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test -android uiautomator locator strategy
 */
public class AndroidUIAutomatorTest {

  private AppiumDriver driver;

  @Before
  public void setup() throws Exception {
    File appDir = new File("src/test/java/io/appium/java_client");
    File app = new File(appDir, "ApiDemos-debug.apk");
    String appOnGithub = "https://github.com/appium/java-client/raw/master/src/test/java/io/appium/java_client/ApiDemos-debug.apk";

    String sauceUsername = System.getenv("SAUCE_USERNAME");
    String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");
    URL serverUrl = new URL("http://" + sauceUsername + ":" + sauceAccessKey + "@ondemand.saucelabs.com:80/wd/hub");

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
    capabilities.setCapability(MobileCapabilityType.APP, appOnGithub);

    // sauce caps
    capabilities.setCapability("name", "java-client: AndroidUIAutomatorTest");
    capabilities.setCapability("appium-version", "1.2.2");

    driver = new AppiumDriver(serverUrl, capabilities);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }

  @Test
  public void complexFind() {
    String animation = String.format("[\"scroll\",[[3,\"%1$s\"]],[[7,\"%1$s\"]]]", "animation");
    String cloning = String.format("[\"scroll\",[[3,\"%1$s\"]],[[7,\"%1$s\"]]]", "cloning");

    driver.complexFind(animation).click();
    driver.complexFind(cloning).click();
  }

  @Test
  public void scrollTo() {
    driver.scrollTo("animation").click();
    driver.scrollTo("cloning").click();
  }

  @Test
  public void scrollToExact() {
    driver.scrollToExact("Animation").click();
    driver.scrollToExact("Cloning").click();
  }


  @Test
  public void findElementTest() {
    WebElement element = driver.findElementByAndroidUIAutomator("new UiSelector().index(0)");
    assertEquals("android.widget.FrameLayout", element.getTagName());
  }

  @Test
  public void findElementsTest() {
    List<WebElement> elements = driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)");
    assertTrue(elements.size() > 11);
  }

  @Test
  public void findElementByTest() {
    WebElement element = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().index(0)"));
    assertEquals("android.widget.FrameLayout", element.getTagName());
  }

  @Test
  public void findElementsByTest() {
    List<WebElement> elements = driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)"));
    assertTrue(elements.size() > 11);
  }

  @Test
  public void findChainedElementsTest() {
	  MobileElement el1 = (MobileElement) driver.findElementByAndroidUIAutomator("resourceId(\"android:id/content\")");
	  MobileElement el2 = (MobileElement) el1.findElementByAndroidUIAutomator("text(\"Accessibility\")");
	  el2.click();
	  MobileElement el3 = (MobileElement) driver.findElementByAndroidUIAutomator("text(\"Custom View\")");
	  assertTrue(el3.isDisplayed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void ErrorTest() {
    driver.findElementByAndroidUIAutomator(null);
  }

}