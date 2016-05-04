/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.java_client.android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import io.appium.java_client.MobileBy;
import org.junit.Before;
import org.junit.Test;

public class AndroidElementTest extends BaseAndroidTest {

    @Before public void setup() throws Exception {
        driver.startActivity("io.appium.android.apis", ".ApiDemos");
    }


    @Test public void findByAccessibilityIdTest() {
        assertNotEquals(driver.findElementById("android:id/content")
            .findElement(MobileBy.AccessibilityId("Graphics")).getText(), null);
        assertEquals(driver.findElementById("android:id/content")
            .findElements(MobileBy.AccessibilityId("Graphics")).size(), 1);
    }

    @Test public void findByAndroidUIAutomatorTest() {
        assertNotEquals(driver.findElementById("android:id/content")
            .findElement(MobileBy
                .AndroidUIAutomator("new UiSelector().clickable(true)")).getText(), null);
        assertNotEquals(driver.findElementById("android:id/content")
            .findElements(MobileBy
                .AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 0);
        assertNotEquals(driver.findElementById("android:id/content")
            .findElements(MobileBy
                .AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 1);
    }

    @Test public void replaceValueTest() {
        String originalValue = "original value";

        driver.startActivity("io.appium.android.apis", ".view.Controls1");
        AndroidElement editElement = driver
            .findElementByAndroidUIAutomator("resourceId(\"io.appium.android.apis:id/edit\")");
        editElement.sendKeys(originalValue);
        assertEquals(originalValue, editElement.getText());
        String replacedValue = "replaced value";
        editElement.replaceValue(replacedValue);
        assertEquals(replacedValue, editElement.getText());
    }
}