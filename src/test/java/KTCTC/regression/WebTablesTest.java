package KTCTC.regression;

import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.uiFramework.KTCTC.Pages.WebTablesPage;
import com.uiFramework.KTCTC.testbase.TestBase;

public class WebTablesTest extends TestBase {

	WebTablesPage webTablesPage;

	String fname = cmObj.getcharacterString(5);
	String lname = cmObj.getcharacterString(5);
	String email = fname + "@" + lname + ".com";
	String age = cmObj.getNumericString(2);
	String salary = cmObj.getNumericString(5);
	String dept = cmObj.getcharacterString(5);

	String newfname = cmObj.getcharacterString(5);
	String newlname = cmObj.getcharacterString(5);
	String newemail = fname + "@" + lname + ".com";
	String newage = cmObj.getNumericString(2);
	String newsalary = cmObj.getNumericString(5);
	String newdept = cmObj.getcharacterString(5);

	@Test(priority = 1)
	public void verifyUserNavigatesToWebTablePage() {

		webTablesPage = new WebTablesPage(driver);
		SoftAssert sAssert = new SoftAssert();
		cmObj.navigateToReQuiredPage(driver, "Elements");
		webTablesPage.navigateToWebTablesPage();
		String cnt = driver.getCurrentUrl();
		sAssert.assertTrue(cnt.contains("webtables"));
		sAssert.assertAll();

	}

	@Test(priority = 2)
	public void verifyNewUserCanBeAddedonWebTablesPage() {
		SoftAssert sAssert = new SoftAssert();
		webTablesPage.addNewUserWithProvidedDetails(fname, lname, email, age, salary, dept);
		boolean flag = webTablesPage.isProvidedUserWithEmailDisplayed(email);
		sAssert.assertTrue(flag, "Newly added user is not displayed on UI");
		sAssert.assertAll();
	}

	@Test(priority = 3)
	public void verifyAllDetailsOfNewlyAddedUser() {
		HashMap<String, String> data = webTablesPage.getAllDetailsOfProvidedUser(email);
		SoftAssert sAssert = new SoftAssert();
		sAssert.assertEquals(data.get("FirstName"), fname, "First name of newly added user is not matched");
		sAssert.assertEquals(data.get("LastName"), lname, "Last name of newly added user is not matched");
		sAssert.assertEquals(data.get("Age"), age, "Age name of newly added user is not matched");
		sAssert.assertEquals(data.get("Email"), email, "Email  of newly added user is not matched");
		sAssert.assertEquals(data.get("Salary"), salary, "Salary  of newly added user is not matched");
		sAssert.assertEquals(data.get("Department"), dept, "Department  of newly added user is not matched");
		sAssert.assertAll();
	}

	@Test(priority = 4)
	public void verifyExistingUserCanBeEdited() {
		SoftAssert sAssert = new SoftAssert();
		webTablesPage.editExistingUserWithProvidedDetails(email, newfname, newlname, newemail, newage, newsalary,
				newdept);
		boolean flag = webTablesPage.isProvidedUserWithEmailDisplayed(newemail);
		sAssert.assertTrue(flag, "Edited user is not displayed on UI");
		sAssert.assertAll();

	}

	@Test(priority = 5)
	public void verifyAllDetailsOfEditedUser() {
		HashMap<String, String> data = webTablesPage.getAllDetailsOfProvidedUser(newemail);
		SoftAssert sAssert = new SoftAssert();
		sAssert.assertEquals(data.get("FirstName"), newfname, "First name of edited user is not matched");
		sAssert.assertEquals(data.get("LastName"), newlname, "Last name of edited user is not matched");
		sAssert.assertEquals(data.get("Age"), newage, "Age  of edited user is not matched");
		sAssert.assertEquals(data.get("Email"), newemail, "Email of edited user is not matched");
		sAssert.assertEquals(data.get("Salary"), newsalary, "Salary of edited user is not matched");
		sAssert.assertEquals(data.get("Department"), newdept, "Department of edited user is not matched");
		sAssert.assertAll();
	}

	@Test(priority = 6)
	public void verifyExistingUserCanBeDeleted() {
		SoftAssert sAssert = new SoftAssert();
		webTablesPage.deleteUserWithProvidedEmailId(newemail);
		boolean flag = webTablesPage.isProvidedUserWithEmailDisplayed(newemail);
		sAssert.assertFalse(flag, "Deleted user is still displayed on UI");
		sAssert.assertAll();

	}

}
