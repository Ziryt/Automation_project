package web.Saucedemo.PageCart;

import core.Field;
import core.Log;
import java.util.List;

public class PageCart extends Field {


    public void verifyCartPage(){
        Log.check("Check that current page is cart page", isDisplayed("Title"));
    }

    public void checkItemsInCart(List<?> items){
        List<String> list_actual = getListOfItems("Item names");
        Log.check("Check that items in cart correspond to selected previously", list_actual, items);
    }

    public List<String> removeItemsFromCart(List<String> items, int amount){
        for(int i = 0; i<amount; i++){
            click("Remove from cart", items.get(i).toLowerCase().replace(" ", "-"));
            items.remove(i);
        }
        return items;
    }

    public void changeItemQuantity(int value, String item){
        set(value, "Item quantity", item);
    }


}
