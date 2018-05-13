package home.test.google.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

public class StartPage {

	private WebDriver driver;

	@FindBy(id = "lst-ib")
	private WebElement searchInput;

	@FindBy(id = "gs_st0")
	private WebElement keyboardIcon;

	@FindBy(id = "kbd")
	private WebElement screenKeyboard;

	@FindBy(css = ".vk-btn")
	private List<WebElement> keys;

	@FindBy(css = ".vk-t-btn")
	private WebElement keyboardCloseButton;

	public StartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Step("Search for {0}")
	public ResultPage searchFor(String keywords) {
		typeIntoSearchInput(keywords);
		return new ResultPage(driver);
	}

	@Step("Type [{0}] into Search input")
	private void typeIntoSearchInput(String keywords) {
		searchInput.sendKeys(keywords);
		searchInput.submit();
	}

	@Step
	public void openScreenKeyboard() {
		clickKeyboardIcon();
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("kbd")));
	}

	@Step
	public void clickKeyboardIcon() {
		this.keyboardIcon.click();
	}

	@Step("Check if Screen Keyboard is displayed")
	public boolean isScreenKeyboardDisplayed() {
		return screenKeyboard.isDisplayed();
	}

	@Step("Close Screen Keyboard")
	public void closeScreenKeyboard() {
		clickScreenKeyboardCloseButton();
	}

	@Step
	private void clickScreenKeyboardCloseButton() {
		keyboardCloseButton.click();
	}

	public StartPage and() {
		return this;
	}

	private List<String> getLettersOnKeyboard() {
		List<String> letters = new ArrayList<>();
		for (WebElement key : keys) {
			letters.add(key.getText());
		}
		return letters;

		// return keys.stream().map(e ->
		// e.getText()).collect(Collectors.toList());
	}

	@Step("Type [{0}] from screen keyboard")
	public void typeFromScreenKeyboard(String string) {
		String[] letters = string.split("");
		List<String> lettersOnKeyboard = getLettersOnKeyboard();

		for (String letter : letters) {
			int index = lettersOnKeyboard.indexOf(letter);
			keys.get(index).click();
		}
	}

	@Attachment("Search Input text")
	@Step("Read text from search input")
	public String getSearchInputText() {
		return this.searchInput.getAttribute("value");
	}
}
