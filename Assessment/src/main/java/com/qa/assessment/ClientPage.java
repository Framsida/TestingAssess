package com.qa.assessment;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClientPage {

    @FindBy(id = "menu_pim_viewPimModule")
    private WebElement pimButton;

    @FindBy(id = "menu_pim_addEmployee")
    private WebElement addEmployeeButton;

    @FindBy(id = "firstName")
    private WebElement firstNameText;

    @FindBy(id = "middleName")
    private WebElement middleNameText;

    @FindBy(id = "lastName")
    private WebElement lastNameText;

    @FindBy(id = "employeeId")
    private WebElement employeeIdText;

    @FindBy(xpath = "//*[@id=\"location_inputfileddiv\"]/div/input")
    private WebElement locationList;

    @FindBy(xpath = "//*[@id=\"select-options-3bb786fb-d024-3bec-9db1-d8a2cbcd062c\"]/li[18]")
    private WebElement australianHQ;

    @FindBy(xpath = "//*[@id=\"pimAddEmployeeForm\"]/div[1]/div/materializecss-decorator[3]/div/sf-decorator/div/label")
    private WebElement createLoginButton;

    @FindBy(id = "username")
    private WebElement usernameText;

    @FindBy(id = "password")
    private WebElement passwordText;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordText;

    @FindBy(id = "systemUserSaveBtn")
    private WebElement saveEmployeeButton;

    @FindBy(id = "menu_pim_viewEmployeeList")
    private WebElement viewEmployeesButton;



    void clickPimTab() {
        pimButton.click();
    }

    void clickEmployeeTab() {
        addEmployeeButton.click();
    }


    void enterEmployeeDetails(String firstName, String middleName, String lastName,
                      String employeeId, String location) {

        firstNameText.sendKeys(firstName);
        middleNameText.sendKeys(middleName);
        lastNameText.sendKeys(lastName);
        employeeIdText.sendKeys(employeeId);
        locationList.click();
        locationList.sendKeys(Keys.DOWN);
        locationList.sendKeys(Keys.DOWN);
        locationList.sendKeys(Keys.DOWN);
        locationList.sendKeys(Keys.ENTER);

    }

    void clickSaveEmployeeDetails() {
        saveEmployeeButton.click();
    }

    void clickCreateLoginDetails() {
        createLoginButton.click();
    }

    void enterLoginDetails(String username, String password, String confirmPassword){
        usernameText.sendKeys(username);
        passwordText.sendKeys(password);
        confirmPasswordText.sendKeys(confirmPassword);
    }

    void goToEmployeeList() {
        viewEmployeesButton.click();
    }


}
