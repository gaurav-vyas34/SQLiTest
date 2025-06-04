package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SQLiTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");


        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://juice-shop.herokuapp.com/#/login");
            Thread.sleep(3000);

            WebElement email = driver.findElement(By.id("email"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("loginButton"));

            email.sendKeys("' OR 1=1 --");
            password.sendKeys("anything");
            loginButton.click();

            Thread.sleep(3000);

            if (driver.getPageSource().contains("Invalid email or password")) {
                System.out.println("SQL Injection blocked.");
            } else {
                System.out.println("SQL Injection might have succeeded!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
