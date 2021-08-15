package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import web.Web;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Field extends Web {

    private static final JavascriptExecutor js = (JavascriptExecutor) driver;

    protected void set(String value, String element) {
        set(value, element, null);
    }

    protected void set(int value, String element, String item) {
        set(String.valueOf(value), element, item);
    }

    private void set(String value, String element, String replacement) {
        String locator = CSV.getLocator(element);
        if (replacement != null)
            locator = locator.replace("#", replacement);
        Log.log("Set value: " + value + " to: " + locator);
        findByXpath(locator).sendKeys(value);
    }

    protected void click(String element) {
        click(element, null);
    }

    protected void click(String element, String naming) {
        String locator = CSV.getLocator(element);
        if (naming != null) {
            locator = locator.replace("#", naming);
        }
        Log.log("Click at: " + locator);
        findByXpath(locator).click();
    }

    protected boolean isDisplayed(String element) {
        String locator = CSV.getLocator(element);
        Log.log("Check that element: " + locator + " is displayed");
        return findByXpath(locator).isDisplayed();
    }

    protected String data(String value) {
        return data(value, "Data", null);
    }

    protected String data(String value, String key) {
        return data(value, "Data", key);
    }

    protected String data(String value, String filename, String key) {
        return CSV.getData(value, filename, key);
    }

    protected String getText(String element) {
        String locator = CSV.getLocator(element);
        Log.log("Get text from: " + locator + " is displayed");
        return findByXpath(locator).getText();
    }

    protected List<?> getListOf(String element, boolean replacement) {
        String locator = CSV.getLocator(element);
        List<WebElement> elements = findListByXpath(locator);
        Log.log("Receiving list from " + elements);
        if (!replacement) {
            List<String> items_list = new ArrayList<>();
            for (WebElement webElement : elements) {
                highlightOn(webElement);
                items_list.add(webElement.getText());
            }
            return items_list;
        } else {
            List<Float> items_list = new ArrayList<>();
            for (WebElement webElement : elements) {
                highlightOn(webElement);
                items_list.add(Float.parseFloat(webElement.getText().replace("$", "")));
            }
            return items_list;
        }
    }

    protected List<String> getListOfItems(String element) {
        return (List<String>) getListOf(element, false);
    }

    protected List<Float> getListOfItemsPrices(String element) {
        return (List<Float>) getListOf(element, true);
    }

    protected void selectDropDownValue(String dropdown, String value) {
        String locator = CSV.getLocator(dropdown);
        Select select = new Select(findByXpath(locator));
        Log.log("Select value: " + value + " in: " + locator);
        select.selectByValue(value);
    }

    protected List<String> getAllOptions(String dropdown) {
        String locator = CSV.getLocator(dropdown);
        Select select = new Select(findByXpath(locator));
        Log.log("Receiving all options from: " + locator);
        List<WebElement> elements = select.getOptions();
        List<String> options = new ArrayList<>();
        for (WebElement element : elements) {
            highlightOn(element);
            options.add(element.getText());
        }
        return options;
    }

    private void highlightOn(WebElement element) {
        js.executeScript("arguments[0].setAttribute('style', 'outline: 2px solid lime;');", element);
        js.executeScript("setTimeout(() => arguments[0].removeAttribute('style', 'outline: 2px solid lime;'), 500)", element);
    }

    private WebElement findByXpath(String locator) {
        WebElement element;
        try {
            element = driver.findElement(By.xpath(locator));
            highlightOn(element);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(locator + " element not found");
        }
        return element;
    }

    private List<WebElement> findListByXpath(String locator) {
        List<WebElement> list = new ArrayList<>();
        try {
            list = driver.findElements(By.xpath(locator));
        } catch (NoSuchElementException e) {
            System.err.println(locator + " element not found");
            e.printStackTrace();
        }
        return list;
    }


}
