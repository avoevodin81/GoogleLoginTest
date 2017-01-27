package pages;

import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends Page {
    private static final String PAGE_URL = "https://accounts.google.com/ServiceLogin#identifier";
    private String mainPageTitle;
    private By email;
    private By nextButton;
    private By password;
    private By signInButton;
    private By invalidEmailMessage;
    private String errorMessage;

    public LoginPage() {
        super(PAGE_URL);
    }

    @Override
    protected void parsePage() {
        email = By.id("Email");
        nextButton = By.id("next");
        password = By.id("Passwd");
        signInButton = By.id("signIn");
        invalidEmailMessage = By.id("errormsg_0_Email");
        errorMessage = "Не удалось распознать адрес электронной почты.";
    }

    @Override
    protected void initPage() {
        mainPageTitle = "Вход – Google Аккаунты";
        // wait for needed title of the page
        getWait().until(ExpectedConditions.titleIs(mainPageTitle));
    }

    public static void fillEmailField(String email) {
        LoginPage loginPage = new LoginPage();
        // wait the email field
        loginPage.getWait().until(ExpectedConditions.presenceOfElementLocated(loginPage.email));
        // fill the email field
        loginPage.getDriver().findElement(loginPage.email).sendKeys(email);
    }

    public static void clickNextButton() {
        final LoginPage loginPage = new LoginPage();
        // click the "Next" button
        loginPage.getDriver().findElement(loginPage.nextButton).click();
        // wait if error message is shown or password field is shown
        loginPage.getWait().until(new Predicate<WebDriver>() {
            public boolean apply(WebDriver webDriver) {
                return (loginPage.getDriver().findElement(loginPage.invalidEmailMessage).getText().equals(loginPage.errorMessage)) ||
                        (loginPage.getDriver().findElements(loginPage.password).size() > 0);
            }
        });
    }


    public static boolean isEmailValid() {
        final LoginPage loginPage = new LoginPage();
        loginPage.getWait().until(new Predicate<WebDriver>() {
            public boolean apply(WebDriver webDriver) {
                // wait if error message is shown or password field is shown
                return loginPage.getDriver().findElement(loginPage.invalidEmailMessage).getText().equals(loginPage.errorMessage) ||
                        loginPage.getDriver().findElements(loginPage.password).size() > 0;
            }
        });
        // check that error message isn't shown
        return !loginPage.getDriver().findElement(loginPage.invalidEmailMessage).getText().equals(loginPage.errorMessage);
    }

}
