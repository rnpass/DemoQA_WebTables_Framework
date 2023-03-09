package KTCTC.regression;

import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.uiFramework.KTCTC.Pages.WebTablesPage;
import com.uiFramework.KTCTC.Pages.WebTablesPage2;
import com.uiFramework.KTCTC.testbase.TestBase;
import com.uiFramwork.KTCTC.ObjectPages.WebTablesObjectPage;

public class WebTablesTest2 extends TestBase{

	WebTablesPage2 webTablesPage;
	
	WebTablesObjectPage webTablesObjectPage = new WebTablesObjectPage();
	WebTablesObjectPage webTablesObjectPageEdit = new WebTablesObjectPage();
	
	
	@Test (priority = 1)
	public void verifyUserNavigatesToWebTablePage()
	{
		webTablesObjectPage.setfName(cmObj.getcharacterString(5));
		webTablesObjectPage.setlName(cmObj.getcharacterString(5));
		webTablesObjectPage.setEmail(webTablesObjectPage.getfName()+"@"+webTablesObjectPage.getlName()+".com");
		webTablesObjectPage.setAge(cmObj.getNumericString(2));
		webTablesObjectPage.setSalary(cmObj.getNumericString(5));
		webTablesObjectPage.setDepartment(cmObj.getcharacterString(5));
		
		webTablesObjectPageEdit.setfName(cmObj.getcharacterString(5));
		webTablesObjectPageEdit.setlName(cmObj.getcharacterString(5));
		webTablesObjectPageEdit.setEmail(webTablesObjectPage.getfName()+"@"+webTablesObjectPage.getlName()+".com");
		webTablesObjectPageEdit.setAge(cmObj.getNumericString(2));
		webTablesObjectPageEdit.setSalary(cmObj.getNumericString(5));
		webTablesObjectPageEdit.setDepartment(cmObj.getcharacterString(5));
		
		
		
		 webTablesPage = new WebTablesPage2(driver);
		SoftAssert sAssert = new SoftAssert();
		cmObj.navigateToReQuiredPage(driver,"Elements");
		webTablesPage.navigateToWebTablesPage();
		String cnt = driver.getCurrentUrl();
		sAssert.assertTrue(cnt.contains("webtables"));
		sAssert.assertAll();
		
	}
	
	@Test (priority = 2)
	public void verifyNewUserCanBeAddedonWebTablesPage()
	{		
		SoftAssert sAssert = new SoftAssert();
		webTablesPage.addNewUserWithProvidedDetails(webTablesObjectPage);
		boolean flag = webTablesPage.isProvidedUserWithEmailDisplayed(webTablesObjectPage.getEmail());
		sAssert.assertTrue(flag, "Newly added user is not displayed on UI");
		sAssert.assertAll();
	}
	
	@Test (priority = 3)
	public void verifyAllDetailsOfNewlyAddedUser()
	{
		HashMap<String, String> data = webTablesPage.getAllDetailsOfProvidedUser(webTablesObjectPage.getEmail());
		SoftAssert sAssert = new SoftAssert();
		sAssert.assertEquals(data.get("FirstName"), webTablesObjectPage.getfName(), "First name of newly added user is not matched");
		sAssert.assertEquals(data.get("LastName"), webTablesObjectPage.getlName(), "Last name of newly added user is not matched");
		sAssert.assertEquals(data.get("Age"), webTablesObjectPage.getAge(), "Age name of newly added user is not matched");
		sAssert.assertEquals(data.get("Email"), webTablesObjectPage.getEmail(), "Email  of newly added user is not matched");
		sAssert.assertEquals(data.get("Salary"), webTablesObjectPage.getSalary(), "Salary  of newly added user is not matched");
		sAssert.assertEquals(data.get("Department"), webTablesObjectPage.getDepartment(), "Department  of newly added user is not matched");
		sAssert.assertAll();
	}
	
	@Test (priority = 4)
	public void verifyExistingUserCanBeEdited()
	{
		SoftAssert sAssert = new SoftAssert();
		webTablesPage.editExistingUserWithProvidedDetails(webTablesObjectPageEdit);
		boolean flag = webTablesPage.isProvidedUserWithEmailDisplayed(webTablesObjectPageEdit.getEmail());
		sAssert.assertTrue(flag, "Edited user is not displayed on UI");
		sAssert.assertAll();		
		
	}
	
	@Test (priority = 5)
	public void verifyAllDetailsOfEditedUser()
	{
		HashMap<String, String> data = webTablesPage.getAllDetailsOfProvidedUser(webTablesObjectPageEdit.getEmail());
		SoftAssert sAssert = new SoftAssert();
		sAssert.assertEquals(data.get("FirstName"), webTablesObjectPageEdit.getfName(), "First name of edited user is not matched");
		sAssert.assertEquals(data.get("LastName"), webTablesObjectPageEdit.getlName(), "Last name of edited user is not matched");
		sAssert.assertEquals(data.get("Age"), webTablesObjectPageEdit.getAge(), "Age  of edited user is not matched");
		sAssert.assertEquals(data.get("Email"), webTablesObjectPageEdit.getEmail(), "Email of edited user is not matched");
		sAssert.assertEquals(data.get("Salary"), webTablesObjectPageEdit.getSalary(), "Salary of edited user is not matched");
		sAssert.assertEquals(data.get("Department"), webTablesObjectPageEdit.getDepartment(), "Department of edited user is not matched");
		sAssert.assertAll();
	}
	@Test (priority = 6)
	public void verifyExistingUserCanBeDeleted()
	{
		SoftAssert sAssert = new SoftAssert();
		webTablesPage.deleteUserWithProvidedEmailId(webTablesObjectPageEdit.getEmail());
		boolean flag = webTablesPage.isProvidedUserWithEmailDisplayed(webTablesObjectPageEdit.getEmail());		
		sAssert.assertFalse(flag, "Deleted user is still displayed on UI");
		sAssert.assertAll();
		
	}
	
}
