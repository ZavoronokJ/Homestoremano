import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShopPageTest {
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get("https://themes.woocommerce.com/homestore/shop/");
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void NavigatePageClickArrow () {
        driver.findElement(By.xpath(
                "//div[@class='col-full']//div[1]//nav[1]//ul[1]//li[3]//a[1]")).click(); //patvarkyti

        driver.findElement(By.xpath(
                "//div[@class='col-full']//div[1]//nav[1]//ul[1]//li[2]//a[1]")).click();
    }

    @Test
    void NavigatePageClickNumber () {
        WebElement element = driver.findElement(By.xpath(
                "//div[@class='col-full']//div[1]//nav[1]//ul[1]//li[2]//a[1]"));
        System.out.println("///////////////////////////////////////////////////////////");
        System.out.println(element.getText());

        driver.findElement(By.xpath(
                "//div[@class='col-full']//div[1]//nav[1]//ul[1]//li[1]//a[1]")).click();
    }

    @Test
    void PageSearchTest () {
        driver.findElement(By.cssSelector("#woocommerce-product-search-field-0"))
                .sendKeys("couch" + Keys.ENTER);
    }

    @Test
    void PageSearchEmptyFieldTest () {
        driver.findElement(By.cssSelector("#woocommerce-product-search-field-0"))
                .sendKeys(" " + Keys.ENTER);
    }

    @Test
    void IncorrectProductSearchTest () {
        driver.findElement(By.cssSelector("#woocommerce-product-search-field-0"))
                .sendKeys("dog" + Keys.ENTER);

        Assertions.assertTrue(driver.findElement(By.cssSelector(".woocommerce-info.woocommerce-no-products-found"))
                .getText().contains("No products were found matching your selection."));
    }

}