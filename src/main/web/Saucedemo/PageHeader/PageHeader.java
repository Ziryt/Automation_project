package web.Saucedemo.PageHeader;

import core.Field;
import core.Log;

public class PageHeader extends Field {


    public void checkCartBadge(int amount){
        Log.check("Check that cart budge has right amount of items", getText("Cart Badge"), amount);
    }

    public void clickCartButton(){
        click("Cart");
    }

    private void clickBurgerMenu(){
        click("Burger menu", 1500);
    }

    public void goToInventoryPage(){
        clickBurgerMenu();
        click("Inventory link");
    }

}
