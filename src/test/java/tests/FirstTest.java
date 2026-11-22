package tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

public class FirstTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
    }

    @AfterAll
    static void closeBrowser() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void createPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void validLogin() {
        page.navigate("https://www.saucedemo.com");
        page.getByPlaceholder("Username").fill("standard_user");
        page.getByPlaceholder("Password").fill("secret_sauce");
        page.getByRole(AriaRole.BUTTON,
            new Page.GetByRoleOptions().setName("Login")).click();
        assertThat(page.locator(".title")).hasText("Products");
        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
      
    }

    @Test
    void lockedUser() {
        page.navigate("https://www.saucedemo.com");
        page.getByPlaceholder("Username").fill("locked_out_user");
        page.getByPlaceholder("Password").fill("secret_sauce");
        page.locator("#login-button").click();
        assertThat(page.locator("[data-test='error']")).containsText("locked out");
    }
    
    @Test
    void wrongPassword() {
        page.navigate("https://www.saucedemo.com");
        page.getByPlaceholder("Username").fill("standard_user");
        page.getByPlaceholder("Password").fill("abc123");
        page.locator("#login-button").click();
        assertThat(page.locator("[data-test='error']")).containsText("do not match");
    }
    
    @Test
    void emptyLogin() {
    	page.navigate("https://www.saucedemo.com");
    	page.locator("#login-button").click();
    	assertThat(page.locator("[data-test='error']")).containsText("Username is required");
    }
}