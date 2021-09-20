import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.openqa.selenium.By.*;

public class UiTest {
    WebDriver driver;
    private final String baseUrl = "https://www.ikea.com/ua/uk/";

    @BeforeTest(groups={"smoke","regress"})
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @BeforeMethod(groups={"smoke","regress"})
    public  void getUrl(){
        driver.get(baseUrl);
    }

    @AfterTest(groups={"smoke","regress"})
    public void cleanDriver() {
        driver.quit();
    }

    @Test(groups={"smoke","regress"})
    public void myUiTest() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
        Assert.assertEquals(currentUrl,"https://www.ikea.com/ua/uk/","Error is not as expected");
    }
    @Test(groups={"regress"})
    public void myUiTest2() {
        String currentTitle = driver.getTitle();
        System.out.println(currentTitle);
        Assert.assertNotEquals(currentTitle,"404 - Ой! Щось пішло не так :( - IKEA","Error is not as expected");

    }
    @Test(groups={"smoke","regress"})
    public void myUITest3(){
        String currentTitle = driver.getTitle();
        System.out.println(currentTitle);
        Assert.assertTrue(currentTitle.contains("IKEA"));
    }
    @Test(groups={"regress"})
    public void myUiTest4() {
        driver.navigate().to("https://www.ikea.com/ua/uk/");
        driver.navigate().to("https://www.ikea.com/ua/uk/temp");
        //driver.navigate().back();
        //driver.navigate().refresh();
        String currentTitle = driver.getTitle();
        System.out.println(currentTitle);
        Assert.assertEquals(currentTitle,"404 - Ой! Щось пішло не так :( - IKEA","Error is not as expected");
        //Assert.assertTrue(currentTitle.contains("IKEA"));
    }



    String STANDART_USER_LOGIN="standard_user";
    String PASSWORD="secret_sauce";
    String expectedResultURL="https://www.saucedemo.com/inventory.html";


    @Test(groups={"smoke","regress"})
    public void standartUserLoginTest() throws InterruptedException {
        openSauce();
        login(STANDART_USER_LOGIN,PASSWORD);
        logout();



    }
    @Test(groups={"regress"})
    public void incorrectUserLoginTest() throws InterruptedException {
        openSauce();
        login("test","test");
        checkErrorMessage();

    }
    private void checkErrorMessage(){
        WebElement errorMes = driver.findElement(xpath("//h3[@data-test='error']"));
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
        //WebElement logoutButton = driver.findElement(id("logout_sidebar_link"));
        WebDriverWait waiting = new WebDriverWait(driver, 10);
        WebElement logoutButton = waiting.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
        logoutButton.click();
        Assert.assertFalse(driver.getCurrentUrl().contains(expectedResultURL));

    }
    private void openSauce(){
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().window().maximize();//fullscreen
    }

}


