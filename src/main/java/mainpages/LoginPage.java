package mainpages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BasePage;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage {

    // Locators
    private By email = By.name("email");
    private By password = By.name("password");

    private By SignIn = By.xpath("//span[text()='Sign In']");
    private By LogIn = By.xpath("//span[text() = 'Log In']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void SignedIn(){

        click(SignIn);
    }
    public void LoggedIn(){
        click(LogIn);
    }
    // Page specific methods
    public void emailId(String name) {
        enterText(email, name);
    }
    public void password(String pass) {

        enterText(password, pass);
    }
    public void window(){
        switchToWindow(1);
    }
    public void windowold(){
        switchToWindow(0);
    }
    public void waiting(){
        waitTillElementEnabled(SignIn,30);
    }








}