package utils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class Hooks extends Driver {
    @BeforeClass
    public void setUp(){
        driver = this.setDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://engage-dev.setvi.com/");
        driver.manage().window().maximize();

    }

    @AfterClass
    public void close(){
//    driver.quit();
    }
}
