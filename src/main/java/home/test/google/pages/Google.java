package home.test.google.pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ru.yandex.qatools.allure.annotations.Step;

public class Google {
	private WebDriver driver;
	private static final String baseUrl = "https://www.google.com/";

	@Step("Open google start page: " + baseUrl)
	public StartPage opneStartPage() {
		String pathSeparator = File.separator;
		String pathToDriver = "assets" + pathSeparator + "webdriver" + pathSeparator + "geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", pathToDriver);

		driver = new FirefoxDriver();
		driver.get(baseUrl);
		return new StartPage(driver);
	}

	public void close() {
		if (this.driver != null)
			this.driver.quit();
		this.driver = null;
	}
}
