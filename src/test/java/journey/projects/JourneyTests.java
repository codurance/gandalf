package journey.projects;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class JourneyTests {

	private String BASE_URL = "http://localhost:4567";

	private HtmlUnitDriver driver = new HtmlUnitDriver();

	protected void navigateTo(String url) {
		driver.navigate().to(BASE_URL + url);
	}

	protected String pageTitle() {
		return driver.getTitle();
	}
}
