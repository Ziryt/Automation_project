package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import web.Web;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Field extends Web {

    public static void set(int value, String element, String item) {
        String locator = CSV.getLocator(element).replace("#", item);
        Log.log("Set value: " + value + " to:" + locator);
        driver.findElement(By.xpath(locator)).sendKeys(String.valueOf(value));
        wait(500);
    }

    public static void set(String value, String element) {
        String locator = CSV.getLocator(element);
        Log.log("Set value: " + value + " to: " + locator);
        driver.findElement(By.xpath(locator)).sendKeys(value);
        wait(500);
    }

    public static void click(String element, String naming) {
        String locator = CSV.getLocator(element).replace("#", naming);
        driver.findElement(By.xpath(locator)).click();
        Log.log("Click at: " + locator);
        wait(500);
    }

    public static void click(String element) {
        String locator = CSV.getLocator(element);
        driver.findElement(By.xpath(locator)).click();
        Log.log("Click at: " + locator);
        wait(500);
    }

    public static boolean isDisplayed(String element) {
        wait(500);
        String locator = CSV.getLocator(element);
        Log.log("Check that element: " + locator + " is displayed");
        return driver.findElement(By.xpath(locator)).isDisplayed();
    }

    public static String data(String value) {
        wait(500);
        return CSV.getData(value, "Data");
    }

    public static String data(String value, String filename) {
        wait(500);
        return CSV.getData(value, filename);
    }

    public static String getText(String element) {
        String locator = CSV.getLocator(element);
        Log.log("Get text from: " + locator + " is displayed");
        return driver.findElement(By.xpath(locator)).getText();
    }

    public static List<String> getListOfItems(String element) {
        String locator = CSV.getLocator(element);
        List<WebElement> elements = driver.findElements(By.xpath(locator));
        Log.log("Receiving list of items from " + elements);
        List<String> item_names = new ArrayList<>();
        for (WebElement webElement : elements) {
            item_names.add(webElement.getText());
        }
        return item_names;
    }

    public static List<Float> getListOfItemsPrices(String element) {
        String locator = CSV.getLocator(element);
        List<WebElement> elements = driver.findElements(By.xpath(locator));
        Log.log("Receiving list of items from " + elements);
        List<Float> item_prices = new ArrayList<>();
        for (WebElement webElement : elements) {
            item_prices.add(Float.parseFloat(webElement.getText().replace("$", "")));
        }
        return item_prices;
    }

    public static void selectDropDownValue(String dropdown, String value) {
        String locator = CSV.getLocator(dropdown);
        Select select = new Select(driver.findElement(By.xpath(locator)));
        Log.log("Select value: " + value + " in: " + locator);
        select.selectByValue(value);
        wait(500);
    }

    public static List<String> getAllOptions(String dropdown) {
        String locator = CSV.getLocator(dropdown);
        Select select = new Select(driver.findElement(By.xpath(locator)));
        Log.log("Receiving all options from: " + locator);
        List<WebElement> elements = select.getOptions();
        List<String> options = new ArrayList<>();
        for (WebElement element : elements) {
            options.add(element.getText());
        }
        return options;
    }

    public static void wait(int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);
    }


}
