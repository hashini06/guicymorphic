package guicymorphic.examples.webdriver;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.lang.Thread.sleep;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 13, 2010
 * Time: 11:41:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestMvpWidgetsUsingWebDriver {

    @Test
    public void checkConversionOnLoseFocus() throws InterruptedException, IOException {

        // Create a new instance of the driver
        // driver can be HtmlUnit (buggy JavaScript support but very fast and non-ui)
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.

        // see http://code.google.com/p/selenium/wiki/NextSteps for more

        // this creates a new anonymous firefox profile
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        // enable native events to be able to sendKeys such as tab and enter
        firefoxProfile.setEnableNativeEvents(true);
        // the Firebug plugin for firefox is next to the jar that holds the WebDriver.class
        // just load the firefox extension relative to webdriver files
        // very cool API
        try {
            firefoxProfile.addExtension(WebDriver.class,"addon-1843-latest.xpi");
        } catch (IOException e) {
            // works from IDE but fails under ant...am missing something
        }

        // We will be using firefox driver
        // this will actually run the Firefox browser you will see it
        WebDriver driver = new FirefoxDriver(firefoxProfile);

        // And now use this to visit to go to the started jetty server
        driver.get("http://localhost:8080/examples/EventBusExample.html");

        // Find the text input element by class name
        // we can use id, xpath etc
        List<WebElement> element = driver.findElements(By.className("gwt-TextBox"));

        // expecting 2 gwt text boxes on the page
        assertEquals(2, element.size());

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
        sleep(500);
        // get the value of the fahrenheit
        String value = fahrenheitText.getValue();

        // we expect the on change being invoked  and setting the proper value
        assertEquals("73", value);


        // send keys the fahrenheit
        fahrenheitText.sendKeys("100\t");

        // and expecting the celsius to be changed
        assertEquals("38", celsiusText.getValue());

        // check if table was updated properly

        List<WebElement> tds = driver.findElements(By.tagName("td"));
        // there should be the sequence C , F, 23, 73, 38, 100, 12 somewhere
        List<String> tableContent = Arrays.asList("C", "F", "23", "73", "38", "100");

        // could make high level api for containing the content of a table etc...
        Iterator<WebElement> tdIterator = tds.iterator();
        while (tdIterator.hasNext()) {
            WebElement webElement = tdIterator.next();
            // we've found start of the table sequence
            if ("C".equalsIgnoreCase(webElement.getText())) {
                for (String target : tableContent) {
                    if (!target.equals(webElement.getText())) {
                        fail("Failed at comparing " + target + " with " + webElement.getText());
                    }
                    try {
                        webElement = tdIterator.next();
                    } catch (Exception e) {}
                }
            }
        }

        // now test history support
        sleep(500);
        driver.navigate().back();
        sleep(500);
        // expecting 23 and 73
        assertEquals("23", celsiusText.getValue());
        assertEquals("73", fahrenheitText.getValue());
        driver.navigate().forward();
        sleep(500);
         // expecting 38 and 100
        assertEquals("38", celsiusText.getValue());
        assertEquals("100", fahrenheitText.getValue());

        // quit the browser
        driver.quit();
    }
}
