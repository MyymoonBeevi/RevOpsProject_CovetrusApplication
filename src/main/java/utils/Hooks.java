package utils;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class Hooks extends Driver {
    @BeforeSuite
    public void setUp(){
        driver = this.setDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://engage-dev.setvi.com/");
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void close(){
//    driver.quit();
    }
}
