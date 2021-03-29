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

public class AutocompleteTest extends TestBase{

	HomePage HomePageObject; //Declare Object from Home page

	@Test
	//verify auto-complete suggestion method definition
	public void AutocompleteSuggestion() {
		HomePageObject = new HomePage(Driver); //Assign object to web-driver
		String Keyword = "Instab"; //Assign value to input keyword
		HomePageObject.SearchMethod(Keyword); //Call text input method
		int counter = 0; //Initialise counter
		
		//method to loop over values in list of suggestions
		for(int i=0; i<HomePageObject.Suggestions.size(); i++)
		{
			//Check if suggestion matches typed keyword and print the list of suggestions
			if(HomePageObject.Suggestions.get(i).getText().toLowerCase().startsWith(Keyword.toLowerCase())) {
				System.out.println(HomePageObject.Suggestions.get(i).getText());
				Assert.assertTrue(HomePageObject.Suggestions.get(i).getText().toLowerCase().contains(Keyword.toLowerCase()));
				counter++; //Increment counter
			}
		}
		Assert.assertNotEquals(counter, 0); //Check if value of number of suggestions is not equal zero
		System.out.println("Google provided " + counter + " suggestions"); //Print number of suggestions
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

