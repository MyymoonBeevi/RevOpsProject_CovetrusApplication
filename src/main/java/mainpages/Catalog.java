package mainpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BasePage;

public class Catalog extends BasePage {

    // Locators
    private final By CatalogModule = By.xpath("//div[text()='Catalog']");
    private final By catalogHeader = By.xpath("//p[text()='Catalog']");
    private final By tableScroll = By.xpath("/html/body/div/div/main/div[2]/div/div[2]/div[1]");
    private final By DoseValueFilter = By.xpath("//p[text()='Doses Value']");
    private final By categoryIconButton = By.xpath("(//div[@id='root']//button)[4]");
    private final By customerDropdown = By.xpath("(//div[@id='root']//button)[5]");
    private final By gridListViewIcon = By.xpath("(//div[@id='root']//button)[6]");
    private final By doneButton = By.xpath("(//button//span[text()='Done'])");
    private final By parentCategory = By.xpath("//p[contains(normalize-space(),'Boarding And Kennel')]");

    private final By clickProductSku = By.xpath("//p[contains(normalize-space(),'SKU: 025097')]");

    private final By subCategory2 = By.xpath("//p[contains(normalize-space(),'Muzzles')]");
    private final By subCategory1 = By.xpath("//p[contains(normalize-space(),'Animal Handling & Restraints')]");
    private final By breadCrumb = By.xpath("//nav[@aria-label='breadcrumb']");
    private final By cusomerId = By.xpath("//p[text()='748700-001']");
    private final By getPrice = By.xpath("//*[@role='dialog']//span[text()='Get Price']");
    private final By getPriceValue = By.xpath("(//div[@role='dialog']//p)[7]");


    public Catalog(WebDriver driver) {
        super(driver);
    }

    public void waitUntilCatalogHeaderDisplay(){
        waitForElementToPresentInDOM(catalogHeader,50);
    }

    public void waitUntilDoseValueFilterDisplay(){
        isVisible(DoseValueFilter);
    }
    public void CategoryIconButton(){
        click(categoryIconButton,"Click Category Icon Button to Open Category Pop up",1000);
    }
    public void ParentCategory(){
        click(parentCategory,"Select Parent Category",1000);
    }
    public void SubCategory1(){
        click(subCategory1,"Select First Sub Category",1000);
    }
    public void SubCategory2(){
        click(subCategory2,"Select Second SubCategory",1000);
    }
    public void DoneButton(){
        actionClick(doneButton,"Done Button in Category pop up ",1000);
    }
    public  void breadCrumb(){
        getTextValue(breadCrumb,"Category Breadcrumbs is shown",1000);
    }
    public void ClickCatalogModule(){ actionClick(CatalogModule,"Catalog Module ",1000);}

    public  void  CatalogHeader(){
        String Header = getTextValue(catalogHeader,"Catalog Header ",1000);
        System.out.println(Header);
    }
    public void ClickCustomerDropdown(){
        actionClick(customerDropdown,"Customer Dropdown",1000);
    }
    public void ClickCustomerIdInDropdown(){
        actionClick(cusomerId,"Customer Id",1000);
    }
    public void ClickProductSku(){
        actionClick(clickProductSku,"Product with SKU: 025097",1000);
    }
    public void GetPrice(){
        actionClick(getPrice,"Get the Price of the Product with SKU: 025097",1000);
    }
    public void PressEscKey(){
        PressEscapeKey();
    }

    public void GetPriceValue(){
        isVisible(getPrice);
        getTextValue(getPriceValue,"Get the Price of the Product with SKU: 025097",1000);
    }

}