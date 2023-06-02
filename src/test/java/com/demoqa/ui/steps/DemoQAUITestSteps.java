package com.demoqa.ui.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.demoqa.ui.pages.DemoQAUILoginPage;

public class DemoQAUITestSteps {

    private WebDriver driver;

    public DemoQAUITestSteps() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    public void runUITest() {
        driver.get("https://demoqa.com/books");

        DemoQAUILoginPage loginPage = new DemoQAUILoginPage(driver);
        loginPage.login("RafaelTestePortugal", "!Qwerty123!@#!");

        int numberOfBooksAdded = getNumberOfBooksAdded(driver);
        int numberOfBooksDisplayed = getNumberOfBooksDisplayed(driver);

        if (numberOfBooksAdded == numberOfBooksDisplayed) {
            System.out.println("Number of books added matches the number of books displayed.");
        } else {
            System.out.println("Number of books added does not match the number of books displayed.");
        }

        verifyBookDetails(driver);

        driver.quit();
    }

    private int getNumberOfBooksAdded(WebDriver driver) {
        // Replace this with your logic to retrieve the number of books added to the user
        return 0;
    }

    private int getNumberOfBooksDisplayed(WebDriver driver) {
        WebElement booksContainer = driver.findElement(By.className("books-wrapper"));
        return booksContainer.findElements(By.className("rt-tr-group")).size();
    }

    private void verifyBookDetails(WebDriver driver) {
        WebElement booksContainer = driver.findElement(By.className("books-wrapper"));
        java.util.List<WebElement> bookRows = booksContainer.findElements(By.className("rt-tr-group"));

        for (WebElement bookRow : bookRows) {
            WebElement titleElement = bookRow.findElement(By.className("rt-td")).findElement(By.tagName("a"));
            String title = titleElement.getText();

            WebElement authorElement = bookRow.findElements(By.className("rt-td")).get(1);
            String author = authorElement.getText();

            WebElement publisherElement = bookRow.findElements(By.className("rt-td")).get(2);
            String publisher = publisherElement.getText();

            WebElement pagesElement = bookRow.findElements(By.className("rt-td")).get(3);
            String pages = pagesElement.getText();

            System.out.println("Title: " + title);
            System.out.println("Author: " + author);
            System.out.println("Publisher: " + publisher);
            System.out.println("Number of Pages: " + pages);
        }
    }
}
