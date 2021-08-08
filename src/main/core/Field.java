package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import web.Web;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Field extends Web {

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
        driver.findElement(By.xpath(locator)).sendKeys(value);
        wait(500);
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
        driver.findElement(By.xpath(locator)).click();
        wait(500);
    }

    public static boolean isDisplayed(String element) {
        wait(500);
        String locator = CSV.getLocator(element);
        Log.log("Check that element: " + locator + " is displayed");
        return driver.findElement(By.xpath(locator)).isDisplayed();
    }

    public static String data(String value) {
        return data(value, "Data");
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

    //Redo

    private static <T> T getListOf(String element, boolean replacement) {
        String locator = CSV.getLocator(element);
        List<WebElement> elements = driver.findElements(By.xpath(locator));
        Log.log("Receiving list from " + elements);
        if (!replacement) {
            List<String> items_list = new ArrayList<>();
            for (WebElement webElement : elements)
                items_list.add(webElement.getText());
            return (T) items_list;
        } else {
            List<Float> items_list = new ArrayList<>();
            for (WebElement webElement : elements)
                items_list.add(Float.parseFloat(webElement.getText().replace("$", "")));
            return (T) items_list;
        }
    }

    public static List<String> getListOfItems(String element) {
        return getListOf(element, false);
    }

    public static List<Float> getListOfItemsPrices(String element) {
        return getListOf(element, true);
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
