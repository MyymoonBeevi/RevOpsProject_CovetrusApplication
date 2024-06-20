package mainpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BasePage;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By email = By.name("email");
    private By password = By.name("password");
    private By SignIn = By.xpath("//span[text()='Sign In']");
    private By logIn = By.xpath("//span[text() = 'Log In']");
    private  By logoutButton = By.xpath("(//div[@id='root']//button)[3]");
    private  By logout = By .xpath("//p[text()='Logout']");



    public void SignedIn(){

        click(SignIn);
    }
    public void LogoutButton(){
        click(logoutButton);
    }
    public void LoggedIn(){
        click(logIn);
    }
    public void LoggedOut(){
        click(logout);
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
    public void homeWindow(){
        switchToWindow(0);
    }
    public void waiting(){
        waitTillElementEnabled(SignIn,30);
    }








}