import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PricesPage;


public class SecondTabTest {

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
        pricesPage.getTabs().get(1).click();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        driver.close();
        driver.quit();
    }

    /**
     * ѕроверка цены услуги во вкладке и в боковой панели.
     */

    @Test
    public void secondTab_test() throws InterruptedException {

        pricesPage.secondTab.getRadioButtonsInAccessCVBase().get(2).click();
        Assert.assertTrue("There is no button go to payment",
                pricesPage.secondTab.isRadioButtonChecked(2));

        pricesPage.secondTab.getAddToCartButton().click();
        Assert.assertEquals(pricesPage.sidePanel.getButtonsRemoveFromCart().size(), 1);
        Assert.assertEquals(pricesPage.sidePanel.getTotalCost().getText(),
                pricesPage.secondTab.getCostOfService().getText());
    }

    /**
     * ѕроверка добавлении в корзину двух услуг из первых двух вкладок.
     * @throws InterruptedException
     */

    @Test
    public void secondTabAndFirst_test() throws InterruptedException {
        pricesPage.secondTab.getRadioButtonsInAccessCVBase().get(2).click();
        pricesPage.secondTab.getAddToCartButton().click();
        pricesPage.getTabs().get(0).click();
        pricesPage.firstTab.getButtonsAddToCart().get(0).click();

        Assert.assertEquals(2, pricesPage.sidePanel.getButtonsRemoveFromCart().size());
        pricesPage.sidePanel.getButtonsRemoveFromCart().get(1).click();

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(pricesPage.firstTab.getDisabledButton_locator()));
        Assert.assertEquals(1, pricesPage.sidePanel.getButtonsRemoveFromCart().size());
    }
}
