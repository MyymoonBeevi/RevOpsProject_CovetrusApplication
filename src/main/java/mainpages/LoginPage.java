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

        click(SignIn,"SignIn Button",10000);
    }
    public void LogoutButton(){
        click(logoutButton);
    }
    public void LoggedIn(){
        actionClick(logIn,"LoggedIn Button",1000);
    }
    public void LoggedOut(){

        actionClick(logout,"Logged Out Button ",1000);
    }
    // Page specific methods
    public void emailId(String emailid) {
        enterText(email, emailid,"emailId",1000);
    }
    public void password(String pass) {

        enterText(password, pass , "Password",1000);
    }
    public void loginWindow(){
        switchToWindow(1,"Switch to the Login Window ",1000);
    }
    public void homeWindow(){
        switchToWindow(0,"Switch to the Home Window ",1000);
    }
    public void waiting(){
        waitTillElementEnabled(SignIn,30);
    }








}