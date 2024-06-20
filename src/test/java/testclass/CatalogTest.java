package testclass;

import mainpages.Catalog;
import mainpages.LoginPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Hooks;
import java.awt.*;

public class CatalogTest extends Hooks {
   @BeforeTest
   public void LoginTest()  {
       LoginPage loginPage = new LoginPage(driver);

       loginPage.waiting();
       loginPage.SignedIn();
       loginPage.window();
       loginPage.emailId("lavanya.velu@setvi.com");
       loginPage.password("Lavanya123#");
       loginPage.LoggedIn();
       loginPage.homeWindow();
   }
    @Test(groups = {"smoke","regression"})
    public void SuccesfulLoginToRevOpsProject() throws InterruptedException, AWTException {
        Catalog catalog = new Catalog(driver);
        catalog.ClickCatalogModule();
        catalog.waitUntilCatalogHeaderDisplay();
        catalog.CatalogHeader();
    }
    @Test(groups = {"smoke","regression"})
    public void LogoutTest()  {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.LogoutButton();
        loginPage.LoggedOut();
    }

    @Test(groups = {"smoke"})
    public void OpenCatalogModule() {
        Catalog catalog = new Catalog(driver);
        catalog.ClickCatalogModule();
        catalog.waitUntilCatalogHeaderDisplay();
   }
    @Test(groups = {"smoke"})
    public void CatalogModuleList() {
        Catalog catalog = new Catalog(driver);
        catalog.ClickCatalogModule();
        catalog.waitUntilCatalogHeaderDisplay();
        catalog.waitUntilDoseValueFilterDisplay();
        catalog.ScrollToElement();

    }
    @Test(groups = {"smoke"})
    public void CategoryExpansion() {
        Catalog catalog = new Catalog(driver);
        catalog.ClickCatalogModule();
        catalog.CategoryIconButton();
        catalog.ParentCategory();
        catalog.SubCategory1();
        catalog.SubCategory2();
        catalog.DoneButton();
        catalog.breadCrumb();
    }

}