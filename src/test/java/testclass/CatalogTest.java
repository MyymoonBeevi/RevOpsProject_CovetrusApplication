package testclass;

import mainpages.Catalog;
import mainpages.LoginPage;
import org.testng.annotations.Test;
import utils.Hooks;
import java.awt.*;


public class CatalogTest extends Hooks {
    @Test
    public void successfulLoginTest() throws InterruptedException, AWTException {
        LoginPage loginPage = new LoginPage(driver);
        Catalog catalog = new Catalog(driver);

        loginPage.waiting();
        loginPage.SignedIn();
        loginPage.window();
        loginPage.emailId("lavanya.velu@setvi.com");
        loginPage.password("Lavanya123#");
        loginPage.LoggedIn();
        loginPage.windowold();
     //Catalog Module
        catalog.ClickCatalogModule();
        catalog.waitUntilCatalogHeaderDisplay();
     // HeaderCheck
        catalog.CatalogHeader();
    }}