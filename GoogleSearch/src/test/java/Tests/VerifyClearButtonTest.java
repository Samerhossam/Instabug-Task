package Tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Pages.HomePage;

public class VerifyClearButtonTest extends TestBase{


	HomePage HomePageObject; //Declare Object from Home page

	@Test
	//Verify if clear button works method definition
	public void VerifyClearButton() {
		HomePageObject = new HomePage(Driver); //Assign object to web-driver
		String Keyword = "Instabug"; //Assign value to input keyword
		HomePageObject.SearchMethod(Keyword); //Call text input method
		HomePageObject.ClearMethod(); //Call method to clear search input field

		String Text = HomePageObject.SearchBox.getAttribute("value"); //Assign value in search input field to string text
		
		//Check if field is empty
		if(Text.isEmpty())
		{
			Assert.assertTrue(Text.isEmpty());
			System.out.println("Search box is cleared"); 
		}
		else {
			System.out.println("Search box is not cleared and has value " + Text);
		}
	}

	@AfterMethod
	//Take screenshot method declaration
	public void TakeScreenshotOnFailure(ITestResult Result) throws IOException {
		if(ITestResult.FAILURE == Result.getStatus()) {

			TakesScreenshot ts = (TakesScreenshot)Driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./Screenshots/" + Result.getName() + ".png"));
		}
	}
}


