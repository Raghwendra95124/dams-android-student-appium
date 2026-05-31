package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class CbtPage {

    public static AndroidDriver driver;
    static WebDriverWait wait;

    // ✅ Constructor
    public CbtPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        TestSeries.setDriver(driver);
    }

    // 🔹 Locators
    private final By cbtTab = By.id("com.emedicoz.app:id/nav_cbt");
    private final By filterAll = By.xpath("//android.widget.TextView[@text='All']");
    private final By neetPgPackages = By.xpath("//android.widget.TextView[@text='NEETPG CBT Packages']");
    private final By fmgePackages = By.xpath("//android.widget.TextView[@text='FMGE CBT Packages']"); // ✅ FIXED

    private final By testInstructions = By.id("com.emedicoz.app:id/btnInstruction");
    private final By backButton = By.id("com.emedicoz.app:id/ivBack");

    private final By buyNow = By.id("com.emedicoz.app:id/btnBuyNow");
    private final By cbtRadioBtn = By.id("com.emedicoz.app:id/rbCBT");
    private final By okCbtBtn = By.id("com.emedicoz.app:id/btnOkCBT");

    private final By searchIcon = By.id("com.emedicoz.app:id/searchButton");
    private final By searchInput = By.id("com.emedicoz.app:id/search_src_text");

    private final By courseCard = By.id("com.emedicoz.app:id/parentCard");

    private final By placeOrder = By.id("com.emedicoz.app:id/text_place_order");
    private final By razorPayOption = By.id("com.emedicoz.app:id/btn_rozar");
    private final By payButton = By.id("com.emedicoz.app:id/btn_continue");

    private final By goBackBtn = By.xpath("//android.widget.Button[@text='Go back']");
    private final By exitYesBtn = By.xpath("//android.widget.Button[@text='Yes, exit']");

    private final By headerLayout = By.id("com.emedicoz.app:id/headerLayout");
    private final By tryAgainBtn = By.id("com.emedicoz.app:id/btnHome");
    private final By paytmOption = By.id("com.emedicoz.app:id/btn_paytm");
    // 🔹 Paytm Option
    private final By paytm = By.id("com.emedicoz.app:id/btn_paytm");

    // 🔹 Paytm Back Button (WebView)
    private final By paytmBackBtn = By.xpath("//android.view.View[@resource-id='ptm-checkout-header']/android.view.View[1]/android.widget.Button");

    // 🔹 Skip Feedback Button
    private final By skipFeedbackBtn = By.xpath("//android.widget.Button[@text='Skip Feedback']");

    // 🔹 Percentage Input
    private static final By percentageField = By.id("com.emedicoz.app:id/etPercentage");

    // 🔹 Radio Group Options
    private static final By radioOptions = By.xpath("//android.widget.RadioGroup[@resource-id='com.emedicoz.app:id/radioGroup']//android.widget.RadioButton");

    // 🔹 DOB Field
    private static final By dobField = By.id("com.emedicoz.app:id/tvDob");

    // 🔹 Submit Button
    private static final By submitBtn = By.id("com.emedicoz.app:id/nextBtn");

    // 🔹 Date Picker ViewPager
    private final By datePickerView = By.id("android:id/day_picker_view_pager");

    // 🔹 Month View
    private final By monthView = By.id("android:id/month_view");

    // 🔹 Year Header (e.g. 2026)
    private final By yearHeader = By.id("android:id/date_picker_header_year");
    // Okay Button
    private final By datePickerOkBtn = By.id("android:id/button1");

    private final By yearPickerList = By.id("android:id/date_picker_year_picker");

    // 🔹 FrameLayout (Generic)
    private final By frameLayout = By.className("android.widget.FrameLayout");

    // 🔹 Helper Methods
    static void safeClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void clickIfPresent(By locator) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement el = shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            el.click();
        } catch (Exception e) {
            System.out.println("Element not present → skipping");
        }
    }

    // 🔹 Main Flow
    public void completeCBTFlow() {

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));
        Random random = new Random();

        // 🔹 1. Click CBT Tab
        safeClick(cbtTab);

        // 🔹 2. Wait for Filters
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterAll));

        // 🔹 3. Filters
        safeClick(neetPgPackages);
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterAll));

        safeClick(fmgePackages);
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterAll));

        safeClick(filterAll);

        // 🔹 4. Instructions
        safeClick(testInstructions);
        safeClick(backButton);

        // 🔹 5. Buy Now
        List<WebElement> buyList = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(buyNow)
        );

        if (!buyList.isEmpty()) {
            buyList.get(0).click();
        } else {
            throw new RuntimeException("No Buy Now button found");
        }

        // 🔹 6. CBT Selection
        safeClick(cbtRadioBtn);
        safeClick(okCbtBtn);

        // 🔹 7. Search
        safeClick(searchIcon);

        WebElement search = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        );
        search.clear();
        search.sendKeys("Delhi");

        // 🔹 8. Select Card
        List<WebElement> cards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(courseCard)
        );

        if (!cards.isEmpty()) {
            cards.get(0).click();
        } else {
            throw new RuntimeException("No center card found");
        }

        // =========================================================
        // 🔥 USER DETAILS HANDLING (MERGED HERE)
        // 🔹 USER DETAILS + DOB FINAL WORKING CODE
        try {
            shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));
            random = new Random();

            // 🔹 1. Check Percentage Field
            WebElement percentage = shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(percentageField)
            );

            System.out.println("User Details Screen Found ✅");

            // 🔹 2. Enter Percentage
            percentage.clear();
            int value = 50 + random.nextInt(51);
            percentage.sendKeys(String.valueOf(value));
            System.out.println("Entered Percentage: " + value);

            // 🔹 3. Select Gender (Male)
            List<WebElement> options = shortWait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(radioOptions)
            );

            if (!options.isEmpty()) {
                options.get(0).click();
                System.out.println("Selected Male");
            }

            // =====================================================
            // 🔥 DOB HANDLING (FINAL FIX WITH YEAR PICKER)
            // =====================================================

            // 🔹 Click DOB Field
            safeClick(dobField);

            // 🔹 Wait Date Picker
            wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerView));

            // 🔹 Click Year Header
            safeClick(datePickerView);

            // 🔹 Wait Year Picker List (IMPORTANT)
            wait.until(ExpectedConditions.visibilityOfElementLocated(yearPickerList));

            // 🔹 Select Random Year
            int randomYear = 1990 + random.nextInt(15);

            By yearLocator = By.xpath("//android.widget.TextView[@text='" + randomYear + "']");

            // 🔹 Scroll INSIDE Year Picker (FIXED)
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().resourceId(\"android:id/date_picker_year_picker\"))" +
                            ".scrollIntoView(new UiSelector().text(\"" + randomYear + "\"));"
            ));

            // 🔹 Wait + Click Year
            WebElement yearElement = wait.until(
                    ExpectedConditions.elementToBeClickable(yearLocator)
            );
            yearElement.click();

            System.out.println("Selected Year: " + randomYear);

            // 🔹 Click OK Button (AFTER YEAR SELECTION)
            wait.until(ExpectedConditions.elementToBeClickable(datePickerOkBtn)).click();

            System.out.println("Clicked OK Button");

            // 🔹 Select Random Day
            int randomDay = 1 + random.nextInt(28);

            By dayLocator = By.xpath("//android.view.View[@text='" + randomDay + "']");
            wait.until(ExpectedConditions.elementToBeClickable(dayLocator)).click();

            System.out.println("Selected Day: " + randomDay);

            // 🔹 Wait Picker Close
            shortWait.until(ExpectedConditions.elementToBeClickable(datePickerOkBtn));
            safeClick(datePickerOkBtn);

            // 🔹 Submit
            safeClick(submitBtn);

            System.out.println("✅ User Details Submitted Successfully");

        } catch (Exception e) {
            System.out.println("User Details NOT present → Skipping ❌");
        }

        // =========================================================
        // 🔹 9. Scroll to Place Order
        // =========================================================
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().resourceId(\"com.emedicoz.app:id/text_place_order\"));"
            ));
        } catch (Exception e) {
            System.out.println("Scroll not needed");
        }

        // 🔹 10. Click Place Order
        wait.until(ExpectedConditions.presenceOfElementLocated(placeOrder));
        safeClick(placeOrder);

        System.out.println("✅ CBT Flow Completed (All-in-One)");
    }
    public void completePaymentFlow() {

        // 🔹 1. Select Razor Pay
        safeClick(razorPayOption);

        // 🔹 2. Click PAY
        safeClick(payButton);

        // 🔹 3. Wait for Go Back button (short controlled wait)
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(50));

        shortWait.until(ExpectedConditions.presenceOfElementLocated(goBackBtn));

        // 🔹 4. Click Go Back
        safeClick(goBackBtn);

        // 🔹 5. Click Yes Exit
        safeClick(exitYesBtn);

        System.out.println("✅ Payment Flow Completed");
    }

    // 🔹 Click Paytm Back Button (after payment screen loads)
    public void completePaytmFlow() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        // 🔹 1. Select Paytm
        wait.until(ExpectedConditions.elementToBeClickable(paytmOption));
        safeClick(paytmOption);
        System.out.println("Selected Paytm");

        // 🔹 2. Click PAY Button
        wait.until(ExpectedConditions.elementToBeClickable(payButton));
        safeClick(payButton);
        System.out.println("Clicked PAY");

        // 🔹 3. Wait for Paytm Back Button (Payment Screen)
        wait.until(ExpectedConditions.presenceOfElementLocated(paytmBackBtn));
        safeClick(paytmBackBtn);
        System.out.println("Clicked Paytm Back");

        // 🔹 4. Wait for Skip Feedback Button
        wait.until(ExpectedConditions.elementToBeClickable(skipFeedbackBtn));
        safeClick(skipFeedbackBtn);
        System.out.println("Clicked Skip Feedback");

        System.out.println("✅ Paytm Flow Completed");
    }


    // CBT Registration Selection

    public static void handleUserDetailsIfPresent() {

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));
        Random random = new Random();

        try {
            // 🔹 Check if Percentage Field is present
            WebElement percentage = shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(percentageField)
            );

            System.out.println("User Details Screen Found ✅");

            // 🔹 1. Enter Random Percentage (50–100)
            percentage.clear();
            int value = 50 + random.nextInt(51);
            percentage.sendKeys(String.valueOf(value));
            System.out.println("Entered Percentage: " + value);

            // 🔹 2. Select Gender Options One by One
            List<WebElement> options = shortWait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(radioOptions)
            );

            for (WebElement option : options) {
                option.click();
                Thread.sleep(500); // small delay to simulate selection
            }

            // 🔹 Select Male (Assuming first option is Male)
            if (!options.isEmpty()) {
                options.get(0).click();
                System.out.println("Selected Male Option");
            }

            // 🔹 3. Select DOB (Click only - picker handling depends on UI)
            safeClick(dobField);
            System.out.println("Clicked DOB Field");

            // 👉 OPTIONAL: You can enhance DOB picker here

            // 🔹 4. Click Submit
            safeClick(submitBtn);
            System.out.println("Clicked Submit");

        } catch (Exception e) {
            System.out.println("User Details Screen NOT Present → Skipping ❌");
        }
    }


    public void clickTryAgain() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tryAgainBtn)).click();
    }

    public void selectPaytm() {
        wait.until(ExpectedConditions.elementToBeClickable(paytmOption)).click();
    }
}





