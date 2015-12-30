import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.PricesPage;


public class FourthTabTest {


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
        pricesPage.getTabs().get(3).click();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        driver.close();
        driver.quit();
    }

    /***
     * Проверка работоспособности элементов четвертой вкладки.
     * @throws InterruptedException
     */


    @Test
    public void fouthTab_test() throws InterruptedException {

        Assert.assertTrue("There is no input",
                pricesPage.fourthTab.getInputs().get(1).isDisplayed());
        pricesPage.fourthTab.getInputs().get(1).sendKeys("15");

        Assert.assertTrue("There is no button",
                pricesPage.fourthTab.getAddToCartButtons().get(1).isDisplayed());

        pricesPage.fourthTab.getAddToCartButtons().get(1).click();

        Assert.assertTrue("There is no button",
                pricesPage.sidePanel.getButtonGoToPayment().isDisplayed());

        pricesPage.sidePanel.getButtonsRemoveFromCart().get(0).click();
    }
}
