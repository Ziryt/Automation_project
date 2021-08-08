package web.Saucedemo.PageInventory;

import core.Field;
import core.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PageInventory extends Field {


    public void verifyInventoryPage() {
        Log.check("Check that current page is inventory page", isDisplayed("Robot pic"));
    }

    public void filterByPrice(boolean ascending) {
        selectDropDownValue("Sort dropdown", ascending ? "lohi" : "hilo");
    }

    public void filterByName(boolean ascending) {
        selectDropDownValue("Sort dropdown", ascending ? "az" : "za");
    }

    public void checkDefaultSorting() {
        Log.check("Check default sorting type", getText("Selected sorting"), data("A-Z sorting"));
    }

    public void checkAvailableSorting() {
        Log.check("Check all options in sort dropdown", getAllOptions("Sort dropdown"), Arrays.asList(data("Available sorting").split(", ")));
    }

    public void checkItemsOrderByName(boolean ascending) {

        List<String> list_actual, list_expected;
        list_actual = list_expected = getListOfItems("Item names");
        Collections.sort(list_expected);
        String description = ascending ? "name (a to z)" : "name (z to a)";
        if (!ascending)
            Collections.reverse(list_expected);
        Log.check("Check that items sorted by " + description, list_actual, list_expected);
    }

    public void checkItemsOrderByPrice(boolean ascending) {
        List<Float> list_actual, list_expected;
        list_actual = list_expected = getListOfItemsPrices("Item prices");
        Collections.sort(list_expected);
        String description = ascending ? "price (low to high)" : "price (high to low)";
        if (!ascending)
            Collections.reverse(list_expected);
        Log.check("Check that items sorted by " + description, list_actual, list_expected);
    }

    public void addItemsToCart(List<String> items) {
        for (String item : items) {
            click("Add to cart", item.toLowerCase().trim().replace(" ", "-"));
        }
    }

    public void checkRemoveButton(List<String> items) {
        List<String> list_actual = getListOfItems("Added items");
        Log.check("Check that items were removed correctly", list_actual, items);
    }


}
