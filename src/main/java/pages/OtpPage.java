package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class OtpPage extends BasePage {

    private By pinView = By.id("com.emedicoz.app:id/pinview");
    private By verifyBtn = By.id("com.emedicoz.app:id/verifyOtpBtn");
    private By closePopup = By.id("com.emedicoz.app:id/ivClose");

    public OtpPage(AndroidDriver driver) {
        super(driver);
    }
    // PIN Login Locators
    private By usePinLoginBtn = By.id("com.emedicoz.app:id/txtPinLogin");

    private By pin1 = By.xpath("(//android.widget.EditText)[0]");
    private By pin2 = By.xpath("(//android.widget.EditText)[1]");
    private By pin3 = By.xpath("(//android.widget.EditText)[2]");
    private By pin4 = By.xpath("(//android.widget.EditText)[3]");

    // PIN Method
    public void loginWithPin(String pin) {

        click(usePinLoginBtn);

        try { Thread.sleep(2000); } catch (Exception e) {}

        WebElement container = driver.findElement(
                By.id("com.emedicoz.app:id/choosePin")
        );

        List<WebElement> fields = container.findElements(
                By.className("android.widget.EditText")
        );

        for (int i = 0; i < pin.length(); i++) {
            fields.get(i).click();  // focus each field
            fields.get(i).clear();  // optional but safe
            fields.get(i).sendKeys(String.valueOf(pin.charAt(i)));
        }
    }

    public void enterOtp(String... digits) {
        WebElement container = waitForElement(pinView);
        List<WebElement> boxes = container.findElements(By.className("android.widget.EditText"));
        for (int i = 0; i < digits.length; i++) {
            boxes.get(i).sendKeys(digits[i]);
        }
        click(verifyBtn);
    }

    public void closePopupIfPresent() {
        try {
            click(closePopup);
        } catch (Exception e) {
            System.out.println("No popup found.");
        }
    }
}
