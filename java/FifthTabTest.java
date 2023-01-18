import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.PricesPage;


public class FifthTabTest {



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
        pricesPage.getTabs().get(4).click();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        driver.close();
        driver.quit();
    }

    /***
     * Проверка работоспособности элементов пятой вкладки.
     * @throws InterruptedException
     */
	 // new 
    @Test
    public void fifthTab_test() throws InterruptedException {

        Assert.assertTrue("There is no input",
                pricesPage.fifthTab.getVSIMinput().isDisplayed());
        pricesPage.fifthTab.getVSIMinput().sendKeys("15");

        Assert.assertTrue("There is no button",
                pricesPage.fifthTab.getAddToCartButtons().get(0).isDisplayed());

        pricesPage.fifthTab.getAddToCartButtons().get(0).click();

        Assert.assertTrue("There is no button",
                pricesPage.sidePanel.getButtonGoToPayment().isDisplayed());

        pricesPage.sidePanel.getButtonsRemoveFromCart().get(0).click();
    }

    @Test
    public void fifthTabSubTabs_test() throws InterruptedException {

        Assert.assertTrue("There is no tabs",
                pricesPage.fifthTab.getTabs().get(0).isDisplayed());

        Assert.assertTrue("There is no tabs",
                pricesPage.fifthTab.getTabs().get(1).isDisplayed());

        pricesPage.fifthTab.getTabs().get(1).click();
        Assert.assertTrue("Second tab isn't active",
                pricesPage.fifthTab.isTabActive(1));

        pricesPage.fifthTab.getTabs().get(2).click();
        Assert.assertTrue("Third tab isn't active",
                pricesPage.fifthTab.isTabActive(2));
    }
}
