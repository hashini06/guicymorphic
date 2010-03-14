package guicymorphic.examples.webdriver;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Simples example using WebDriver. From <a href="http://code.google.com/p/selenium/wiki/GettingStarted">Getting Started
 * Guide</a>.
 *
 * @author Alen Vrecko
 */
public class HelloWorldTest {


    @Test
    public void helloWorld() throws InterruptedException {
        // Create a new instance of the HtmlUnit Driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = new HtmlUnitDriver();
//        WebDriver driver = new FirefoxDriver();
        // And now use this to visit Google
        driver.get("http://www.google.com/");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // wait for the WebDriver to catch up
        Thread.sleep(100);

        // Check the title of the page
        assertTrue(driver.getTitle().startsWith("Cheese!"));

        driver.quit();
    }

}
