package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class BasePage extends Driver {
    protected static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private String fileName;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    public void screenShots(){
        try {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

//            FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));
            System.out.println("Screenshot taken");
        } catch (Exception e) {

            System.out.println("Exception while taking screenshot " + e.getMessage());
        }
    }

    public void click(By locator) {
        try {
            driver.findElement(locator).click();
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : FAIL");
        }
    }
    public void actionClick(By locator) {
        try {
            Actions action = new Actions(driver);
             WebElement act = driver.findElement(locator);
            action.click().build().perform();;
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : FAIL");
        }
    }

    public void setWait(WebDriverWait wait,By locator) {
        this.wait = wait;
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitTillElementEnabled(By locator, int timeInMillis){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeInMillis));
            wait.until(elementToBeClickable(locator));
        } catch (Throwable e) {
            //Assert.fail("Element not visible "+e);
        }
    }
    public void enterText(By locator, String text) {
        try {
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : FAIL");
        }
    }

    public void waitForElementToPresentInDOM(By locator, int timeInMillis) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeInMillis));
            wait.until(refreshed(presenceOfElementLocated(locator)));
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : FAIL");
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
    public String getTextFromElement(By by){
        try {
            return driver.findElement(by).getText();
        } catch (Throwable e) {
            System.out.println("WebDriverException : FAIL");
            Assert.fail("Timeout Error " + e);
            return "";
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
    public void closeCurrentWindow(){
        driver.close();
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

    public String getAlertText() {

        String text = "";
        Alert alert = driver.switchTo().alert();
        text = alert.getText();
        return text;
    }

    public void acceptAlert() {
        // Switch to the alert
        String text = "";
        try {
            Alert alert = driver.switchTo().alert();
            text = alert.getText();
            // Accept the alert (equivalent to clicking "OK" or "Yes")
            alert.accept();
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : FAIL");
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
}
