package mainpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BasePage;

public class Catalog extends BasePage {

    // Locators
    private By CatalogModule = By.xpath("//div[text()='Catalog']");
    private By catalogHeader = By.xpath("//p[text()='Catalog']");
    private By tableScroll = By.xpath("/html/body/div/div/main/div[2]/div/div[2]/div[1]");
    private By DoseValueFilter = By.xpath("//p[text()='Doses Value']");
    private By categoryIconButton = By.xpath("(//div[@id='root']//button)[4]");
    private By customerDropdown = By.xpath("(//div[@id='root']//button)[5]");
    private By gridListViewIcon = By.xpath("(//div[@id='root']//button)[6]");
    private By doneButton = By.xpath("(//button//span[text()='Done']");
    private By parentCategory = By.xpath("//p[contains(normalize-space(),'Boarding And Kennel')]");
    private By subCategory2 = By.xpath("//p[contains(normalize-space(),'Muzzles')]");
    private By subCategory1 = By.xpath("//p[contains(normalize-space(),'Animal Handling & Restraints')]");
    private By breadCrumb = By.xpath("//nav[@aria-label='breadcrumb']");


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
        click(categoryIconButton);
    }
    public void ParentCategory(){
        click(parentCategory);
    }
    public void SubCategory1(){
        click(subCategory1);
    }
    public void SubCategory2(){
        click(subCategory2);
    }
    public void DoneButton(){
        click(doneButton);
    }
    public  void breadCrumb(){
        getTextValue(breadCrumb);
    }



    public void ClickCatalogModule(){

        click(CatalogModule);
    }

    public  void  CatalogHeader(){
        String Header = getTextValue(catalogHeader);
        System.out.println(Header);
    }

    public void ScrollToElement() {

        scrollToElement(tableScroll);
    }
}