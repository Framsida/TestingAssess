package com.qa.assessment;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class AssessmentTest {

    private static final String FILEPATH = "report.html";
    private static ExtentReports extentReport = new ExtentReports(FILEPATH, true);
    private ExtentTest extentTest;

    private WebDriver driver;
    private ClientPage clientPage;
    private EmployeesPage employeesPage;
    private String searchText;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Development\\web_driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        startLog("Create User");
        extentTest.log(LogStatus.INFO, "Test started");




    }
    @After
    public void teardown() {
        driver.close();
        driver.quit();
        extentReport.flush();
    }

    @Given("^the login page$")
    public void the_login_page() throws Throwable {
        driver.get("https://qa-trials641.orangehrmlive.com/auth/login");
        extentTest.log(LogStatus.INFO, "Page successfully loaded");

    }

    @When("^I login$")
    public void i_login() throws Throwable {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login("Admin", "AdminAdmin");
        extentTest.log(LogStatus.INFO, "Logged In");

    }

    @When("^I click the PIM tab$")
    public void i_click_the_PIM_tab() throws Throwable {
        Thread.sleep(2000);
        clientPage = PageFactory.initElements(driver, ClientPage.class);
        clientPage.clickPimTab();
    }

    @When("^then the Add Employee Tab$")
    public void then_the_Add_Employee_Tab() throws Throwable {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_addEmployee")));

        clientPage.clickEmployeeTab();
    }

    @When("^I fill out the Add Employee Details correctly$")
    public void i_fill_out_the_Add_Employee_Details_correctly() throws Throwable {
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id("firstName")));

        searchText = RandomString.make();

        clientPage.enterEmployeeDetails(
                searchText,
                RandomString.make(),
                RandomString.make(),
                "",
                "Australia");
    }

    @When("^I choose to create Login Details by clicking the appropriate button$")
    public void i_choose_to_create_Login_Details_by_clicking_the_appropriate_button() throws Throwable {
        clientPage.clickCreateLoginDetails();
    }
    @When("^I fill out the Login Details correctly$")
    public void i_fill_out_the_Login_Details_correctly() throws Throwable {
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        clientPage.enterLoginDetails(RandomString.make(), "password", "password");
    }

    @When("^I click the Save button$")
    public void i_click_the_Save_button() throws Throwable {
        extentTest.log(LogStatus.INFO, "Details Entered");

        Thread.sleep(1000);
        clientPage.clickSaveEmployeeDetails();
    }

    @Then("^I can search for the Employee I have just created$")
    public void i_can_search_for_the_Employee_I_have_just_created() throws Throwable {


        new WebDriverWait(driver, 30).until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("pim.navbar.employeeName"),
                searchText
        ));

        extentTest.log(LogStatus.INFO, "User Created");

        clientPage.goToEmployeeList();



        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(
                By.id("employee_name_quick_filter_employee_list_value")));

        employeesPage = PageFactory.initElements(driver, EmployeesPage.class);

        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"employeeListTable\"]/tbody/tr[1]")
        ));
        employeesPage.searchEmployee(searchText);
    }

    @Then("^select them for inspection$")
    public void select_them_for_inspection() throws Throwable {


        new WebDriverWait(driver, 30).until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//*[@id=\"employeeListTable\"]/tbody/tr[1]/td[3]"),
                searchText
        ));


        int tries = 0;
        boolean breakIt;
        while(tries < 100) {
            try {
                tries++;
                breakIt = true;
                employeesPage.clickSearchedEmployee(searchText);
            } catch (Exception e) {
                breakIt = false;
            }
            if(breakIt) break;
        }



        if(driver.findElement(By.id("pim.navbar.employeeName")).getText().contains(searchText)) {
            extentTest.log(LogStatus.PASS, "TEST PASSED");
        } else {
            extentTest.log(LogStatus.FAIL, "There was an issue creating the user");
        }

    }


    private void startLog(String name) {
        extentTest = extentReport.startTest(name);
        extentTest.log(LogStatus.INFO, "Test Started");


    }



}
