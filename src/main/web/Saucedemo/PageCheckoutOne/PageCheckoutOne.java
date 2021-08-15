package web.Saucedemo.PageCheckoutOne;

import core.Field;
import core.Log;

public class PageCheckoutOne extends Field {

    public void verifyCheckoutOnetPage() {
        Log.check("Check that current page is fisrt step of checkout", isDisplayed("Zip code"));
    }

    public void setFirstName(String name) {
        set(name, "First Name");
    }

    public void setLastName(String name) {
        set(name, "Last Name");
    }

    public void setZipCode(String code) {
        set(code, "Zip code");
    }

    public void setCustomer(String first, String last, String code) {
        setFirstName(first);
        setLastName(last);
        setZipCode(code);
    }

    public void nestPage() {
        click("Continue");
    }


}
