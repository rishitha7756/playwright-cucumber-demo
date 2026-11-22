package pages;

import com.microsoft.playwright.*;

public class LoginPage {
    private final Page page;
    private final Locator username;
    private final Locator password;
    private final Locator loginButton;
    private final Locator error;
    private final Locator title;

    public LoginPage(Page page) {
        this.page = page;
        username = page.getByPlaceholder("Username");
        password = page.getByPlaceholder("Password");
        loginButton = page.locator("#login-button");
        error = page.locator("[data-test='error']");
        title = page.locator(".title");
    }

    public void open() { page.navigate("https://www.saucedemo.com"); }

    public void login(String user, String pass) {
        username.fill(user);
        password.fill(pass);
        loginButton.click();
    }

    public Locator pageTitle() { return title; }
    public Locator errorMessage() { return error; }
}