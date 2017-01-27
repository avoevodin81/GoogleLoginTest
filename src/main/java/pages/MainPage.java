package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends Page {
    private static final String PAGE_URL = "https://www.google.com.ua/";
    private String pageTitle;
    private By signIn;

    public MainPage() {
        super(PAGE_URL);
    }

    @Override
    protected void parsePage() {
        signIn = By.id("gb_70");
    }

    @Override
    protected void initPage() {
        pageTitle = "Google";
    }

    public static void openMainPage() {
        final MainPage mainPage = new MainPage();
        // waiting while the main page is not loaded, and if isn't loaded, try to load it more
        mainPage.getWait().until(new com.google.common.base.Predicate<WebDriver>() {
            public boolean apply(WebDriver webDriver) {
                mainPage.getDriver().get(mainPage.getCurrentPage());
                return mainPage.getDriver().getTitle().equals(mainPage.pageTitle);
            }
        });
    }

    public static void clickSignInButton() {
        MainPage mainPage = new MainPage();
        // wait while the "Sign in" button isn't shown
        mainPage.getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(mainPage.signIn));
        mainPage.getDriver().findElement(mainPage.signIn).click();
    }
}
