package TestCases;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

@Test
public class Scenario1 {

  public static AndroidDriver<MobileElement> driver;

  /**
   * This method sets all the Appium capabilities and performs the testcase on device
   * 
   * @throws MalformedURLException when the url provided is wrong or not valid
   */
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

    driver.findElement(By.id("Home")).click();

    driver.findElement(By.id("com.climate.farmrise:id/checkWeatherView")).click();

    driver.findElementByXPath("//android.widget.TextView[@text='Now']").isDisplayed();

    MobileElement banner = driver.findElement(By.className("android.widget.HorizontalScrollView"));

    Point bannerPoint = banner.getLocation();
    Dimension screenSize = driver.manage().window().getSize();
    int startX = Math.toIntExact(Math.round(screenSize.getWidth() * 0.8));
    int endX = 10;

    TouchAction action = new TouchAction(driver);
    action.press(PointOption.point(startX, bannerPoint.getY()))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
        .moveTo(PointOption.point(endX, bannerPoint.getY())).release();
    driver.performTouchAction(action);

    boolean flag = false;

    while (!flag) {
      try {
        driver
            .findElementByXPath(
                "//android.widget.HorizontalScrollView//android.widget.LinearLayout[@index='23']")
            .isDisplayed();

        flag = true;
      } catch (Exception ioException) {

        action.press(PointOption.point(startX, bannerPoint.getY()))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
            .moveTo(PointOption.point(endX, bannerPoint.getY())).release();
        driver.performTouchAction(action);
      }

    }
  }
}
