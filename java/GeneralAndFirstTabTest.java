import org.junit.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PricesPage;

import java.util.concurrent.TimeUnit;


public class GeneralAndFirstTabTest {

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

    //*
    @Test
    public void findTitle_test() {
        Assert.assertTrue("Page doesn't open",
                pricesPage.getTitle().isDisplayed());
    }

    /***
     * Проверка табов.
     */

    @Test
    public void allTabs_test() {
        Assert.assertTrue("There is no tabs",
                pricesPage.getTabs().get(0).isDisplayed());

        Assert.assertTrue("There is no tabs",
                pricesPage.getTabs().get(1).isDisplayed());

        pricesPage.getTabs().get(1).click();
        Assert.assertTrue("Second tab isn't active",
                pricesPage.isTabActive(1));

        pricesPage.getTabs().get(2).click();
        Assert.assertTrue("Third tab isn't active",
                pricesPage.isTabActive(2));

        pricesPage.getTabs().get(3).click();
        Assert.assertTrue("Fourth tab isn't active",
                pricesPage.isTabActive(3));

        pricesPage.getTabs().get(0).click();
        Assert.assertTrue("First tab isn't active",
                pricesPage.isTabActive(0));
    }
    //*/

    /***
     * Добавление в корзину и удаление из неё.
     * @throws InterruptedException
     */

    @Test
    public void putServiceToBasketAndRemoveIt_test() throws InterruptedException {
        Assert.assertTrue("There is no buttons",
                pricesPage.firstTab.getButtonsAddToCart().get(0).isDisplayed());
        pricesPage.firstTab.getButtonsAddToCart().get(0).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                pricesPage.sidePanel.getButtonGoToPaymentWhenLogin_locator()));

        Assert.assertTrue("There is no button go to payment",
                pricesPage.sidePanel.getButtonGoToPayment().isDisplayed());

        Assert.assertTrue("There is no button remove from cart",
                pricesPage.sidePanel.getButtonsRemoveFromCart().get(0).isDisplayed());

        pricesPage.sidePanel.getButtonsRemoveFromCart().get(0).click();

        Assert.assertTrue("There is a button go to payment",
                !pricesPage.sidePanel.getButtonGoToPayment().isDisplayed());

    }

    /**
     * Проверка редиректа без регистрации.
     * @throws InterruptedException
     */

    //*
    @Test
    public void putServiceToBasketAndContinue_test() throws InterruptedException{

        pricesPage.firstTab.getButtonsAddToCart().get(0).click();

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                pricesPage.sidePanel.getButtonGoToPaymentWhenLogin_locator()));

        Assert.assertTrue("There is no button go to payment",
                pricesPage.sidePanel.getButtonGoToPayment().isDisplayed());

        pricesPage.sidePanel.getButtonGoToPayment().click();

        pricesPage.waitUntilRegistrationPageIsOpen();

        Assert.assertTrue("Registration page wasn't opened",
                driver.findElement(pricesPage.getSelectorOfRegistrationPage()).isDisplayed());
    }


    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        driver.close();
        driver.quit();
    }
}
