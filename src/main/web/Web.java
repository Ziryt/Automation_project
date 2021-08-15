package web;

import core.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Web {

    public static WebDriver driver = null;

    @BeforeSuite
    public void initiate() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Parameters({"url"})
    @BeforeMethod
    public void open(String url) {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterMethod
    public void close() {
        Log.assertAll();
    }

    @AfterSuite
    public void quit() {
        driver.quit();
    }


}
