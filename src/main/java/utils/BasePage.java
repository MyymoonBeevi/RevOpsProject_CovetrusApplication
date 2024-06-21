package utils;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static utils.ExtentReport.getTest;

public class BasePage extends Driver {
    protected static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private String fileName;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }


public long takeSnap(){
    long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
    try {

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dynamicFileName = "./reports/images/report_" + System.currentTimeMillis() + ".jpg";
        FileUtils.copyFile(screenshot, new File(dynamicFileName));
    } catch (WebDriverException e) {
        System.out.println("The browser has been closed.");
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return number;
}

    public void click(By locator) {
        try {
            driver.findElement(locator).click();
            getTest().log(LogStatus.PASS,"");
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : FAIL");
        }
    }
    public void click(By locator,String name,int waitTime) {
        try {
            waitTillElementEnabled(locator,waitTime);
            driver.findElement(locator).click();
            getTest().log(LogStatus.PASS,name +" is clicked");
        } catch (WebDriverException e) {
            getTest().log(LogStatus.FAIL,name +" is not clicked "+e);
            System.out.println("WebDriverException : FAIL");
        }
    }

    public void actionClick(By locator) {
        try {
            Actions action = new Actions(driver);
             WebElement act = driver.findElement(locator);
            action.click(act).build().perform();
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : FAIL");
        }
    }
    public void enterText(By locator, String text) {
        try {

            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
            getTest().log(LogStatus.PASS," is entered succesfully");
        } catch (WebDriverException e) {
            getTest().log(LogStatus.FAIL," is not entered "+ e);
            System.out.println("WebDriverException : FAIL");
        }
    }
    public void enterText(By locator, String text,String name,int waitTime) {
        try {
            waitTillElementEnabled(locator,waitTime);
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
            getTest().log(LogStatus.PASS,name +" is entered succesfully");
        } catch (WebDriverException e) {
            getTest().log(LogStatus.FAIL,name +" is not entered "+ e);
            System.out.println("WebDriverException : FAIL");
        }
    }
    public void setWait(WebDriverWait wait,By locator) {
        this.wait = wait;
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitForElementToPresentInDOM(By locator, int timeInMillis) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeInMillis));
            wait.until(refreshed(presenceOfElementLocated(locator)));
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : FAIL");
        }
    }
    public void waitTillElementEnabled(By locator, int timeInMillis){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeInMillis));
            wait.until(elementToBeClickable(locator));
        } catch (Throwable e) {
            //Assert.fail("Element not visible "+e);
        }
    }
    public void isVisible(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            element.isDisplayed();
        } catch (WebDriverException e) {
            System.out.println("Value Not Visible ");
        }
    }
    public void navigateBackWindow(){
        try {
            driver.navigate().back();
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : FAIL");
        }
    }
    public void refreshPage(){
        try{
            driver.navigate().refresh();
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : FAIL");
        }
    }

    public String getCurrentURL(){
        try{
            return driver.getCurrentUrl();
        }catch (Throwable e){
            Assert.fail("not able to get Current URL : \n"+e);
            return null;
        }
    }
    public void scrollDown() throws InterruptedException, AWTException
    {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
    }

    public void switchToWindow(int index) {

        Set<String> allWindowHandles = driver.getWindowHandles();
        List<String> allHandles = new ArrayList<>(allWindowHandles);
        driver.switchTo().window(allHandles.get(index));
    }

    public String getAttribute(By by, String attribute){
        try {
            return driver.findElement(by).getAttribute(attribute);
        } catch (Throwable e) {
            Assert.fail("Timeout Error " + e);
            return "";
        }
    }
    public String getTextValue(By locator){
        String text = "";
        try {
            WebElement element = driver.findElement(locator);
            text = element.getText();

        } catch (WebDriverException e) {
            System.out.println("WebDriverException : FAIL");
        }
        return text;
    }

    public String getTitle(By locator){
        String text = "";
        try {
            text =  driver.getTitle();

        } catch (WebDriverException e) {
            System.out.println("WebDriverException : FAIL");
        }
        return text;
    }

    public String getAlertText() {

        String text = "";
        Alert alert = driver.switchTo().alert();
        text = alert.getText();
        return text;
    }

    public void acceptAlert() {
        // Switch to the alert
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : FAIL");
        }
    }

    public void dismissAlert() {
        String text = "";
        try {
            Alert alert = driver.switchTo().alert();
            text = alert.getText();
            alert.dismiss();

        } catch (WebDriverException e) {
            System.out.println("WebDriverException : FAIL");

        }
    }
    public void selectDropDownUsingText(WebElement ele, String value) {
        try {
            new Select(ele).selectByVisibleText(value);

        } catch (WebDriverException e) {

        }

    }

    public void selectDropDownUsingIndex(WebElement ele, int index) {
        try {
            new Select(ele).selectByIndex(index);
            System.out.println("The dropdown is selected with index - PASS");
        } catch (WebDriverException e) {

        }

    }
    public void scrollToElement(By locator){
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
//            js.executeScript("arguments[0].scrollIntoView({behavior: \"smooth\", block: \"center\", inline: \"nearest\"});",driver.findElement(locator));
        } catch (Throwable e) {
            Assert.fail("Can't scroll element " + e);
        }
    }

    public void closeCurrentBrowser() {
        driver.close();
    }

    public void closeAllBrowsers() {
        driver.quit();
    }
}
