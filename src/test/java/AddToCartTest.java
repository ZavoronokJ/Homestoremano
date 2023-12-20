import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import static org.assertj.core.api.Assertions.assertThat;

public class AddToCartTest {
    WebDriver driver;

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("https://themes.woocommerce.com/homestore/shop/");
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void AddToCart () {
//        driver.findElement().click();


    }


}