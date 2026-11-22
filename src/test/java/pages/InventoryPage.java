package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class InventoryPage {
    private final Page page;

    public InventoryPage(Page page) { this.page = page; }

    public void addToCart(String product) {
        page.locator(".inventory_item")
            .filter(new Locator.FilterOptions().setHasText(product))
            .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Add to cart"))
            .click();
    }
    
    public void Remove(String product) {
        page.locator(".inventory_item")
            .filter(new Locator.FilterOptions().setHasText(product))
            .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Remove"))
            .click();
    }

    public Locator cartBadge() { return page.locator(".shopping_cart_badge"); }
    public Locator products() { return page.locator(".inventory_item"); }
    public void openCart() {page.locator(".shopping_cart_link").click();}
    
}