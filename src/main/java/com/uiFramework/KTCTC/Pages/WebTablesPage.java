package com.uiFramework.KTCTC.Pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.uiFramework.KTCTC.helper.wait.WaitHelper;

/**
 * @author dell
 *
 */
public class WebTablesPage {
	private WebDriver driver;
	// use alt+ shift+j
	// Web tables page locators
	By addButtonOnWebTablePage = By.id("addNewRecordButton");
	By searchBoxOnWebTablePage = By.id("searchBox");
	By editButtonOnWebTablePage = By.xpath("//span[contains(@id,'edit-record')]");
	By deleteButtonOnWebTablePage = By.xpath("//span[contains(@id,'delete-record')]");
	// Registration forms locators of webtable page
	By firstNameonRegistrationForm = By.id("firstName");
	By lastNameonRegistrationForm = By.id("lastName");
	By emailonRegistrationForm = By.id("userEmail");
	By ageonRegistrationForm = By.id("age");
	By salaryonRegistrationForm = By.id("salary");
	By departmentonRegistrationForm = By.id("department");
	By submitbuttononRegistrationForm = By.id("submit");

	By webTableLinkOnElements = By.xpath("//*[contains(text(),'Web Tables')]");

	public WebTablesPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method Navigate from Tool QA Home PAge to WebTAble TAb Present on Home
	 * Page Of Tool QA
	 */
	public void navigateToWebTablesPage() {
		driver.findElement(webTableLinkOnElements).click();
	}

	/**
	 * Method adds new user on webtable page with provided details
	 * 
	 * @param driver
	 * @param fname
	 * @param lname
	 * @param email
	 * @param age
	 * @param salary
	 * @param dept
	 */
	public void addNewUserWithProvidedDetails(String fname, String lname, String email, String age, String salary,
			String dept) {
		driver.findElement(addButtonOnWebTablePage).click();

		/*
		 * WaitHelper wtHelp = new WaitHelper(driver);
		 * wtHelp.WaitForElementVisibleWithPollingTime(driver.findElement(
		 * firstNameonRegistrationForm), 5, 200);
		 */

		driver.findElement(firstNameonRegistrationForm).sendKeys(fname);
		driver.findElement(lastNameonRegistrationForm).sendKeys(lname);
		driver.findElement(emailonRegistrationForm).sendKeys(email);
		driver.findElement(ageonRegistrationForm).sendKeys(age);
		driver.findElement(salaryonRegistrationForm).sendKeys(salary);
		driver.findElement(departmentonRegistrationForm).sendKeys(dept);
		driver.findElement(submitbuttononRegistrationForm).click();

	}

	public void searchProvidedStringInSearchBox(String email) {
		driver.findElement(searchBoxOnWebTablePage).clear();
		driver.findElement(searchBoxOnWebTablePage).sendKeys(email);

	}

	public boolean isProvidedUserWithEmailDisplayed(String email) {
		boolean flag = false;
		searchProvidedStringInSearchBox(email);
		try {
			flag = driver.findElement(By.xpath("//*[contains(text(),'" + email + "')]")).isDisplayed();
		} catch (Exception e) {

		}

		return flag;
	}

	public void editExistingUserWithProvidedDetails(String existingEmail, String fname, String lname, String email,
			String age, String salary, String dept) {
		searchProvidedStringInSearchBox(existingEmail);

		driver.findElement(editButtonOnWebTablePage).click();

		driver.findElement(firstNameonRegistrationForm).clear();
		driver.findElement(firstNameonRegistrationForm).sendKeys(fname);
		driver.findElement(lastNameonRegistrationForm).clear();
		driver.findElement(lastNameonRegistrationForm).sendKeys(lname);
		driver.findElement(emailonRegistrationForm).clear();
		driver.findElement(emailonRegistrationForm).sendKeys(email);
		driver.findElement(ageonRegistrationForm).clear();
		driver.findElement(ageonRegistrationForm).sendKeys(age);
		driver.findElement(salaryonRegistrationForm).clear();
		driver.findElement(salaryonRegistrationForm).sendKeys(salary);
		driver.findElement(departmentonRegistrationForm).clear();
		driver.findElement(departmentonRegistrationForm).sendKeys(dept);
		driver.findElement(submitbuttononRegistrationForm).click();

	}

	public void deleteUserWithProvidedEmailId(String email) {
		searchProvidedStringInSearchBox(email);

		driver.findElement(deleteButtonOnWebTablePage).click();
	}

	public HashMap<String, String> getAllDetailsOfProvidedUser(String email) {
		
		searchProvidedStringInSearchBox(email);

		HashMap<String, String> data = new HashMap<>();

		data.put("FirstName", driver
				.findElement(By.xpath("//*[contains(text(),'" + email + "')]/preceding-sibling::div[3]")).getText());
		data.put("LastName", driver
				.findElement(By.xpath("//*[contains(text(),'" + email + "')]/preceding-sibling::div[2]")).getText());
		data.put("Age", driver.findElement(By.xpath("//*[contains(text(),'" + email + "')]/preceding-sibling::div[1]"))
				.getText());
		data.put("Email", driver.findElement(By.xpath("//*[contains(text(),'" + email + "')]")).getText());
		data.put("Salary", driver
				.findElement(By.xpath("//*[contains(text(),'" + email + "')]/following-sibling::div[1]")).getText());
		data.put("Department", driver
				.findElement(By.xpath("//*[contains(text(),'" + email + "')]/following-sibling::div[2]")).getText());

		return data;
	}

}
