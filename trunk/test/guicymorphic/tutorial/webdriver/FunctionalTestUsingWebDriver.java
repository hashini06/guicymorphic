package guicymorphic.tutorial.webdriver;

import junit.framework.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 13, 2010
 * Time: 11:41:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class FunctionalTestUsingWebDriver {

    @Test
    public void checkConversionOnLoseFocus() throws InterruptedException {

        // Create a new instance of the driver
        // driver can be HtmlUnit (buggy JavaScript support but very fast and non-ui)
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.

        // see http://code.google.com/p/selenium/wiki/NextSteps for more

        // this creates a new anonymous firefox profile
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        // enable native events to be able to sendKeys such as tab and enter
        firefoxProfile.setEnableNativeEvents(true);

        // We will be using firefox driver
        // this will actually run the Firefox browser you will see it
        WebDriver driver = new FirefoxDriver(firefoxProfile);

        // And now use this to visit to go to the started jetty server
        driver.get("http://localhost:8080/mvp/MvpTutorial.html");

        // Find the text input element by class name
        // we can use id, xpath etc
        List<WebElement> element = driver.findElements(By.className("gwt-TextBox"));

        // expecting 4 gwt text boxes on the page
        Assert.assertEquals(4, element.size());

        // first element found should be the celsius text box
        WebElement celsiusText = element.get(0);
        // the second is the fahrenheit
        WebElement fahrenheitText = element.get(1);

        // lets send "23" and the tab
        // not if we hadn't enabled native event the tab would not be sent properly
        // I expect the guys to fix this in the final release of Selenium 2.x
        celsiusText.sendKeys("23\t");

        // just in case give the browser some time
        // and the user to see that something is happening
        Thread.sleep(500);
        // get the value of the fahrenheit
        String value = fahrenheitText.getValue();

        // we expect the on change being invoked  and setting the proper value
        Assert.assertEquals("73", value);


        // send keys the fahrenheit
        fahrenheitText.sendKeys("100\t");

        // and expecting the celsius to be changed
        Assert.assertEquals("38", celsiusText.getValue());

        // quit the browser
        driver.quit();
    }
}
