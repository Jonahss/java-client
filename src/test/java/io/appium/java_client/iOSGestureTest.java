/*
 +Copyright 2014 Appium contributors
 +Copyright 2014 Software Freedom Conservancy
 +
 +Licensed under the Apache License, Version 2.0 (the "License");
 +you may not use this file except in compliance with the License.
 +You may obtain a copy of the License at
 +
 +     http://www.apache.org/licenses/LICENSE-2.0
 +
 +Unless required by applicable law or agreed to in writing, software
 +distributed under the License is distributed on an "AS IS" BASIS,
 +WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 +See the License for the specific language governing permissions and
 +limitations under the License.
 + */

package io.appium.java_client;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;

/**
 * Test Mobile Driver features
 */
public class iOSGestureTest {

  private AppiumDriver driver;

  @Before
  public void setup() throws Exception {
    File appDir = new File("src/test/java/io/appium/java_client");
    File app = new File(appDir, "TestApp.app.zip");
    String appOnGithub = "https://github.com/appium/java-client/raw/master/src/test/java/io/appium/java_client/TestApp.app.zip";

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
    capabilities.setCapability("name", "java-client: iOSGestureTest");
    capabilities.setCapability("appium-version", "1.2.2");

    driver = new AppiumDriver(serverUrl, capabilities);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }

  @Test
  public void TouchActionTest() throws InterruptedException {
    WebElement button = driver.findElementsByClassName("UIAButton").get(3);
    TouchAction action = new TouchAction(driver);
    action.press(button).perform();
    Thread.sleep(2000);
  }

  @Test
  public void TouchActionChainTest() throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, 2);

    WebElement button = driver.findElementsByClassName("UIAButton").get(5);
    TouchAction action = new TouchAction(driver);
    action.press(button).perform();

    wait.until(ExpectedConditions.alertIsPresent());
    Alert alert = driver.switchTo().alert();
    alert.accept();

    WebElement mapview = driver.findElementByXPath("//UIAWindow[1]/UIAMapView[1]");
    action = new TouchAction(driver);
    action.press(mapview).moveTo(mapview, 0, 100).release().perform();
    Thread.sleep(2000);
  }

  @Test
  public void MultiGestureTest() throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, 2);

    WebElement button = driver.findElementsByClassName("UIAButton").get(5);
    TouchAction action = new TouchAction(driver);
    action.press(button).perform();

    wait.until(ExpectedConditions.alertIsPresent());
    Alert alert = driver.switchTo().alert();
    alert.accept();

    WebElement mapview = driver.findElementByXPath("//UIAWindow[1]/UIAMapView[1]");

    MultiTouchAction multiTouch = new MultiTouchAction(driver);
    TouchAction action0 = new TouchAction(driver).press(mapview, 100, 0).moveTo(mapview, 0,-80).release();
    TouchAction action1 = new TouchAction(driver).press(mapview, 100, 50).moveTo(mapview, 0,80).release();
    multiTouch.add(action0).add(action1).perform();
    Thread.sleep(2000);
  }

  @Test
  public void ZoomTest() throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, 2);

    WebElement button = driver.findElementsByClassName("UIAButton").get(5);
    TouchAction action = new TouchAction(driver);
    action.press(button).perform();

    wait.until(ExpectedConditions.alertIsPresent());
    Alert alert = driver.switchTo().alert();
    alert.accept();

    WebElement mapview = driver.findElementByXPath("//UIAWindow[1]/UIAMapView[1]");

    driver.zoom(mapview);
    Thread.sleep(2000);
  }

  @Test
  public void TapSingleFingerTest() {
    driver.tap(1,100,200,1000);
  }
}
