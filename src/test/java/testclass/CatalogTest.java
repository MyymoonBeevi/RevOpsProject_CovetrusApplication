package testclass;

import mainpages.Catalog;
import mainpages.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Hooks;

import java.awt.*;

import static utils.ExtentReport.getTest;

public class CatalogTest extends Hooks {
   @BeforeClass(dependsOnMethods = {"setUp"})
   public void LoginTest()  {
       test = getTest("Login the Engage Application");
       LoginPage loginPage = new LoginPage(driver);
       loginPage.waiting();
       loginPage.SignedIn();
       loginPage.loginWindow();
       loginPage.emailId("lavanya.velu@setvi.com");
       loginPage.password("Lavanya123#");
       loginPage.LoggedIn();
       loginPage.homeWindow();
   }
    @Test(groups = {"smoke","regression"}, priority=3)
    public void SuccesfulLoginToRevOpsProject() throws InterruptedException, AWTException {
        test = getTest("Login the Application as a Manufacturer User");
        Catalog catalog = new Catalog(driver);
        catalog.ClickCatalogModule();
        catalog.waitUntilCatalogHeaderDisplay();
        catalog.CatalogHeader();
    }


    @Test(groups = {"smoke"}, priority=0)
    public void OpenCatalogModule() {
        test = getTest("Open the Catalog Module in the Engage Application");
//        LoginTest();
        Catalog catalog = new Catalog(driver);
        catalog.ClickCatalogModule();
        catalog.waitUntilCatalogHeaderDisplay();
   }
    @Test(groups = {"smoke"}, priority=1)
    public void CatalogModuleList() {
        test = getTest("Open the Product List in Catalog Module in the Engage Application");
        Catalog catalog = new Catalog(driver);
        catalog.ClickCatalogModule();
        catalog.waitUntilCatalogHeaderDisplay();
        catalog.waitUntilDoseValueFilterDisplay();

    }
    @Test(groups = {"smoke"}, priority=2)
    public void CategoryExpansion() {
        test = getTest("Select the Category in Catalog Module in the Engage Application");
        Catalog catalog = new Catalog(driver);
        catalog.ClickCatalogModule();
        catalog.CategoryIconButton();
        catalog.ParentCategory();
        catalog.SubCategory1();
        catalog.SubCategory2();
        catalog.DoneButton();
        catalog.breadCrumb();
    }
    @Test(groups = {"smoke","regression"}, priority=4)
    public void CustomerDropdownOpen()  {
        test = getTest("Click the Customer Dropdown");
        Catalog catalog = new Catalog(driver);
        catalog.ClickCatalogModule();
        catalog.ClickCustomerDropdown();
        catalog.ClickCustomerIdInDropdown();
    }
    @Test(groups = {"smoke","regression"}, priority=5)
    public void ClickProductUsingSku() throws InterruptedException {
        test = getTest("Click the Customer Dropdown");
        Catalog catalog = new Catalog(driver);
        catalog.ClickCatalogModule();
        catalog.ClickCustomerDropdown();
        catalog.ClickCustomerIdInDropdown();
        catalog.ClickProductSku();
        catalog.GetPrice();
        catalog.PressEscKey();
        catalog.ClickProductSku();
        catalog.GetPriceValue();
    }
    @Test(groups = {"smoke","regression"}, priority=6)
    public void LogoutTest()  {
        test = getTest("Logout the Application");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.LogoutButton();
        loginPage.LoggedOut();
    }

}