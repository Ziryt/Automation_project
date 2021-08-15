package web.Saucedemo.PageCheckoutTwo;

import core.Field;
import core.Log;

public class PageCheckoutTwo extends Field {

    public void verifyCheckoutTwo() {
        Log.check("Check that current page is last step of checkout", isDisplayed("Finish"));
    }

    public void finish() {
        click("Finish");
    }

}
