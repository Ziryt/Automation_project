package web.Saucedemo.PageInventory;

import core.Field;
import core.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PageInventory extends Field {


    public void verifyInventoryPage(){
        Log.check("Check that current page is inventory page", isDisplayed("Robot pic"));
    }

    public void filterByPriceLH(){
        selectDropDownValue("Sort dropdown", "lohi");
    }

    public void filterByPriceHL(){
        selectDropDownValue("Sort dropdown", "hilo");
    }

    public void filterByNameAZ(){
        selectDropDownValue("Sort dropdown", "az");
    }

    public void filterByNameZA(){
        selectDropDownValue("Sort dropdown", "za");
    }

    public void checkDefaultSorting(){
        Log.check("Check default sorting type", getText("Selected sorting"), data("A-Z sorting"));
    }

    public void checkAvailableSorting(){
        Log.check("Check all options in sort dropdown", getAllOptions("Sort dropdown"), Arrays.asList(data("Available sorting").split(", ")));
    }

    public void checkSelectedSorting(){
       // Log.check("Checking selected sorting type", getText("Selected sorting"));
    }

    public void checkItemsOrderAZ(){
        List<String> list_actual = getListOfItems("Item names");
        List<String> list_expected = getListOfItems("Item names");
        Collections.sort(list_expected);
        Log.check("Check that items sorted by name (a-z)", list_actual, list_expected);
    }

    public void checkItemsOrderZA(){
        List<String> list_expected = getListOfItems("Item names");
        List<String> list_actual = getListOfItems("Item names");
        Collections.sort(list_expected);
        Collections.reverse(list_expected);
//        List<String> list_actual = new ArrayList<>();
//        list_actual.add("Sauce Labs Backpack");
//        list_actual.add("Sauce Labs Bike Light");
//        list_actual.add("Sauce Labs Fleece Jacket");
//        list_actual.add("Sauce Labs Onesie");
//        list_actual.add("Sauce Labs Bolt T-Shirt");
//        list_actual.add("Test.allTheThings() T-Shirt (Red) ");
        Log.check("Check that items sorted by name (z-a)", list_actual, list_expected);
    }

    public void checkItemsOrderLH(){
        List<Float> list_actual = getListOfItemsPrices("Item prices");
        List<Float> list_expected = getListOfItemsPrices("Item prices");
        Collections.sort(list_expected);
        Log.check("Check that items sorted by name (low to high)", list_actual, list_expected);
    }

    public void checkItemsOrderHL(){
        List<Float> list_actual = getListOfItemsPrices("Item prices");
        List<Float> list_expected = getListOfItemsPrices("Item prices");
        Collections.sort(list_expected);
        Collections.reverse(list_expected);
        Log.check("Check that items sorted by price (high to low)", list_actual, list_expected);
    }

    public void addItemsToCart(List<String> items) {
        for (String item:items){
            click("Add to cart", item.toLowerCase().trim().replace(" ", "-"));
        }
    }

    public void checkRemoveButton(List<String> items){
        List<String> list_actual = getListOfItems("Added items");
        Log.check("Check that items were removed correctly", list_actual, items);
    }


}
