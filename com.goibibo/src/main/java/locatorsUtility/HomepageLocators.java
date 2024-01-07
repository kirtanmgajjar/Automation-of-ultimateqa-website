package locatorsUtility;

import org.openqa.selenium.By;

public class HomepageLocators {
		public static final By signInBtn = By.id("signInLink");
		public static final By emailBox = By.id("signInName");
		public static final By passwordBox = By.id("password");
		public static final By loginBtn = By.id("next");
		public static final By userBtn = By.cssSelector(".topbar__extra-item button");
		public static final By profileBtn = By.xpath("(//div[@class=\"topbar__user-dropdown-menu\"]//a)[1]");
		public static final By basicProf = By.className("basic-info");
}
