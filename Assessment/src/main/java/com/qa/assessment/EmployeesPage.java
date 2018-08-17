package com.qa.assessment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EmployeesPage {
    @FindBy(xpath = "//*[@id=\"employeeListTable\"]/tbody")
    private WebElement employeetable;

    @FindBy(id = "employee_name_quick_filter_employee_list_value")
    private WebElement searchEmployeeText;

    @FindBy(id = "quick_search_icon")
    private WebElement quickSearchButton;

    void searchEmployee(String searchText) throws Throwable {
        searchEmployeeText.sendKeys(searchText);
        Thread.sleep(1000);
        quickSearchButton.click();

    }

    void clickSearchedEmployee(String searchText) {
        List<WebElement> tableRows = employeetable.findElements(By.tagName("tr"));
        for (WebElement tableRow : tableRows) {
            List<WebElement> tableColumns = tableRow.findElements(By.tagName("td"));
            for (WebElement column : tableColumns) {
                if(column.getText().equals(searchText)) {
                    column.click();
                    return;
                }
            }
        }
    }
}
