package edu.udacity.java.nano.chat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class JUnitSeleniumTodoTest {


    static WebDriver driver;

    @BeforeClass
    public static void BrowserOpen() {
        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    @Test
    public void toDoTests() throws InterruptedException {
        // TODO Auto-generated method stub

        /***
         *  Login Test
         */

        String usernameStr = "ons";
        boolean status = false;
        driver.get("http://localhost:8080/");
        WebElement username = driver.findElement(By.id("username"));

        WebElement login = driver.findElement(By.linkText("Login"));
        username.sendKeys(usernameStr);
        driver.findElement(By.linkText("Login")).click();

        String actualUrl = "http://localhost:8080/index?username=" + usernameStr;
        String expectedUrl = driver.getCurrentUrl();
        System.out.println(driver.getCurrentUrl());
        if (actualUrl.equalsIgnoreCase(expectedUrl)) {
            System.out.println("Login Test passed");
            status = true;
        } else {
            System.out.println("Login Test failed");

        }

        if (status == true) {

            /***
             * Join Test
             ***/

            WebElement joinedUser =  driver.findElement(By.id("username"));
            if(joinedUser.getText().equalsIgnoreCase(usernameStr)){
                System.out.println("User Joined : Test passed");
            }
            else{
                System.out.println("User Joined : Test failed");
            }

            /***
             *  Chat Test
             ***/

            WebElement talkbox = driver.findElement(By.id("msg"));
            talkbox.sendKeys("Hello, I m ons hello");
            WebElement sendbutton = driver.findElement(By.id("sendBtn"));
            sendbutton.click();

            /***
             *  Logout Test
             ***/

            WebElement logout = driver.findElement(By.cssSelector(".mdui-btn-icon"));
            logout.click();
            String url = "http://localhost:8080/";
            String expected_logouUrl = driver.getCurrentUrl();
            System.out.println(driver.getCurrentUrl());
            if (url.equalsIgnoreCase(expected_logouUrl)) {
                System.out.println("Logout Test passed ");

            } else {
                System.out.println("Logout Test failed");

            }

        }

    }
    @AfterClass
    public static void BrowserClose() {
        driver.quit();
    }
}