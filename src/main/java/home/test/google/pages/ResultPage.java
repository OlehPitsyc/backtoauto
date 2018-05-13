package home.test.google.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

public class ResultPage {
	private WebDriver driver;

	@FindBy(css = ".g")
	private List<WebElement> resultList;

	public ResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, driver);

	}

	@Attachment("Search result number")
	@Step("Calculate the number of found results")
	public int getNumberOfresults() {
		return resultList.size();
	}
}
