package web.Saucedemo.PageCheckoutComplete;

import core.Field;
import core.Log;

public class PageCheckoutComplete extends Field {

    public void verifyCheckoutCompletePage() {
        Log.check("Check that current page is completed checkout", isDisplayed("Pony express logo"));
    }

}
