package steps;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.regex.Pattern;

import hooks.Hooks;
import io.cucumber.java.en.*;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginSteps {
    private LoginPage loginPage;

    @Given("I am on the login page")
    public void openLogin() {
        loginPage = new LoginPage(Hooks.getPage());
        loginPage.open();
    }

    @When("I login with {string} and {string}")
    public void login(String user, String pass) {
        loginPage.login(user, pass);
    }

    @Then("I should see the products page")
    public void productsPage() {
        assertThat(loginPage.pageTitle()).hasText("Products");
    }

    @Then("I should see the error {string}")
    public void errorShown(String msg) {
        assertThat(loginPage.errorMessage()).containsText(msg);
    }

    @When("I add the product {string} to the cart")
    public void addProduct(String product) {
        new InventoryPage(Hooks.getPage()).addToCart(product);
    }

    @Then("the cart badge should show {string}")
    public void badgeShows(String count) {
        assertThat(new InventoryPage(Hooks.getPage()).cartBadge()).hasText(count);
    }
    
    @Then("the page url should contain {string}")
    public void the_page_url_should_contain(String text) {
    	assertThat(Hooks.getPage()).hasURL(Pattern.compile(".*" + text + ".*"));
    }
    
    @Then("I should see {int} products on the page")
    public void I_should_see_products_on_the_page(Integer count) {
    	assertThat(new InventoryPage(Hooks.getPage()).products()).hasCount(count);
    }
    
    @When("I open the cart")
    public void i_open_the_cart() {
        new InventoryPage(Hooks.getPage()).openCart();
    }
    
    @When("I remove the product {string} from the cart")
    public void remove_the_product_from_cart(String product) {
        new InventoryPage(Hooks.getPage()).Remove(product);
    }
    
    @Then("the cart badge should be empty")
    public void cart_badge_empty(){
        assertThat(new InventoryPage(Hooks.getPage()).cartBadge()).hasCount(0);
    }
    
}