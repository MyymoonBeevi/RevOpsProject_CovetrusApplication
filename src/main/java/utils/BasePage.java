package utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class BasePage extends Driver {
    protected static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private String fileName;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    public void click(By locator) {
        try {
            driver.findElement(locator).click();
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
        WebElement element = driver.findElement(locator);
        element.isDisplayed();
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



//    public long takeSnap(){
//        TakesScreenshot screenShot =((TakesScreenshot)driver);
//        long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
//        try {
//            FileUtils.copyFile(screenShot.getScreenshotAs(OutputType.FILE) , new File("./reports/images/"+number+".jpg"));
//        } catch (WebDriverException e) {
//            System.out.println("The browser has been closed.");
//        } catch (IOException e) {
//            System.out.println("The snapshot could not be taken");
//        }
//        return number;
//    }


//    public static void takeSnapShot(String fileName) {
//        TakesScreenshot scrShot =((TakesScreenshot)driver);
//        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
//        File DestFile=new File(System.getProperty("user.dir")+"/screenshots/"+fileName+".png");
//        try {
//            FileUtils.copyFile(SrcFile, DestFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

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
