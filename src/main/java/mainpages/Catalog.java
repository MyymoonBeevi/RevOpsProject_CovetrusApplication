package mainpages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BasePage;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Catalog extends BasePage {

    // Locators
    private By CatalogModule = By.xpath("//div[text()='Catalog']");
    private By catalogHeader = By.xpath("//p[text()='Catalog']");

//    private By tablerow = By.xpath("//tr[@class='MuiTableRow-root MuiTableRow-head']//th");


    public Catalog(WebDriver driver) {

        super(driver);
    }
    public void waitUntilCatalogHeaderDisplay(){
        waitForElementToPresentInDOM(catalogHeader,50);
    }
    public void ClickCatalogModule(){
        click(CatalogModule);
    }
    public  void  CatalogHeader(){

        String Header = getTextValue(catalogHeader);
        System.out.println(Header);

    }








}