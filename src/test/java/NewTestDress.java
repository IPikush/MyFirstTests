import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NewTestDress {
    WebDriver driver;
    private final String baseUrl = "http://automationpractice.com/index.php";

    @BeforeTest
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //implicitwait
        //driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    @AfterTest
    public void cleanDriver() {
        driver.quit();
    }
    @Test
    public void myUiTest() throws InterruptedException {

        driver.get(baseUrl);
        goToDresses();
        goToEveningDresses();
        printedDressAddToCont();
        continueShopping();
        openCart();
        testAddProduct();


    }
    public void goToDresses(){
        WebElement dresses= driver.findElement(By.xpath("//*[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[2]/a[@title='Dresses']"));
        dresses.click();
    }
    public void goToEveningDresses(){
        WebElement eveningDresses=driver.findElement(By.xpath("//*[@class='subcategory-image']/a[@title='Evening Dresses']"));
        eveningDresses.click();
    }
    public void printedDressAddToCont(){
        WebDriverWait waiting = new WebDriverWait(driver, 6);
        WebElement buttonAdd= driver.findElement(By.xpath("//a[@class='button ajax_add_to_cart_button btn btn-default']"));
        WebElement buttonAddClickable = waiting.until(ExpectedConditions.elementToBeClickable(buttonAdd));
        buttonAddClickable.click();
    }
    public  void continueShopping(){
        WebDriverWait waiting = new WebDriverWait(driver, 10);
        WebElement continShop= driver.findElement(By.xpath("//i[@class='icon-chevron-left left']"));
        WebElement continShopClickable=waiting.until(ExpectedConditions.elementToBeClickable(continShop));
        continShopClickable.click();
    }
    public void openCart(){
        WebDriverWait waiting = new WebDriverWait(driver, 6);
        WebElement openCart= driver.findElement(By.xpath("//a[@title='View my shopping cart']"));
        WebElement openCartClickable = waiting.until(ExpectedConditions.elementToBeClickable(openCart));
        openCartClickable.click();

    }
    public void testAddProduct(){
        WebDriverWait waiting = new WebDriverWait(driver, 6);
        //WebElement printedDressInCart = driver.findElement(By.xpath("//*[@class='cart_description']//a[contains(text(), 'Printed Dress')]"));
        WebElement printedDressInCartPresence = waiting.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//*[@class='cart_description']//a[contains(text(), 'Printed Dress')]")));
        Assert.assertTrue(printedDressInCartPresence.isDisplayed(),"Сталася помилка");

    }
}
