package journey;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class BaseJourneyTests {

	private String BASE_URL = "http://localhost:8080";

	private HtmlUnitDriver driver = new HtmlUnitDriver();

	protected void navigateTo(String url) {
		driver.navigate().to(BASE_URL + url);
	}

	protected String pageTitle() {
		return driver.getTitle();
	}
}
