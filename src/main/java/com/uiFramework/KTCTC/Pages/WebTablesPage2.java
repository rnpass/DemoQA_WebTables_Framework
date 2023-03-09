package com.uiFramework.KTCTC.Pages;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.uiFramework.KTCTC.helper.logger.LoggerHelper;
import com.uiFramework.KTCTC.helper.wait.WaitHelper;
import com.uiFramwork.KTCTC.ObjectPages.WebTablesObjectPage;

public class WebTablesPage2 {
	private Logger log = LoggerHelper.getLogger(WebTablesPage2.class);
	
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
	
	
	public WebTablesPage2(WebDriver driver)
	{
		this.driver = driver;
	}
	
	/**
	 * Method navigates to Webtable page
	 * 
	 */
	public void navigateToWebTablesPage()
	{
		log.info("Navigating to webtable page...");
		driver.findElement(webTableLinkOnElements).click();
		log.info("Navigated to webtable page");
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
	public void addNewUserWithProvidedDetails(WebTablesObjectPage webTablesObjectPage)
	{
		log.info("Adding new user on webtable page...");
		
		driver.findElement(addButtonOnWebTablePage).click();	
		
		/*
		 * WaitHelper wtHelp = new WaitHelper(driver);
		 * wtHelp.WaitForElementVisibleWithPollingTime(driver.findElement(
		 * firstNameonRegistrationForm), 5, 200);
		 */
		
		driver.findElement(firstNameonRegistrationForm).sendKeys(webTablesObjectPage.getfName());
		driver.findElement(lastNameonRegistrationForm).sendKeys(webTablesObjectPage.getlName());
		driver.findElement(emailonRegistrationForm).sendKeys(webTablesObjectPage.getEmail());
		driver.findElement(ageonRegistrationForm).sendKeys(webTablesObjectPage.getAge());
		driver.findElement(salaryonRegistrationForm).sendKeys(webTablesObjectPage.getSalary());
		driver.findElement(departmentonRegistrationForm).sendKeys(webTablesObjectPage.getDepartment());		
		driver.findElement(submitbuttononRegistrationForm).click();		
		log.info("New user added successfuly");
		
	}
	
	/**
	 * Method searchs provided string in search box
	 * @param email
	 */
	public void searchProvidedStringInSearchBox(String email)
	{
		log.info("Searching provided email in search box");
		driver.findElement(searchBoxOnWebTablePage).clear();
		driver.findElement(searchBoxOnWebTablePage).sendKeys(email);
		log.info("Searched provided email in search box");
		
	}
	
	/**
	 * Methods checks if user is present on webtables page
	 * @param email
	 * @return
	 */
	public boolean isProvidedUserWithEmailDisplayed(String email)
	{
		log.info("chking if user is displayed on webtable page...");
		boolean flag = false;
		searchProvidedStringInSearchBox(email);
		try {
			flag = driver.findElement(By.xpath("//*[contains(text(),'"+email+"')]")).isDisplayed();
			log.info("Provided user is displayed on webtable page");
		} catch (Exception e) {
			log.info("Provided user is not displayed on webtable page");
		}
		
		return flag;		
	}
	
	/**
	 * Method eidts existing user details 
	 * 
	 * @param webTablesObjectPageEdit
	 */
	public void editExistingUserWithProvidedDetails(WebTablesObjectPage webTablesObjectPageEdit)
	{
		log.info("Editing existing user on webtable page...");
		searchProvidedStringInSearchBox(webTablesObjectPageEdit.getEmail());
		
		driver.findElement(editButtonOnWebTablePage).click();
		
		driver.findElement(firstNameonRegistrationForm).clear();
		driver.findElement(firstNameonRegistrationForm).sendKeys(webTablesObjectPageEdit.getfName());
		driver.findElement(lastNameonRegistrationForm).clear();
		driver.findElement(lastNameonRegistrationForm).sendKeys(webTablesObjectPageEdit.getlName());
		driver.findElement(emailonRegistrationForm).clear();
		driver.findElement(emailonRegistrationForm).sendKeys(webTablesObjectPageEdit.getEmail());
		driver.findElement(ageonRegistrationForm).clear();
		driver.findElement(ageonRegistrationForm).sendKeys(webTablesObjectPageEdit.getAge());
		driver.findElement(salaryonRegistrationForm).clear();
		driver.findElement(salaryonRegistrationForm).sendKeys(webTablesObjectPageEdit.getSalary());
		driver.findElement(departmentonRegistrationForm).clear();
		driver.findElement(departmentonRegistrationForm).sendKeys(webTablesObjectPageEdit.getDepartment());		
		driver.findElement(submitbuttononRegistrationForm).click();	
		log.info("Edited existing user on webtable page");
		
	}
	
	/**
	 * Method deletes existing user from webtable page
	 * @param email
	 */
	public void deleteUserWithProvidedEmailId(String email)
	{
		log.info("Deleting user with provided email id...");
		searchProvidedStringInSearchBox(email);
		
		driver.findElement(deleteButtonOnWebTablePage).click();
		log.info("User deleted successfuly");
	}
	
	/**
	 * Method returns back all details of specified user
	 * @param email
	 * @return
	 */
	public HashMap<String,String> getAllDetailsOfProvidedUser(String email)
	{
		log.info("Getting all details of provided user...");
		searchProvidedStringInSearchBox(email);
		
		HashMap<String, String> data = new HashMap<>();
		
		data.put("FirstName", driver.findElement(By.xpath("//*[contains(text(),'"+email+"')]/preceding-sibling::div[3]")).getText());
		data.put("LastName", driver.findElement(By.xpath("//*[contains(text(),'"+email+"')]/preceding-sibling::div[2]")).getText());
		data.put("Age", driver.findElement(By.xpath("//*[contains(text(),'"+email+"')]/preceding-sibling::div[1]")).getText());
		data.put("Email", driver.findElement(By.xpath("//*[contains(text(),'"+email+"')]")).getText());
		data.put("Salary", driver.findElement(By.xpath("//*[contains(text(),'"+email+"')]/following-sibling::div[1]")).getText());
		data.put("Department", driver.findElement(By.xpath("//*[contains(text(),'"+email+"')]/following-sibling::div[2]")).getText());
		
		log.info("All details of user returned successfuly");
		return data;
	}
	

}
