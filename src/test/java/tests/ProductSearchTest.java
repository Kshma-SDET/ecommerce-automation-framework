package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.BaseTest;

public class ProductSearchTest extends BaseTest {

    private static final String BASE_URL = "https://www.saucedemo.com";

    @Test(priority = 1, description = "Verify home page loads successfully")
    public void verifyHomePageLoads() {
        driver.get(BASE_URL);
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Swag Labs"),
            "Home page title mismatch");
    }

    @Test(priority = 2, description = "Verify user can login successfully")
    public void verifySuccessfulLogin() {
        driver.get(BASE_URL);
        driver.findElement(
            org.openqa.selenium.By.id("user-name")).sendKeys("standard_user");
        driver.findElement(
            org.openqa.selenium.By.id("password")).sendKeys("secret_sauce");
        driver.findElement(
            org.openqa.selenium.By.id("login-button")).click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory"),
            "Login failed - inventory page not loaded");
    }

    @Test(priority = 3, description = "Verify product is visible in inventory")
    public void verifyProductListing() {
        driver.get(BASE_URL);
        driver.findElement(
            org.openqa.selenium.By.id("user-name")).sendKeys("standard_user");
        driver.findElement(
            org.openqa.selenium.By.id("password")).sendKeys("secret_sauce");
        driver.findElement(
            org.openqa.selenium.By.id("login-button")).click();

        boolean productVisible = driver.findElement(
            org.openqa.selenium.By.className("inventory_list"))
            .isDisplayed();
        Assert.assertTrue(productVisible,
            "Product inventory not visible");
    }

    @Test(priority = 4, description = "Verify product can be added to cart")
    public void verifyAddToCart() {
        driver.get(BASE_URL);
        driver.findElement(
            org.openqa.selenium.By.id("user-name")).sendKeys("standard_user");
        driver.findElement(
            org.openqa.selenium.By.id("password")).sendKeys("secret_sauce");
        driver.findElement(
            org.openqa.selenium.By.id("login-button")).click();

        driver.findElement(
            org.openqa.selenium.By.id("add-to-cart-sauce-labs-backpack"))
            .click();

        String cartCount = driver.findElement(
            org.openqa.selenium.By.className("shopping_cart_badge"))
            .getText();
        Assert.assertEquals(cartCount, "1",
            "Cart count mismatch after adding product");
    }
}
