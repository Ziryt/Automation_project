package web.Saucedemo.PageLogin;

import core.Field;
import core.Log;
import org.openqa.selenium.WebDriver;

public class PageLogin extends Field {

    private final String users = "src/main/web/Saucedemo/PageLogin/Data.csv";
    WebDriver driver;

    public PageLogin(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username) {
        set(username, "Username input");
        set(data(username), "Password input");
        click("Login button");
    }

    public void verifyLoginPage() {
        Log.check("Check that current page is login page", isDisplayed("Login button"));
    }


}
