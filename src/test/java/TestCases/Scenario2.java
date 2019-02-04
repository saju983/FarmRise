package TestCases;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Scenario2 {

  public static AndroidDriver<MobileElement> driver;

  /**
   * This method sets all the Appium capabilities and performs the testcase on device
   * 
   * @throws MalformedURLException when the url provided is wrong or not valid
   */
  @Test

  public void Login() throws MalformedURLException {
    DesiredCapabilities capabilitities = new DesiredCapabilities();
    capabilitities.setCapability("platformName", "Android");
    capabilitities.setCapability("deviceName", "samsung edge");
    capabilitities.setCapability("noReset", true);
    capabilitities.setCapability("appPackage", "com.climate.farmrise");
    capabilitities.setCapability("appActivity", "com.climate.farmrise.SplashScreen");
    driver =
        new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilitities);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver.findElement(By.id("More")).click();

    driver.findElement(By.id("com.climate.farmrise:id/more_govtSchemes")).click();

    TouchAction action = new TouchAction(driver);
    action.press(PointOption.point(466, 1593))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
        .moveTo(PointOption.point(492, 370)).release();
    driver.performTouchAction(action);

    driver.findElement(By.id("Search")).click();
    driver.findElement(By.id("android:id/search_src_text")).sendKeys("scheme");
    action.press(PointOption.point(986, 1805)).release().perform();

  }
}
