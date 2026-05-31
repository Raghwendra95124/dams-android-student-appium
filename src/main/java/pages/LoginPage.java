package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    // Existing Locators
    private By loginRegBtn = By.id("com.emedicoz.app:id/btnLoginReg");
    private By mobileInput = By.id("com.emedicoz.app:id/mobileET");
    private By getOtpBtn = By.id("com.emedicoz.app:id/btnOtpGet");
    private By loggedOutMsg = By.id("com.emedicoz.app:id/txtLoggedOut");

    // 🔹 New Locators (Email Flow)
    private By loginWithEmail = By.id("com.emedicoz.app:id/txtRegister");
    private By emailInput = By.id("com.emedicoz.app:id/emailET");
    private By continueEmailBtn = By.id("com.emedicoz.app:id/btnOtpGetEmail");

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    // 🔹 Mobile Login
    public void enterMobileNumber(String mobile) {
        click(loginRegBtn);
        type(mobileInput, mobile);
        driver.hideKeyboard();
    }

    public void clickGetOtp() {
        click(getOtpBtn);
        handleSessionConflict();
    }

    // 🔹 Email Login (NEW FLOW)
    public void clickRegisterLogin() {
        click(loginRegBtn);
    }

    public void clickLoginWithEmail() {
        click(loginWithEmail);
    }

    public void enterEmail(String email) {
        type(emailInput, email);
        driver.hideKeyboard();
    }

    public void clickContinueEmail() {
        click(continueEmailBtn);
        handleSessionConflict(); // reuse same logic
    }

    // 🔹 Common Method
    private void handleSessionConflict() {
        try {
            click(loggedOutMsg);
            click(getOtpBtn); // works for both flows
        } catch (Exception e) {
            // No conflict message
        }

    }

    public boolean isLoginScreenVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginRegBtn));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
