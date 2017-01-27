import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.LoginPage;
import pages.MainPage;
import webdriver.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

@RunWith(Parameterized.class)
public class GoogleLoginTest {
    String email;

    public GoogleLoginTest(String email) {
        this.email = email;
    }

    @BeforeClass
    public static void setUp() {
        Context.initInstance(Context.BROWSER_CHROME, "https://www.google.com.ua/");
    }

    // for opening main page before each test
    @Before
    public void openMainPage() {
        MainPage.openMainPage();
        MainPage.clickSignInButton();
    }

    @Test
    public void TestEmails() {
        LoginPage.fillEmailField(email);
        LoginPage.clickNextButton();
        assertTrue(email, LoginPage.isEmailValid());
    }

    @AfterClass
    public static void tearDown() {
        Context.browserClose();
    }

    public static void assertTrue(String email, boolean isEmailValid) {
        Assert.assertTrue(String.format("The \"%s\" email is invalid!", email), isEmailValid);
    }

    @Parameterized.Parameters
    public static Object[] data() {
        //read the file
        ArrayList<String> words = new ArrayList<String>();
        Scanner scn = null;
        try {
            scn = new Scanner(new File("src/main/resources/emails.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scn.hasNext()) {
            words.add(scn.nextLine());
        }
        scn.close();
        return words.toArray();
    }
}
