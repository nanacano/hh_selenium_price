import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PricesPage;


public class FirstTabWithLoginTest {

    static WebDriver driver;
    static PricesPage pricesPage;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        pricesPage = new PricesPage(driver);
    }

    @Before
    public void setUp() throws Exception {
        pricesPage.openPricesPage();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        driver.close();
        driver.quit();
    }

    /**
     * Проверка редиректа при логине.
     * @throws InterruptedException
     */

    @Test
    public void putServiceToBasketAndContinue2_test() throws InterruptedException{
        WebDriverWait wait = pricesPage.logInAsCompany();
        pricesPage.openPricesPage();
        pricesPage.firstTab.getButtonsAddToCart().get(0).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(pricesPage.sidePanel.getButtonGoToPaymentWhenLogin_locator()));

        pricesPage.sidePanel.getButtonGoToPaymentWhenLogin().click();

        pricesPage.waitUntilPaymentPageIsOpen(wait);

        Assert.assertTrue("Payment page wasn't opened",
                driver.findElement(pricesPage.getSelectorOfPaymentPage()).isDisplayed());
    }
}
