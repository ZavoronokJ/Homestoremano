import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {
    WebDriver driver;

    @BeforeEach //reikia atskiros BaseTest klases before ir after dalykams
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("https://themes.woocommerce.com/homestore/my-account/");
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @ValueSource(strings = {"katinukai123@gmail.com"})
    @DisplayName("Not successful login with email")
    public void NotSuccessfulLoginWithEmailTest(String email) {         //pirmas -antras  testai gali apsijungti

        driver.findElement(By.id("username")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys("katinukai");

        driver.findElement(By.id("rememberme")).click();
        WebElement element = driver.findElement(By.cssSelector(".woocommerce-error"));
        driver.findElement(By.xpath("//*[@id=\"post-214\"]/div/div/form/p[3]/button")).click(); //kazkaip kitaip, paprasciau
        Assertions.assertTrue(element.getText().contains("Unknown email address. Check again or try your username."));

    }

    @ParameterizedTest
    @ValueSource(strings = {"katinukai123"})
    @DisplayName("Not successful login with username")
    public void NotSuccessfulLoginWithUsernameTest(String username) {

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys("katinukai");

        driver.findElement(By.id("rememberme")).click();

        driver.findElement(By.xpath("//*[@id=\"post-214\"]/div/div/form/p[3]/button")).click();
        Assertions.assertTrue(driver.findElement(By.cssSelector("ul[role='alert']")) //assertus  patvarkyti
                .getText().contains(" is not registered on this site. If you are unsure of your username, try your email address instead."));

    }

    @ParameterizedTest
    @CsvSource({
            "' ', katinukai, Error: Username is required.",
            "katinukai123, ' ' , Error: The password field is empty.",
            "' ' ,' ' , Error: Username is required.",
    })
    @DisplayName("Unsuccessful login with csv")
    public void NotSuccessfulLoginTest(String username, String password, String message) {

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.id("rememberme")).click();

        driver.findElement(By.xpath("//*[@id=\"post-214\"]/div/div/form/p[3]/button")).click();
        Assertions.assertTrue(driver.findElement(By.cssSelector("ul[role='alert']")).getText().contains(message));

    }

    @ParameterizedTest
    @ValueSource(strings = {"katinukai123"})
    @DisplayName("Not successful recover password with username")
    public void NotSuccessfulRecoverPasswordWithUsernameTest(String username) {

        driver.findElement(By.xpath("//a[normalize-space()='Lost your password?']")).click();

        driver.findElement(By.cssSelector("#user_login")).sendKeys(username);

        driver.findElement(By.xpath("//*[@id=\"post-214\"]/div/div/form/p[3]/button")).click();

        Assertions.assertTrue(driver.findElement(By.cssSelector("ul[role='alert']"))
                .getText().contains("Invalid username or email."));

    }


    @ParameterizedTest
    @CsvSource({"' ', Enter a username or email address.",})
    @DisplayName("Unsuccessful recover password with empty field")
    public void NotSuccessfulRecoverPasswordTest(String username, String message) { //papildyti pavadinima emptyfield

        driver.findElement(By.xpath("//a[normalize-space()='Lost your password?']")).click();

        driver.findElement(By.cssSelector("#user_login")).sendKeys(username);

        driver.findElement(By.xpath("//*[@id=\"post-214\"]/div/div/form/p[3]/button")).click();

        Assertions.assertTrue(driver.findElement(By.cssSelector("ul[role='alert']")).getText().contains(message));

    }
}