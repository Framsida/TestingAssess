package com.qa.assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(id = "txtUsername")
    private WebElement loginText;

    @FindBy(id = "txtPassword")
    private WebElement passwordText;

    @FindBy(id = "btnLogin")
    private WebElement loginButton;

    void login(String username, String password) {
        loginText.sendKeys(username);
        passwordText.sendKeys(password);
        loginButton.click();

    }
}
