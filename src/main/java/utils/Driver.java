package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    protected WebDriver driver;
    public WebDriver  setDriver() {

        driver = new ChromeDriver();
        return driver;
    }


}
