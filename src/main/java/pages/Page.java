package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.Context;

public abstract class Page {
    private Context context;
    private String currentPage;

    public Page(String pageUrl) {
        this.currentPage = pageUrl;
        setContext(Context.getInstance());
        initPage();
        parsePage();
    }

    private void setContext(Context instance) {
        this.context = instance;
    }

    protected abstract void parsePage();

    protected abstract void initPage();

    public String getCurrentPage() {
        return currentPage;
    }

    protected WebDriver getDriver() {
        return context.getDriver();
    }

    protected WebDriverWait getWait() {
        return context.getWait();
    }
}
