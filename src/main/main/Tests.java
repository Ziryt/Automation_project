package main;

import core.Log;
import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import web.Saucedemo.PageCart.PageCart;
import web.Saucedemo.PageInventory.PageInventory;
import web.Saucedemo.PageLogin.PageLogin;
import web.Saucedemo.PageHeader.PageHeader;
import web.Web;

import java.util.ArrayList;
import java.util.List;

public class Tests extends Web {

    @Test(description = "Sausedemo: Filtering")
    public void SD_Filtering() {
        Log.step("Preconditions");
        PageLogin pageLogin = PageFactory.initElements(driver, PageLogin.class);
        pageLogin.verifyLoginPage();
        pageLogin.login("standard_user");
        Log.step("1.Observe products default sort on page");
        PageInventory pageInventory = PageFactory.initElements(driver, PageInventory.class);
        pageInventory.verifyInventoryPage();
        pageInventory.checkDefaultSorting();
        pageInventory.checkItemsOrderAZ();
        Log.step("2.Check available types of sorting");
        pageInventory.checkAvailableSorting();
        Log.step("3.Change sorting type to \"Name (z to a)\"");
        pageInventory.filterByNameZA();
        pageInventory.checkItemsOrderZA();
        Log.step("4.Change sorting type to \"price low to high\"");
        pageInventory.filterByPriceLH();
        pageInventory.checkItemsOrderLH();
        Log.step("5.Change sorting type to \"price low to high\"");
        pageInventory.filterByPriceHL();
        pageInventory.checkItemsOrderHL();
    }

    @Test(description = "Sausedemo: Shopping cart")
    public void SD_Shopping_Cart() throws InterruptedException {
        Log.step("Preconditions");
        PageLogin pageLogin = PageFactory.initElements(driver, PageLogin.class);
        pageLogin.verifyLoginPage();
        pageLogin.login("standard_user");
        Log.step("1.Add 2 items to cart");
        PageInventory pageInventory = PageFactory.initElements(driver, PageInventory.class);
        pageInventory.verifyInventoryPage();
        List<String> items = new ArrayList<>();
        items.add("Sauce Labs Bike Light");
        items.add("Sauce Labs Backpack");
        pageInventory.addItemsToCart(items);
        PageHeader pageHeader = PageFactory.initElements(driver, PageHeader.class);
        pageHeader.checkCartBadge(2);
        Log.step("2.Click on shopping cart icon");
        pageHeader.clickCartButton();
        Log.step("3.Check that items in cart correspond to selected previously");
        PageCart pageCart = PageFactory.initElements(driver, PageCart.class);
        pageCart.verifyCartPage();
        pageCart.checkItemsInCart(items);
        Thread.sleep(500);
        Log.step("4.Remove 1 item from cart");
        items = pageCart.removeItemsFromCart(items, 1);
        Thread.sleep(500);
        Log.step("5.Go back to inventory page, verify that item deleted from cart properly");
        pageHeader.goToInventoryPage();
        Thread.sleep(500);
        pageInventory.verifyInventoryPage();
        Thread.sleep(500);
        pageInventory.checkRemoveButton(items);
        Thread.sleep(500);
        Log.step("6.Click on cart, change quantity of item to 2");
        pageHeader.clickCartButton();
        pageCart.verifyCartPage();
        Thread.sleep(20000);
        //next method will try to change item quantity, but Sausedemo do not have possibility to do it, so test will fail
        pageCart.changeItemQuantity(2, "Sauce Labs Backpack");
    }


}
