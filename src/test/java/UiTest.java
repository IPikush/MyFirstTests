import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.openqa.selenium.By.*;

public class UiTest {
    WebDriver driver;
    private final String baseUrl = "https://www.ikea.com/ua/uk/";

    /*@BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();

    }*/

    @BeforeTest
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterTest
    public void cleanDriver() {
        driver.quit();
    }

    @Test
    public void myUiTest() {

        driver.get(baseUrl);
        //driver.navigate().to("https://www.ikea.com/ua/uk/");
        //driver.close();
    }

    @Test
    public void myUiTest2() {
        driver.get(baseUrl);
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
        Assert.assertEquals(currentUrl,"https://www.ikea.com/ua/uk/","Error is not as expected");
    }
    @Test
    public void myUiTest3() {
        driver.get(baseUrl);
        String currentTitle = driver.getTitle();
        System.out.println(currentTitle);
        Assert.assertEquals(currentTitle,"404 - Ой! Щось пішло не так :( - IKEA","Error is not as expected");
        Assert.assertTrue(currentTitle.contains("IKeA"));
    }
    @Test
    public void myUiTest4() {
        driver.navigate().to("https://www.ikea.com/ua/uk/");
        driver.navigate().to("https://www.ikea.com/ua/uk/temp");
        driver.navigate().back();
        driver.navigate().refresh();
        String currentTitle = driver.getTitle();
        System.out.println(currentTitle);
       // Assert.assertEquals(currentTitle,"404 - Ой! Щось пішло не так :( - IKEA","Error is not as expected");
        Assert.assertTrue(currentTitle.contains("IKEA"));
    }



        String STANDART_USER_LOGIN="standard_user";
        String PASSWORD="secret_sauce";
    String expectedResultURL="https://www.saucedemo.com/inventory.html";


    @Test
    public void standartUserLoginTest() throws InterruptedException {
        openSauce();
        login(STANDART_USER_LOGIN,PASSWORD);
        logout();



    }
    @Test
    public void incorrectUserLoginTest() throws InterruptedException {
        openSauce();
        login("test","test");
        textMessage();
        //logout();

    }
    private void textMessage(){
        WebElement errorMes = driver.findElement(xpath("//h3[text()='Epic sadface: Username and password do not match any user in this service']"));
        Assert.assertTrue(errorMes.isDisplayed(),"login is correct");
    }

    private void login(String login,String password){
        WebElement loginField= driver.findElement(id("user-name"));
        WebElement passwordField=driver.findElement(id("password"));

        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        WebElement loginButton = driver.findElement(id("login-button"));
        loginButton.click();
        String actualResult=driver.getCurrentUrl();
        String expectedResult="https://www.saucedemo.com/inventory.html";
        //Assert.assertEquals(actualResult,expectedResult,"User did not log in");

    }
    private void logout() throws InterruptedException {
        openSauce();
        login(STANDART_USER_LOGIN,PASSWORD);
        WebElement burgerMenu= driver.findElement(id("react-burger-menu-btn"));
        burgerMenu.click();
        WebElement logoutButton = driver.findElement(id("logout_sidebar_link"));
        Thread.sleep(500);
        logoutButton.click();
        Assert.assertFalse(driver.getCurrentUrl().contains(expectedResultURL));

    }
    private void openSauce(){
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().window().maximize();//fullscreen
    }

}


