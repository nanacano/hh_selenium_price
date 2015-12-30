package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class PricesPage {

    WebDriver driver;

    public FirstTab firstTab = new FirstTab();
    public SecondTab secondTab = new SecondTab();
    public ThirdTab thirdTab = new ThirdTab();
    public FourthTab fourthTab = new FourthTab();

    public FifthTab fifthTab = new FifthTab();
    public SidePanel sidePanel = new SidePanel();

    public class FirstTab{
        By buttonsAddToCart = By.cssSelector(".price-spoffers button.bloko-button[data-qa=\"cart-item__button-add\"]");

        By disabledButton_locator = By.cssSelector("button[disabled=\"\"]");

        public By getDisabledButton_locator() {
            return disabledButton_locator;
        }

        public  List<WebElement> getButtonsAddToCart() {
            return driver.findElements(buttonsAddToCart);
        }
    }

    public class SecondTab{
        By radioButtonsInAccessCVBase = By.cssSelector("span.bloko-radio__text");

        By costOfService = By.cssSelector("span.HH-Price-ResumeAccess-Cost");

        public WebElement getCostOfService() {
            return driver.findElement(costOfService);
        }

        By addToCartButton = By.cssSelector(".price-resume-access button.bloko-button.bloko-button_secondary");

        public WebElement getAddToCartButton() {
            return driver.findElement(addToCartButton);
        }

        public boolean isRadioButtonChecked(int index){
            String str = driver.findElements(By.cssSelector("input.bloko-radio__input")).get(index).getAttribute("checked");
            if (str == null){
                return false;
            }else{
                return true;
            }
        }

        public List<WebElement> getRadioButtonsInAccessCVBase() {
            return driver.findElements(radioButtonsInAccessCVBase);
        }
    }

    public class ThirdTab{

        By inputs = By.cssSelector("div.price-countable-services input.HH-Price-CountableService-AmountInput");

        By addToCartButtons = By.cssSelector("button.bloko-button.bloko-button_small.HH-Price-CountableService-AddToCartButton");

        public List<WebElement> getAddToCartButtons() {
            return driver.findElements(addToCartButtons);
        }

        public List<WebElement> getInputs() {
            return driver.findElements(inputs);
        }
    }

    public class FourthTab{

        By inputs = By.cssSelector("input[data-code^=\"TEST_SHL_\"]");

        By addToCartButtons = By.cssSelector("[class$=\"price-countable-services_3-columns\"] button.bloko-button");

        public List<WebElement> getAddToCartButtons() {
            return driver.findElements(addToCartButtons);
        }

        public List<WebElement> getInputs() {
            return driver.findElements(inputs);
        }
    }

    public class FifthTab{

        By tabs = By.cssSelector("span.g-switchrow__switch.HH-Tabs-Item");

        By VSIMinput = By.cssSelector("input[data-code=\"VSIM\"]");

        By addToCartButtons = By.cssSelector("[class$=\"price-countable-services_2-columns\"] button.bloko-button");

        public boolean isTabActive(int index){
            return driver.findElements(tabs).get(index).getAttribute("class").contains("m-switchrow__switch_selected");
        }

        public List<WebElement> getTabs() {
            return driver.findElements(tabs);
        }

        public WebElement getVSIMinput() {
            return driver.findElement(VSIMinput);
        }

        public List<WebElement> getAddToCartButtons() {
            return driver.findElements(addToCartButtons);
        }

    }

    public class SidePanel{

        By buttonGoToPayment = By.cssSelector("span.price-cart__button");

        By buttonGoToPaymentWhenLogin = By.cssSelector("input.bloko-button[type=\"submit\"]");

        By totalCost = By.cssSelector("span.HH-PriceCart-TotalCost-Actual");

        public WebElement getTotalCost() {
            return driver.findElement(totalCost);
        }

        public WebElement getButtonGoToPaymentWhenLogin() {
            return driver.findElement(buttonGoToPaymentWhenLogin);
        }

        public By getButtonGoToPaymentWhenLogin_locator() {
            return buttonGoToPaymentWhenLogin;
        }

        public WebElement getButtonGoToPayment() {
            return driver.findElement(buttonGoToPayment);
        }

        public By getButtonGoToPayment_locator() {
            return buttonGoToPayment;
        }

        By buttonsRemoveFromCart = By.cssSelector("small.HH-PriceCart-ItemRemover");

        public List<WebElement> getButtonsRemoveFromCart() {
            return driver.findElements(buttonsRemoveFromCart);
        }
    }

    By title = By.cssSelector("div>h1.title");
    By tabs = By.cssSelector("a.flat-tabs__switcher");




    public WebDriverWait logInAsCompany(){
        String login = "nanacano@rambler.ru";
        String pass = "14X1YA";

        driver.findElement(By.cssSelector("div.navi-item__switcher[data-qa=\"mainmenu_loginForm\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type=\"email\"]")));
        driver.findElement(By.cssSelector("input[type=\"email\"]")).sendKeys(login);
        driver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys(pass);
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

//        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(title));
        return wait;
    }


    public boolean isTabActive(int index){
        return getTabs().get(index).getAttribute("class").contains("flat-tabs__switcher_active");
    }

    public List<WebElement> getTabs() {
        return driver.findElements(tabs);
    }

    public PricesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPricesPage(){
        driver.navigate().to("http://hh.ru/price");
    }

    public WebElement getTitle() {
        return driver.findElement(title);
    }

    By selectorOfPaymentPage = By.cssSelector("button.bloko-button.bloko-button_secondary.HH-Invoice-Submit");
    By selectorOfRegistrationPage = By.cssSelector("button.HH-Form-SubmitButton[data-qa=\"employer-registartion-submit\"]");

    public By getSelectorOfPaymentPage() {
        return selectorOfPaymentPage;
    }

    public By getSelectorOfRegistrationPage() {
        return selectorOfRegistrationPage;
    }

    public void waitUntilPaymentPageIsOpen(WebDriverWait wait){
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectorOfPaymentPage));
    }

    public void waitUntilRegistrationPageIsOpen(){
        waitUntilPageIsOpen(selectorOfRegistrationPage);
    }

    public void waitUntilPageIsOpen(By by){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

}
