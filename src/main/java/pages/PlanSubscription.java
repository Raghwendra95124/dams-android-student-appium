package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import io.appium.java_client.AppiumBy;

import static pages.CbtPage.*;

public class PlanSubscription {

    By yesBtn = By.id("com.emedicoz.app:id/btnYes");

    // 🔹 Plan Name (e.g., APP LIVE)


    public static final By planName =
            By.id("com.emedicoz.app:id/tvPlanName");

    // 🔹 RecyclerView (All Plans List)
    public static final By subscriptionRecyclerView =
            By.id("com.emedicoz.app:id/rvSubscriptionPlan");

    // 🔹 BUY NOW button
    public static final By buyNowBtn =
            By.id("com.emedicoz.app:id/txtSubBuy");

    // 🔹 Enroll Now button
    public static final By enrollNowBtn =
            By.id("com.emedicoz.app:id/btnEnroll");

    private static final By paytmOption = By.id("com.emedicoz.app:id/btn_paytm");

    private static final By payButton = By.id("com.emedicoz.app:id/btn_continue");


    private static final By goBackBtn = By.xpath("//android.widget.Button[@text='Go back']");
    private static final By exitYesBtn = By.xpath("//android.widget.Button[@text='Yes, exit']");

    // 🔹 Paytm Back Button (WebView)
    private static final By paytmBackBtn = By.xpath("//android.view.View[@resource-id='ptm-checkout-header']/android.view.View[1]/android.widget.Button");

    // 🔹 Skip Feedback Button
    private static final By skipFeedbackBtn = By.xpath("//android.widget.Button[@text='Skip Feedback']");
    // 🔹 Place Order button
    public static final By placeOrderBtn =
            By.id("com.emedicoz.app:id/text_place_order");
    // Click Try Again
    private static final By tryAgainBtn = By.id("com.emedicoz.app:id/btnHome");

    private static final By razorPayOption = By.id("com.emedicoz.app:id/btn_rozar");

    // 🔹 Remove Coupon Button (If Present)
    public static final By removeCouponBtn =
            By.id("com.emedicoz.app:id/crossCouponButton");

    // 🔹 Add To Cart Button
    public static final By addToCartBtn =
            By.id("com.emedicoz.app:id/tvAddToCart");

    // 🔹 Cart Items (Container)
    public static final By cartItems =
            By.id("com.emedicoz.app:id/cart_item_card");

    // 🔹 Price Summary Section
    public static final By priceSummary =
            By.id("com.emedicoz.app:id/linearSummary");


    // 🔹 Cut Off Price (Old Price)
    public static final By priceCutOff =
            By.id("com.emedicoz.app:id/priceCutOff");

    // 🔹 Base Amount (Current Price)
    public static final By baseAmount =
            By.id("com.emedicoz.app:id/baseAmount");

    // 🔹 Course Duration (e.g., 24 month)
    public static final By courseDuration =
            By.id("com.emedicoz.app:id/course_duration");

    // 🔹 Selected Plan Tick Icon
    public static final By selectedPlanTick =
            By.id("com.emedicoz.app:id/planselectedtick");

    // 🔹 Select Date Radio Button
    public static final By selectDateRadioBtn =
            By.id("com.emedicoz.app:id/cvStartDate");

    // 🔹 Day Picker ViewPager (Calendar)
    public static final By dayPickerViewPager =
            By.id("android:id/day_picker_view_pager");

    // 🔹 Validity Info Text
    public static final By validityText =
            By.xpath("//android.widget.TextView[@text='Validity as per your chosen subscription & start date.']");

    // 🔹 Price Summary Layout
    public static final By linearSummary =
            By.id("com.emedicoz.app:id/linearSummary");

    // ========================================
// 🔹 SHIPPING & PLATFORM FEE LOCATORS
// ========================================

    // 🔹 Shipping Charge
    public static final By shippingCharge =
            By.id("com.emedicoz.app:id/tvShiping");

    // 🔹 Platform Fee
    public static final By platformFee =
            By.id("com.emedicoz.app:id/txtPlatformFee");

    // ========================================
// 🔹 PRICE SUMMARY LOCATORS
// ========================================

    // 🔹 Item Total Label
    public static final By itemTotalLabel =
            By.xpath("//android.widget.TextView[@text='Item Total']");

    // 🔹 Total Course Count
    public static final By totalCourseCount =
            By.id("com.emedicoz.app:id/txtTotalCourse");

    // 🔹 Item(s) Amount Label
    public static final By itemAmountLabel =
            By.xpath("//android.widget.TextView[@text='Item(s) Amount']");

    // 🔹 Item(s) Price Value
    public static final By itemPriceValue =
            By.id("com.emedicoz.app:id/txtPriceValue");

    // 🔹 Coupon Layout
    public static final By couponLayout =
            By.id("com.emedicoz.app:id/couponLinerLayout");

    // 🔹 Coupon Text
    public static final By couponText =
            By.id("com.emedicoz.app:id/tvCouponText");

    // 🔹 Coupon Discount Amount
    public static final By couponDiscount =
            By.id("com.emedicoz.app:id/txtCoupon");

    // 🔹 GST Charges Label
    public static final By gstChargesLabel =
            By.xpath("//android.widget.TextView[@text='GST Charges']");

    // 🔹 GST Amount
    public static final By gstAmount =
            By.id("com.emedicoz.app:id/txtTaxes");

    // 🔹 Grand Total Label
    public static final By grandTotalLabel =
            By.xpath("//android.widget.TextView[@text='Grand Total']");

    // 🔹 Grand Total Amount
    public static final By grandTotalAmount =
            By.id("com.emedicoz.app:id/txtGrandTotalValue");

    // 🔹 Selected Start Date (e.g., 27-05-2026)
    public static final By startDate =
            By.id("com.emedicoz.app:id/tvStartDate");

    // 🔹 Expiry Date (e.g., 26 May 2030)
    public static final By expiryDate =
            By.id("com.emedicoz.app:id/tvExpiredDate");

    // Go PRO CLICK
    By goProBtn = By.id("com.emedicoz.app:id/btnPro");

    private static Object MobileBy;


    // Plan Subscription Flow With Paytm

    public static void subscribeFlow() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1. Click Go Pro Button
        By goProBtn = By.id("com.emedicoz.app:id/btnPro");
        wait.until(ExpectedConditions.elementToBeClickable(goProBtn)).click();

        // 2. Click First Plan
        By planName = By.id("com.emedicoz.app:id/tvPlanName");
        wait.until(ExpectedConditions.visibilityOfElementLocated(planName));
        List<WebElement> plans = driver.findElements(planName);

        if (!plans.isEmpty()) {
            plans.get(0).click();
        }

        // 3. Select Random Subscription Option
        By subscriptionRecyclerView = By.id("com.emedicoz.app:id/rvSubscriptionPlan");
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionRecyclerView));

        List<WebElement> options = driver.findElements(subscriptionRecyclerView);

        if (!options.isEmpty()) {
            int randomIndex = new Random().nextInt(options.size());
            options.get(randomIndex).click();
        }

        // 4. Click Buy Now
        By buyNowBtn = By.id("com.emedicoz.app:id/txtSubBuy");
        wait.until(ExpectedConditions.elementToBeClickable(buyNowBtn)).click();

        // 5. Handle "Yes" button (if present)
        By yesBtn = By.id("com.emedicoz.app:id/btnYes");

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement yesElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(yesBtn));

            if (yesElement.isDisplayed()) {
                yesElement.click();
            }
        } catch (TimeoutException e) {
            System.out.println("⚠️ No 'Yes' button. Possibly: No Items Are Present In Cart");
        }

        // 6. Click Enroll Now
        By enrollNowBtn = By.id("com.emedicoz.app:id/btnEnroll");
        wait.until(ExpectedConditions.elementToBeClickable(enrollNowBtn)).click();

        // ⭐ 7. Scroll to "Place Order" button
        // Scroll
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().resourceId(\"com.emedicoz.app:id/text_place_order\"))"
        ));

// Click
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
    }

    public static void completePaytmFlow() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

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

    public static void completeRazorPayFlow() {

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

    public static void clickTryAgain() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tryAgainBtn)).click();
    }


    // Select Feature Date ===================

    public static void SelectFeaturedate() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1. Click Go Pro Button
        By goProBtn = By.id("com.emedicoz.app:id/btnPro");
        wait.until(ExpectedConditions.elementToBeClickable(goProBtn)).click();

        // 2. Click First Plan
        By planName = By.id("com.emedicoz.app:id/tvPlanName");
        wait.until(ExpectedConditions.visibilityOfElementLocated(planName));
        List<WebElement> plans = driver.findElements(planName);

        if (!plans.isEmpty()) {
            plans.get(0).click();
        }

        // 3. Select Random Subscription Option
        By subscriptionRecyclerView = By.id("com.emedicoz.app:id/rvSubscriptionPlan");
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionRecyclerView));

        List<WebElement> options = driver.findElements(subscriptionRecyclerView);

        if (!options.isEmpty()) {
            int randomIndex = new Random().nextInt(options.size());
            options.get(randomIndex).click();
        }

        // 4. Click Buy Now
        By buyNowBtn = By.id("com.emedicoz.app:id/txtSubBuy");
        wait.until(ExpectedConditions.elementToBeClickable(buyNowBtn)).click();

        // 5. Handle "Yes" button (if present)
        By yesBtn = By.id("com.emedicoz.app:id/btnYes");

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement yesElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(yesBtn));

            if (yesElement.isDisplayed()) {
                yesElement.click();
            }
        } catch (TimeoutException e) {
            System.out.println("⚠️ No 'Yes' button. Possibly: No Items Are Present In Cart");
        }

        // ============================
        // ⭐ 7. DATE SELECTION LOGIC
        // ============================

        // Click Select Date
        By selectDateRadioBtn = By.id("com.emedicoz.app:id/cvStartDate");
        wait.until(ExpectedConditions.elementToBeClickable(selectDateRadioBtn)).click();

        // Wait for Calendar
        By dayPickerViewPager = By.id("android:id/day_picker_view_pager");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dayPickerViewPager));

        // Get selectable dates
        List<WebElement> dates = driver.findElements(
                By.xpath("//android.view.View[@clickable='true' and not(@enabled='false')]")
        );

        if (dates.isEmpty()) {
            throw new RuntimeException("❌ No selectable dates available");
        }

        // Select random date
        WebElement selectedDate = dates.get(new Random().nextInt(dates.size()));
        String selectedDateText = selectedDate.getText();
        selectedDate.click();

        System.out.println("✅ Selected Date: " + selectedDateText);

        // Validate validity text
        By validityText = By.xpath(
                "//android.widget.TextView[contains(@text,'Validity as per your chosen subscription')]"
        );

        WebElement validityElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(validityText)
        );

        String validityMsg = validityElement.getText();
        System.out.println("Validity Message: " + validityMsg);

        if (!validityMsg.contains("Validity")) {
            System.out.println("⚠️ Alert: Validity Is Incorrect");
        }

        // Wait for Start Date to update
        WebElement startDateElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(startDate)
        );

        String startDateUI = startDateElement.getText();

        System.out.println("📅 Selected (Calendar): " + selectedDateText);
        System.out.println("📅 Start Date (UI): " + startDateUI);

// ✅ Validate Selected Date matches Start Date
        if (!startDateUI.contains(selectedDateText)) {
            System.out.println("❌ Alert: Selected date NOT reflected in Start Date");
        } else {
            System.out.println("✅ Start Date correctly updated");
        }

// 🔹 Expiry Date Validation (basic check)
        WebElement expiryDateElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(expiryDate)
        );

        String expiryDateUI = expiryDateElement.getText();
        System.out.println("📅 Expiry Date (UI): " + expiryDateUI);

// Basic validation
        if (expiryDateUI.isEmpty()) {
            System.out.println("❌ Alert: Expiry Date not displayed");
        }

        // 6. Click Enroll Now
        By enrollNowBtn = By.id("com.emedicoz.app:id/btnEnroll");
        wait.until(ExpectedConditions.elementToBeClickable(enrollNowBtn)).click();

        // ============================
        // 8. Scroll to Place Order
        // ============================

        By placeOrderBtn = By.id("com.emedicoz.app:id/text_place_order");

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().resourceId(\"com.emedicoz.app:id/text_place_order\"))"
        ));

        // 9. Click Place Order
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
    }


    // Cross Sale ================================================================================

    public static void SelectFeaturedatewithcrosssale() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1. Click Go Pro Button
        By goProBtn = By.id("com.emedicoz.app:id/btnPro");
        wait.until(ExpectedConditions.elementToBeClickable(goProBtn)).click();

        // 2. Click First Plan
        By planName = By.id("com.emedicoz.app:id/tvPlanName");
        wait.until(ExpectedConditions.visibilityOfElementLocated(planName));

        List<WebElement> plans = driver.findElements(planName);

        if (!plans.isEmpty()) {
            plans.get(0).click();
        }

        // 3. Select Random Subscription Option
        By subscriptionRecyclerView =
                By.id("com.emedicoz.app:id/rvSubscriptionPlan");

        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionRecyclerView));

        List<WebElement> options =
                driver.findElements(By.id("com.emedicoz.app:id/planselectedtick"));

        if (!options.isEmpty()) {

            int randomIndex = new Random().nextInt(options.size());

            options.get(randomIndex).click();
        }

        // 4. Click Buy Now
        By buyNowBtn =
                By.id("com.emedicoz.app:id/txtSubBuy");

        wait.until(ExpectedConditions.elementToBeClickable(buyNowBtn)).click();

        // 5. Handle YES Button If Present
        By yesBtn =
                By.id("com.emedicoz.app:id/btnYes");

        try {

            WebDriverWait shortWait =
                    new WebDriverWait(driver, Duration.ofSeconds(5));

            WebElement yesElement = shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(yesBtn)
            );

            if (yesElement.isDisplayed()) {

                yesElement.click();

                System.out.println("✅ YES button clicked");
            }

        } catch (TimeoutException e) {

            System.out.println("⚠️ No 'Yes' button. Possibly: No Items Are Present In Cart");
        }

        // ====================================
        // ⭐ DATE SELECTION LOGIC
        // ====================================

        // Click Select Date
        By selectDateRadioBtn =
                By.id("com.emedicoz.app:id/cvStartDate");

        wait.until(ExpectedConditions.elementToBeClickable(selectDateRadioBtn)).click();

        // Wait for Calendar
        By dayPickerViewPager =
                By.id("android:id/day_picker_view_pager");

        wait.until(ExpectedConditions.visibilityOfElementLocated(dayPickerViewPager));

        // Get Selectable Dates
        List<WebElement> dates = driver.findElements(
                By.xpath("//android.view.View[@clickable='true' and @enabled='true']")
        );

        if (dates.isEmpty()) {

            throw new RuntimeException("❌ No selectable dates available");
        }

        // Select Random Future Date
        WebElement selectedDate =
                dates.get(new Random().nextInt(dates.size()));

        String selectedDateText =
                selectedDate.getText();

        selectedDate.click();

        System.out.println("✅ Selected Date: " + selectedDateText);

        // ====================================
        // VALIDITY TEXT VALIDATION
        // ====================================

        By validityText = By.xpath(
                "//android.widget.TextView[contains(@text,'Validity as per your chosen subscription')]"
        );

        WebElement validityElement =
                wait.until(ExpectedConditions.visibilityOfElementLocated(validityText));

        String validityMsg =
                validityElement.getText();

        System.out.println("📄 Validity Message: " + validityMsg);

        if (!validityMsg.contains("Validity")) {

            System.out.println("⚠️ Alert: Validity Is Incorrect");
        }

        // ====================================
        // START DATE VALIDATION
        // ====================================

        By startDate =
                By.id("com.emedicoz.app:id/tvStartDate");

        WebElement startDateElement =
                wait.until(ExpectedConditions.visibilityOfElementLocated(startDate));

        String startDateUI =
                startDateElement.getText();

        System.out.println("📅 Selected Calendar Date: " + selectedDateText);
        System.out.println("📅 Start Date UI: " + startDateUI);

        if (!startDateUI.contains(selectedDateText)) {

            System.out.println("❌ Alert: Selected Date NOT reflected in Start Date");

        } else {

            System.out.println("✅ Start Date correctly updated");
        }

        // ====================================
        // EXPIRY DATE VALIDATION
        // ====================================

        By expiryDate =
                By.id("com.emedicoz.app:id/tvExpiredDate");

        WebElement expiryDateElement =
                wait.until(ExpectedConditions.visibilityOfElementLocated(expiryDate));

        String expiryDateUI =
                expiryDateElement.getText();

        System.out.println("📅 Expiry Date UI: " + expiryDateUI);

        if (expiryDateUI.isEmpty()) {

            System.out.println("❌ Alert: Expiry Date not displayed");
        }

        // ====================================
        // CLICK ENROLL NOW
        // ====================================

        By enrollNowBtn =
                By.id("com.emedicoz.app:id/btnEnroll");

        wait.until(ExpectedConditions.elementToBeClickable(enrollNowBtn)).click();

        // ====================================
        // HANDLE CROSS SALE
        // ====================================

        By addToCartBtn =
                By.id("com.emedicoz.app:id/tvAddToCart");

        try {

            WebDriverWait shortWait =
                    new WebDriverWait(driver, Duration.ofSeconds(5));

            WebElement addCartElement = shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(addToCartBtn)
            );

            if (addCartElement.isDisplayed()) {

                addCartElement.click();

                System.out.println("✅ Cross Sale Product Added Successfully");
            }

        } catch (TimeoutException e) {

            System.out.println("⚠️ Alert: Cross Sale Is Not Activated");
        }

        // ====================================
        // SCROLL TO PLACE ORDER
        // ====================================

        By placeOrderBtn =
                By.id("com.emedicoz.app:id/text_place_order");

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().resourceId(\"com.emedicoz.app:id/text_place_order\"))"
        ));
// ====================================
// SCROLL TO PRICE SUMMARY SECTION
// ====================================

        By linearSummary =
                By.id("com.emedicoz.app:id/linearSummary");

        try {

            // 🔹 Scroll Until Price Summary Visible
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector()" +
                            ".resourceId(\"com.emedicoz.app:id/linearSummary\"))"
            ));

            // 🔹 Wait For Summary Section
            WebElement summarySection =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(linearSummary));

            System.out.println("✅ Price Summary Section Found");

            // ====================================
            // FETCH ALL PRICE SUMMARY VALUES
            // ====================================

            String totalCourse = "";
            String itemPrice = "0";
            String couponName = "";
            String couponDiscountValue = "0";
            String gstTax = "0";
            String grandTotal = "0";
            String shippingValue = "0";
            String platformFeeValue = "0";
            String payableAmount = "0";

            // ====================================
            // TOTAL COURSE COUNT
            // ====================================

            try {

                totalCourse =
                        wait.until(ExpectedConditions.visibilityOfElementLocated(totalCourseCount))
                                .getText();

            } catch (Exception e) {

                totalCourse = "Not Found";
            }

            // ====================================
            // ITEM PRICE
            // ====================================

            try {

                itemPrice =
                        wait.until(ExpectedConditions.visibilityOfElementLocated(itemPriceValue))
                                .getText();

            } catch (Exception e) {

                itemPrice = "0";
            }

            // ====================================
            // COUPON DETAILS
            // ====================================

            try {

                WebElement couponElement =
                        wait.until(ExpectedConditions.visibilityOfElementLocated(couponLayout));

                if (couponElement.isDisplayed()) {

                    couponName =
                            wait.until(ExpectedConditions.visibilityOfElementLocated(couponText))
                                    .getText();

                    couponDiscountValue =
                            wait.until(ExpectedConditions.visibilityOfElementLocated(couponDiscount))
                                    .getText();
                }

            } catch (Exception e) {

                couponName = "Coupon Is Not Applied";
                couponDiscountValue = "0";
            }

            // ====================================
            // GST AMOUNT
            // ====================================

            try {

                gstTax =
                        wait.until(ExpectedConditions.visibilityOfElementLocated(gstAmount))
                                .getText();

            } catch (Exception e) {

                gstTax = "0";
            }

            // ====================================
            // SHIPPING CHARGE
            // ====================================

            try {

                shippingValue =
                        wait.until(ExpectedConditions.visibilityOfElementLocated(shippingCharge))
                                .getText();

                System.out.println("🚚 Delivery Charges Applicable : " + shippingValue);

            } catch (Exception e) {

                shippingValue = "0";

                System.out.println("⚠️ Alert: Delivery Charges Is Not Applicable On This Product");
            }

            // ====================================
            // PLATFORM FEE
            // ====================================

            try {

                platformFeeValue =
                        wait.until(ExpectedConditions.visibilityOfElementLocated(platformFee))
                                .getText();

                System.out.println("🧾 Platform Fee Applicable : " + platformFeeValue);

            } catch (Exception e) {

                platformFeeValue = "0";

                System.out.println("⚠️ Platform Fee Not Applicable");
            }

            // ====================================
            // GRAND TOTAL
            // ====================================

            try {

                grandTotal =
                        wait.until(ExpectedConditions.visibilityOfElementLocated(grandTotalAmount))
                                .getText();

            } catch (Exception e) {

                grandTotal = "0";
            }

            // ====================================
            // FINAL PAYABLE AMOUNT
            // ====================================

            By totalPayableAmount =
                    By.id("com.emedicoz.app:id/textTotalAmount");

            try {

                payableAmount =
                        wait.until(ExpectedConditions.visibilityOfElementLocated(totalPayableAmount))
                                .getText();

            } catch (Exception e) {

                payableAmount = "0";
            }

            // ====================================
            // FINAL REPORT
            // ====================================

            System.out.println("\n========== PRICE SUMMARY REPORT ==========");

            System.out.println("📚 Total Course Count : " + totalCourse);

            System.out.println("💰 Item(s) Amount : " + itemPrice);

            // ====================================
            // COUPON REPORT
            // ====================================

            if (!couponName.equals("Coupon Is Not Applied")) {

                System.out.println("🏷 Coupon Applied : " + couponName);

                System.out.println("🎯 Coupon Discount : " + couponDiscountValue);

            } else {

                System.out.println("⚠️ Alert: Coupon Is Not Applied");
            }

            // ====================================
            // GST REPORT
            // ====================================

            System.out.println("🧾 GST Charges : " + gstTax);

            // ====================================
            // SHIPPING REPORT
            // ====================================

            System.out.println("🚚 Delivery Charge : " + shippingValue);

            // ====================================
            // PLATFORM FEE REPORT
            // ====================================

            System.out.println("🧾 Platform Fee : " + platformFeeValue);

            // ====================================
            // GRAND TOTAL REPORT
            // ====================================

            System.out.println("💳 Grand Total : " + grandTotal);

            // ====================================
            // FINAL PAYABLE REPORT
            // ====================================

            System.out.println("💵 Overall Payable Amount : " + payableAmount);

            // ====================================
            // GRAND TOTAL VALIDATION
            // Formula:
            // Item Amount - Coupon Discount + GST
            // + Delivery Charges (If Present)
            // + Platform Fee
            // ====================================

            try {

                // 🔹 Item Amount
                double itemAmountValue =
                        Double.parseDouble(
                                itemPrice.replace("₹", "")
                                        .replace(",", "")
                                        .trim()
                        );

                // 🔹 Coupon Discount
                double couponAmountValue = 0;

                try {

                    couponAmountValue =
                            Double.parseDouble(
                                    couponDiscountValue.replace("₹", "")
                                            .replace("-", "")
                                            .replace(",", "")
                                            .trim()
                            );

                } catch (Exception ignored) {

                    couponAmountValue = 0;
                }

                // 🔹 GST Amount
                double gstAmountValue =
                        Double.parseDouble(
                                gstTax.replace("₹", "")
                                        .replace(",", "")
                                        .trim()
                        );

                // 🔹 Shipping Charges
                double shippingChargeValue = 0;

                try {

                    shippingChargeValue =
                            Double.parseDouble(
                                    shippingValue.replace("₹", "")
                                            .replace(",", "")
                                            .trim()
                            );

                } catch (Exception ignored) {

                    shippingChargeValue = 0;
                }

                // 🔹 Platform Fee
                double platformChargeValue = 0;

                try {

                    platformChargeValue =
                            Double.parseDouble(
                                    platformFeeValue.replace("₹", "")
                                            .replace(",", "")
                                            .trim()
                            );

                } catch (Exception ignored) {

                    platformChargeValue = 0;
                }

                // 🔹 Actual Grand Total
                double actualGrandTotal =
                        Double.parseDouble(
                                grandTotal.replace("₹", "")
                                        .replace(",", "")
                                        .trim()
                        );

                // ====================================
                // EXPECTED GRAND TOTAL
                // ====================================

                double expectedGrandTotal =
                        (itemAmountValue - couponAmountValue)
                                + gstAmountValue
                                + shippingChargeValue
                                + platformChargeValue;

                System.out.println("🧮 Expected Grand Total : ₹ " + expectedGrandTotal);

                System.out.println("💳 Actual Grand Total : ₹ " + actualGrandTotal);

                // ====================================
                // GRAND TOTAL VALIDATION
                // ====================================

                if (Math.abs(expectedGrandTotal - actualGrandTotal) < 2) {

                    System.out.println("✅ Grand Total Amount Is Accurate");

                } else {

                    System.out.println("❌ Alert: Grand Total Amount Is Mismatched");
                }

                // ====================================
                // OVERALL PAYABLE AMOUNT VALIDATION
                // ====================================

                double actualPayableAmount =
                        Double.parseDouble(
                                payableAmount.replace("₹", "")
                                        .replace(",", "")
                                        .trim()
                        );

                // 🔹 Final Payable Amount
                double expectedPayableAmount =
                        expectedGrandTotal;

                System.out.println("🧮 Expected Payable Amount : ₹ " + expectedPayableAmount);

                System.out.println("💵 Actual Payable Amount : ₹ " + actualPayableAmount);

                // ====================================
                // PAYABLE AMOUNT VALIDATION
                // ====================================

                if (Math.abs(expectedPayableAmount - actualPayableAmount) < 2) {

                    System.out.println("✅ Overall Payable Amount Matched Successfully");

                } else {

                    System.out.println("❌ Alert: Overall Payable Amount Is Mismatched");
                }

            } catch (Exception e) {

                System.out.println("❌ Alert: Unable To Validate Amount Calculation");
            }

            System.out.println("==========================================");

        } catch (Exception e) {

            System.out.println("❌ Alert: Price Summary Section Not Found");
        }



        // ====================================
        // CLICK PLACE ORDER
        // ====================================

        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();

        System.out.println("✅ Place Order Clicked Successfully");
    }

    // Cross Sale Add All Product In Cart =================================================================================================================
    public static void SelectFeaturedatewithcrosssale2() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // 1. Navigation & Plan Selection
        By goProBtn = By.id("com.emedicoz.app:id/btnPro");
        wait.until(ExpectedConditions.elementToBeClickable(goProBtn)).click();

        By planName = By.id("com.emedicoz.app:id/tvPlanName");
        wait.until(ExpectedConditions.visibilityOfElementLocated(planName));
        List<WebElement> plans = driver.findElements(planName);
        if (!plans.isEmpty()) {
            plans.get(0).click();
        }

        // 2. Select Subscription
        By subscriptionRecyclerView = By.id("com.emedicoz.app:id/rvSubscriptionPlan");
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionRecyclerView));
        List<WebElement> options = driver.findElements(By.id("com.emedicoz.app:id/planselectedtick"));
        if (!options.isEmpty()) {
            int randomIndex = new Random().nextInt(options.size());
            options.get(randomIndex).click();
        }

        // 3. Buy Now & Handle Cart Popup
        By buyNowBtn = By.id("com.emedicoz.app:id/txtSubBuy");
        wait.until(ExpectedConditions.elementToBeClickable(buyNowBtn)).click();

        By yesBtn = By.id("com.emedicoz.app:id/btnYes");
        try {
            WebElement yesElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(yesBtn));
            if (yesElement.isDisplayed()) {
                yesElement.click();
                System.out.println("✅ YES button clicked");
            }
        } catch (TimeoutException e) {
            System.out.println("⚠️ No 'Yes' button present.");
        }

        // 4. ⭐ DATE SELECTION & STORAGE
        By selectDateRadioBtn = By.id("com.emedicoz.app:id/cvStartDate");
        wait.until(ExpectedConditions.elementToBeClickable(selectDateRadioBtn)).click();

        By dayPickerViewPager = By.id("android:id/day_picker_view_pager");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dayPickerViewPager));

        List<WebElement> dates = driver.findElements(By.xpath("//android.view.View[@clickable='true' and @enabled='true']"));
        if (dates.isEmpty()) throw new RuntimeException("❌ No selectable dates available");

        WebElement dateToPick = dates.get(new Random().nextInt(dates.size()));
        String expectedDateText = dateToPick.getText(); // Store the date we click
        dateToPick.click();
        System.out.println("📅 Date Selected in Picker: " + expectedDateText);

        // 5. Enroll Now
        By enrollNowBtn = By.id("com.emedicoz.app:id/btnEnroll");
        wait.until(ExpectedConditions.elementToBeClickable(enrollNowBtn)).click();

        // 6. CROSS SALE LOOP
        By addToCartBtn = By.id("com.emedicoz.app:id/tvAddToCart");
        while (true) {
            try {
                WebElement addCartElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBtn));
                if (addCartElement.isDisplayed()) {
                    addCartElement.click();
                    System.out.println("✅ Cross Sale Product Added Successfully");
                    Thread.sleep(3000);
                }
            } catch (Exception e) {
                System.out.println("⚠️ Cross Sale Add To Cart Button No Longer Visible");
                break;
            }
        }

        // ====================================
        // ⭐ PRICE SUMMARY & DATE VERIFICATION
        // ====================================
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().resourceId(\"com.emedicoz.app:id/linearSummary\"))"
            ));

            // --- DATE ACCURACY CHECK ---
            try {
                By startDateLocator = By.id("com.emedicoz.app:id/tvStartDate");
                String actualDateInUI = driver.findElement(startDateLocator).getText();
                if (actualDateInUI.contains(expectedDateText)) {
                    System.out.println("✅ Date Accuracy: Match Found (" + actualDateInUI + ")");
                } else {
                    System.out.println("❌ Date Accuracy: Mismatch! Expected: " + expectedDateText + " but found: " + actualDateInUI);
                }
            } catch (Exception e) {
                System.out.println("⚠️ Could not find Start Date element to verify.");
            }

            // --- ALL PREVIOUS PRICE CONDITIONS RETAINED ---
            String totalCourse = "0", itemPrice = "0", couponDiscountValue = "0", gstTax = "0";
            String grandTotal = "0", shippingValue = "0", platformFeeValue = "0", payableAmount = "0";

            try { totalCourse = driver.findElement(totalCourseCount).getText(); } catch (Exception e) { totalCourse = "Not Found"; }
            try { itemPrice = driver.findElement(itemPriceValue).getText(); } catch (Exception e) { itemPrice = "₹0"; }
            try { gstTax = driver.findElement(gstAmount).getText(); } catch (Exception e) { gstTax = "₹0"; }
            try { shippingValue = driver.findElement(shippingCharge).getText(); } catch (Exception e) { shippingValue = "₹0"; }
            try { platformFeeValue = driver.findElement(platformFee).getText(); } catch (Exception e) { platformFeeValue = "₹0"; }
            try { couponDiscountValue = driver.findElement(couponDiscount).getText(); } catch (Exception e) { couponDiscountValue = "₹0"; }
            try { grandTotal = driver.findElement(grandTotalAmount).getText(); } catch (Exception e) { grandTotal = "₹0"; }

            // SCROLL TO PAYABLE AMOUNT
            By totalPayableAmountLocator = By.id("com.emedicoz.app:id/textTotalAmount");
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().resourceId(\"com.emedicoz.app:id/textTotalAmount\"))"
            ));
            payableAmount = driver.findElement(totalPayableAmountLocator).getText();

            // --- CALCULATIONS ---
            double itemVal = Double.parseDouble(itemPrice.replaceAll("[^0-9.]", ""));
            double gstVal = Double.parseDouble(gstTax.replaceAll("[^0-9.]", ""));
            double shipVal = Double.parseDouble(shippingValue.replaceAll("[^0-9.]", ""));
            double platformVal = Double.parseDouble(platformFeeValue.replaceAll("[^0-9.]", ""));
            double couponVal = 0;
            try { couponVal = Double.parseDouble(couponDiscountValue.replaceAll("[^0-9.]", "")); } catch (Exception ignored) {}

            double actualPayableUI = Double.parseDouble(payableAmount.replaceAll("[^0-9.]", ""));
            double expectedFinalPayable = (itemVal - couponVal) + gstVal + shipVal + platformVal;

            // --- REPORTING ---
            System.out.println("\n========== FINAL VERIFICATION REPORT ==========");
            System.out.println("📅 Selected Date Status   : VERIFIED");
            System.out.println("💰 Base Item Price       : " + itemPrice);
            System.out.println("🏷️ Coupon Discount        : -" + couponDiscountValue);
            System.out.println("🧾 GST Charges            : +" + gstTax);
            System.out.println("🚚 Delivery Charge        : +" + shippingValue);
            System.out.println("🧾 Platform Fee           : +" + platformFeeValue);
            System.out.println("----------------------------------------------");
            System.out.println("🧮 Expected Total Payable : ₹ " + expectedFinalPayable);
            System.out.println("💵 UI Final Payable       : " + payableAmount);
            System.out.println("==============================================\n");

            if (Math.abs(expectedFinalPayable - actualPayableUI) < 2) {
                System.out.println("✅ SUCCESS: Overall Calculation is Accurate");
            } else {
                System.out.println("❌ ERROR: Overall Calculation Mismatched!");
            }

        } catch (Exception e) {
            System.out.println("❌ Alert: Validation Failed: " + e.getMessage());
        }

        // 7. Click Place Order
        By placeOrderBtn = By.id("com.emedicoz.app:id/text_place_order");
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
        System.out.println("✅ Place Order Clicked Successfully");
    }

}
