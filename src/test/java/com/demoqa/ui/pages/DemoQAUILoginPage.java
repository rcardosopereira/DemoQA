package com.demoqa.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DemoQAUILoginPage {

    private WebDriver driver;

    private By loginButton = By.id("login");
    private By userNameField = By.id("userName");
    private By passwordField = By.id("password");

    public DemoQAUILoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.findElement(loginButton).click();
        driver.findElement(userNameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
