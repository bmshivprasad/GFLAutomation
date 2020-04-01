package gflwishes.PageObjects;

import gflwishes.base.EnhancedBaseClass;
import gflwishes.base.Generics;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends EnhancedBaseClass {

    WebDriver localDriver;
    Generics generics;

    public LoginPage(WebDriver baseDriver) {
        this.localDriver = baseDriver;
        PageFactory.initElements(localDriver, this);
        generics = new Generics(localDriver);
        log4j = Logger.getLogger("LoginPage");
    }

    @FindBy(xpath = "//input[@type='email']")
    WebElement txtUsername;

    @FindBy(xpath = "//input[@type='password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement btnSubmit;

    public void loginAs(String username, String password) {
        generics.type(txtUsername, username);
        testStepsLog("Enter Username : " + username);
        generics.pause(3);
        generics.clickOn(btnSubmit);
        generics.pause(3);
        testStepsLog("Click on SignIn button");
        generics.type(txtPassword, password);
        testStepsLog("Enter Password");
        generics.clickOn(btnSubmit);
        testStepsLog("Click on SignIn button");
        generics.clickOn(btnSubmit);

    }

    public void openFM() {
        generics.openFM();
    }

    public void selectSignIn(String userName) {
        testStepsLog("Click on SignIn button");
        System.out.println("//small[text()='" + userName + "']");
        generics.clickOn(localDriver.findElement(By.xpath("//small[text()='" + userName + "']")));
    }
}
