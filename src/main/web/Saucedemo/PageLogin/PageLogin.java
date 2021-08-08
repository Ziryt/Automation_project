package web.Saucedemo.PageLogin;

import core.Field;
import core.Log;

public class PageLogin extends Field {

    public void login(String username) {
        set(username, "Username input");
        set(data(username), "Password input");
        click("Login button");
    }

    public void verifyLoginPage() {
        Log.check("Check that current page is login page", isDisplayed("Login button"));
    }


}
