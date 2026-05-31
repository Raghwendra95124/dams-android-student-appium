package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private By profileIcon = By.id("com.emedicoz.app:id/ivProfileImage2");
    private final By logoutBtn = By.id("com.emedicoz.app:id/logoutBtn");
    private By confirmYes = By.id("android:id/button2");

    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    public void goToProfile() {
        click(profileIcon);
    }

    public void performLogout() {

        // 1. Scroll to logout
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                        + "new UiSelector().resourceId(\"com.emedicoz.app:id/logoutBtn\"));"
        ));

        // 2. Click logout
        click(logoutBtn);

        // 3. Click Logout text in popup
        By confirmLogoutText = AppiumBy.androidUIAutomator("new UiSelector().text(\"Logout\")");
        wait.until(ExpectedConditions.elementToBeClickable(confirmLogoutText)).click();

        // 4. Click YES button (android dialog)
        wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button2"))).click();

        System.out.println("Logout confirmed. Waiting for Login Screen...");

        // ✅ 5. WAIT FOR LOGIN SCREEN (THIS WAS MISSING)

        By loginBtn = By.id("com.emedicoz.app:id/btnRegisterLogin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn));

        System.out.println("Login Screen displayed successfully ✅");
    }
}
