package roi.roitest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import home.test.google.pages.Google;
import home.test.google.pages.StartPage;

public class ScreenKeyBoardTest {
	
	private StartPage startPage;
		private Google google = new Google();
	

	@BeforeClass
	public void setup() {
		startPage = google.opneStartPage();
	}

	@Test(priority = 1)
	public void testKeyboardAppearance() {
		startPage.openScreenKeyboard();
		Assert.assertTrue(startPage.isScreenKeyboardDisplayed());

	}

	@Test(priority = 2)
	public void testKeyBoardClose() throws InterruptedException {
		Thread.sleep(3000);
		startPage.closeScreenKeyboard();
		Assert.assertFalse(startPage.isScreenKeyboardDisplayed());

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		google.close();

	}
}
