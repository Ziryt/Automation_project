package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import web.Web;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Field extends Web {

    private static final JavascriptExecutor js = (JavascriptExecutor) driver;

    public void set(String value, String element) {
        set(value, element, null);
    }

    public void set(int value, String element, String item) {
        set(String.valueOf(value), element, item);
    }

    private static void set(String value, String element, String replacement) {
        String locator = CSV.getLocator(element);
        if (replacement != null)
            locator = locator.replace("#", replacement);
        Log.log("Set value: " + value + " to: " + locator);
        findElement(locator).sendKeys(value);
    }

    public static void click(String element) {
        click(element, null);
    }

    public static void click(String element, String naming) {
        String locator = CSV.getLocator(element);
        if (naming != null) {
            locator = locator.replace("#", naming);
        }
        Log.log("Click at: " + locator);
        findElement(locator).click();
    }

    public static boolean isDisplayed(String element) {
        String locator = CSV.getLocator(element);
        Log.log("Check that element: " + locator + " is displayed");
        return findElement(locator).isDisplayed();
    }

    public static String data(String value) {
        return data(value, "Data", null);
    }

    public static String data(String value, String key) {
        return data(value, "Data", key);
    }

    public static String data(String value, String filename, String key) {
        return CSV.getData(value, filename, key);
    }

    public static String getText(String element) {
        String locator = CSV.getLocator(element);
        Log.log("Get text from: " + locator + " is displayed");
        return findElement(locator).getText();
    }

    private static List<?> getListOf(String element, boolean replacement) {
        String locator = CSV.getLocator(element);
        List<WebElement> elements = driver.findElements(By.xpath(locator));
        Log.log("Receiving list from " + elements);
        if (!replacement) {
            List<String> items_list = new ArrayList<>();
            for (WebElement webElement : elements){
                highlightOn(webElement);
                items_list.add(webElement.getText());
            }
            return items_list;
        } else {
            List<Float> items_list = new ArrayList<>();
            for (WebElement webElement : elements)
            {
                highlightOn(webElement);
                items_list.add(Float.parseFloat(webElement.getText().replace("$", "")));
            }
            return items_list;
        }
    }

    public static List<String> getListOfItems(String element) {
        return (List<String>) getListOf(element, false);
    }

    public static List<Float> getListOfItemsPrices(String element) {
        return (List<Float>) getListOf(element, true);
    }

    public static void selectDropDownValue(String dropdown, String value) {
        String locator = CSV.getLocator(dropdown);
        Select select = new Select(findElement(locator));
        Log.log("Select value: " + value + " in: " + locator);
        select.selectByValue(value);
    }

    public static List<String> getAllOptions(String dropdown) {
        String locator = CSV.getLocator(dropdown);
        Select select = new Select(findElement(locator));
        Log.log("Receiving all options from: " + locator);
        List<WebElement> elements = select.getOptions();
        List<String> options = new ArrayList<>();
        for (WebElement element : elements) {
            highlightOn(element);
            options.add(element.getText());
        }
        return options;
    }

    private static void highlightOn(WebElement element){
        js.executeScript("arguments[0].setAttribute('style', 'outline: 2px solid lime;');", element);
        js.executeScript("setTimeout(() => arguments[0].removeAttribute('style', 'outline: 2px solid lime;'), 500)", element);
    }

    private static WebElement findElement(String locator){
        WebElement element = driver.findElement(By.xpath(locator));
        highlightOn(element);
        return element;
    }


}
