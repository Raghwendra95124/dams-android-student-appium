package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.collect.ImmutableMap;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class TestSeries {
    private static AndroidDriver driver;

    public static void setDriver(AndroidDriver sharedDriver) {
        driver = sharedDriver;
    }

    // ====================================
    // HOME PAGE - MY STUDY TAB
    // ====================================
    public static final By myStudyTab = By.id("com.emedicoz.app:id/nav_study");
    public static final By sectionSpinnerText = By.id("com.emedicoz.app:id/textSpinner");

    // ====================================
    // COURSE DROPDOWN
    // ====================================
    public static final By courseDropdown = By.id("com.emedicoz.app:id/spCourse");

    // SUBMIT BUTTON
    public static final By submitTestBtn = By.id("com.emedicoz.app:id/btnSubmitTest");

    // QUESTION NUMBER
    public static final By tvQuestionNumber = By.id("com.emedicoz.app:id/tvQuestionNumber");

    // QUESTION TITLE
    public static final By tvQuestion = By.id("com.emedicoz.app:id/tvQuestion");

    // ====================================
    // COURSE NAME
    // ====================================
    public static final By faceToFaceTestingCourse =
            By.xpath("//android.widget.TextView[@text='Face to Face testing']");

    // ====================================
    // COURSE CARD VIEW
    // ====================================
    public static final By textSpinner = By.id("com.emedicoz.app:id/textSpinner");
    public static final By courseCardView = By.id("com.emedicoz.app:id/mcvCardView");
    public static final By startTestButton = By.id("com.emedicoz.app:id/btnStartTest");
    public static final By optionIconTV = By.id("com.emedicoz.app:id/optionIconTV");
    public static final By btnNext = By.id("com.emedicoz.app:id/btn_next");

    // ====================================
    // QUESTION PALETTE LOCATORS
    // ====================================
    public static final By alignLeftLayout = By.id("com.emedicoz.app:id/alignLeftLayout");
    public static final By rlQuestionPad = By.id("com.emedicoz.app:id/rlQuestionPad");
    public static final By planName = By.id("com.emedicoz.app:id/planName");
    public static final By tvCatName = By.id("com.emedicoz.app:id/tvCatName");

    // ====================================
    // COURSE IMAGE
    // ====================================
    public static final By courseImage = By.id("com.emedicoz.app:id/imageView17");

    // ====================================
    // TEST SERIES SECTION
    // ====================================
    public static final By imgTestBook = By.id("com.emedicoz.app:id/imgTestBook");

    // ====================================
    // TEST SERIES TITLE
    // ====================================
    public static final By testSeriesTitle =
            By.xpath("//android.widget.TextView[@text='Test series (Section wise test) Only used for Testing']");

    // ====================================
    // START BUTTON LAYOUT
    // ====================================
    public static final By relativeStart = By.id("com.emedicoz.app:id/relativeStart");

    // ====================================
    // QBANK HEADER TITLE
    // ====================================
    public static final By qbTxtHeader = By.id("com.emedicoz.app:id/qbTxtHeader");

    // ====================================
    // NAVIGATION BUTTONS
    // ====================================
    public static final By btnPrevious = By.id("com.emedicoz.app:id/btn_previous");
    public static final By btnPrev = By.id("com.emedicoz.app:id/btn_prev");
    public static final By previousText = By.xpath("//android.widget.TextView[@text='Previous']");
    public static final By tvTimer = By.id("com.emedicoz.app:id/tv_timer");

    // ====================================
    // TIMER PATTERN
    // ====================================
    public static final Pattern TIMER_PATTERN = Pattern.compile("\\d{1,2}:\\d{2}(:\\d{2})?");

    // ====================================
    // UTILITY METHODS
    // ====================================

    public static void openTestSeriesCourse() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement studyTab = wait.until(ExpectedConditions.elementToBeClickable(myStudyTab));
            studyTab.click();
            System.out.println("✅ My Study Tab Clicked");

            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(courseDropdown));
            dropdown.click();
            System.out.println("✅ Course Dropdown Clicked");

            WebElement course = wait.until(ExpectedConditions.elementToBeClickable(faceToFaceTestingCourse));
            course.click();
            System.out.println("✅ Face To Face Testing Course Selected");

            Thread.sleep(3000);
        } catch (Exception e) {
            throw new RuntimeException("Failed To Open Test Series Course", e);
        }
    }

    public static void scrollAndClickPlanName(String expectedPlanName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            boolean isFound = false;
            String expectedNormalized = normalizeText(expectedPlanName);

            for (int i = 0; i < 10; i++) {
                boolean planSeenThisPass = false;
                try {
                    List<WebElement> planCandidates = driver.findElements(By.id("com.emedicoz.app:id/planName"));
                    for (WebElement plan : planCandidates) {
                        String planText = normalizeText(plan.getText());
                        if (plan.isDisplayed() && !planText.isEmpty() && planText.contains(expectedNormalized)) {
                            planSeenThisPass = true;
                            System.out.println("Plan Found : " + plan.getText());
                            if (clickPlanWithRetries(wait, plan, expectedPlanName)) {
                                System.out.println("Plan Clicked : " + plan.getText());
                                isFound = true;
                                break;
                            }
                            System.out.println("Plan visible but click failed on this pass.");
                        }
                    }
                } catch (Exception ignored) {}

                if (isFound) break;
                if (planSeenThisPass) {
                    // Plan is visible; avoid scrolling away repeatedly. Try again on same screen.
                    Thread.sleep(700);
                    continue;
                }

                driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                        "left", 100, "top", 100, "width", 800, "height", 1600,
                        "direction", "down", "percent", 0.7
                ));
                System.out.println("Scrolling Down...");
            }

            if (!isFound) {
                throw new RuntimeException("Plan Not Found : " + expectedPlanName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed To Scroll And Click Plan Name", e);
        }
    }

    private static boolean clickWithFallback(WebDriverWait wait, WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            return true;
        } catch (Exception firstClickError) {
            try {
                int centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
                int centerY = element.getLocation().getY() + (element.getSize().getHeight() / 2);
                driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                        "x", centerX,
                        "y", centerY
                ));
                return true;
            } catch (Exception ignored) {
                return false;
            }
        }
    }

    private static boolean clickPlanWithRetries(WebDriverWait wait, WebElement planElement, String expectedPlanName) {
        // Try direct click/tap first on the label element.
        if (clickWithFallback(wait, planElement)) {
            return true;
        }

        // Fallback: click likely parent/container around the plan text.
        String safePlanText = expectedPlanName.replace("'", "\\'");
        By[] fallbackLocators = new By[] {
                By.xpath("//android.widget.TextView[@resource-id='com.emedicoz.app:id/planName' and contains(@text,'" + safePlanText + "')]"),
                By.xpath("//android.widget.TextView[contains(@text,'" + safePlanText + "')]/ancestor::android.view.ViewGroup[1]"),
                By.xpath("//android.widget.TextView[contains(@text,'" + safePlanText + "')]/ancestor::android.widget.LinearLayout[1]")
        };

        for (By locator : fallbackLocators) {
            try {
                List<WebElement> matches = driver.findElements(locator);
                if (!matches.isEmpty() && clickWithFallback(wait, matches.get(0))) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }

    public static void slowScrollAndClickTestSeries(String expectedCourseName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            boolean isFound = false;
            String expectedNormalized = normalizeText(expectedCourseName);

            for (int i = 0; i < 20; i++) {
                try {
                    List<WebElement> allTextViews = driver.findElements(By.className("android.widget.TextView"));
                    for (WebElement course : allTextViews) {
                        if (!course.isDisplayed()) continue;

                        String courseName = course.getText();
                        String normalizedCourse = normalizeText(courseName);
                        if (!normalizedCourse.isEmpty() && normalizedCourse.contains(expectedNormalized)) {
                            System.out.println("=================================");
                            System.out.println("COURSE FOUND : " + courseName);
                            System.out.println("=================================");

                            if (clickWithFallback(wait, course)) {
                                System.out.println("Course Clicked : " + courseName);
                                isFound = true;
                                break;
                            }
                            System.out.println("Course visible but click failed, retry after scroll.");
                        }
                    }
                } catch (Exception ignored) {}

                if (isFound) break;

                driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                        "left", 100, "top", 300, "width", 800, "height", 1200,
                        "direction", "down", "percent", 0.4
                ));
                System.out.println("Slow Scrolling Line By Line...");
                Thread.sleep(1500);
            }

            if (!isFound) {
                throw new RuntimeException("Could not find this test series course: " + expectedCourseName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed To Find And Click Course", e);
        }
    }

    public static void clickCategory(String categoryName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            By dynamicCategory = By.xpath(
                    "//android.widget.TextView[@resource-id='com.emedicoz.app:id/tvCatName' and @text='" + categoryName + "']"
            );
            WebElement category = wait.until(ExpectedConditions.elementToBeClickable(dynamicCategory));
            category.click();
            System.out.println("✅ Category Clicked : " + categoryName);
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException("Failed To Click Category : " + categoryName, e);
        }
    }

    public static void startTestAndSelectRandomAnswers() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Random random = new Random();
        int questionCount = 0;
        String lastSectionName = "";

        try {
            // Scroll down
            driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 300, "width", 800, "height", 1200,
                    "direction", "down", "percent", 1.0
            ));
            System.out.println("✅ Scrolled Down");

            // Click Start Test
            WebElement startBtn = wait.until(ExpectedConditions.elementToBeClickable(startTestButton));
            startBtn.click();
            System.out.println("✅ START TEST Clicked");
            Thread.sleep(2000);

            // Question loop
            while (true) {
                try {
                    // Check submit button
                    List<WebElement> submitBtns = driver.findElements(submitTestBtn);
                    if (!submitBtns.isEmpty() && submitBtns.get(0).isDisplayed()) {
                        System.out.println("=================================");
                        System.out.println("✅ SUBMIT BUTTON FOUND");
                        System.out.println("TOTAL QUESTIONS ATTEMPTED : " + questionCount);
                        System.out.println("=================================");
                        break;
                    }

                    // Get section name
                    String currentSection = "N/A";
                    try {
                        WebElement section = driver.findElement(textSpinner);
                        currentSection = section.getText().trim();
                    } catch (Exception ignored) {}

                    // Show report if section changed
                    if (!currentSection.equals(lastSectionName)) {
                        System.out.println("=================================");
                        System.out.println("SECTION CHANGED : " + currentSection);
                        System.out.println("=================================");
                        lastSectionName = currentSection;
                    }

                    // Get question number
                    String questionNumber = "N/A";
                    try {
                        WebElement questionNo = driver.findElement(tvQuestionNumber);
                        questionNumber = questionNo.getText().trim();
                    } catch (Exception ignored) {}

                    // Get question title
                    String currentQuestion = "N/A";
                    try {
                        WebElement question = driver.findElement(tvQuestion);
                        currentQuestion = question.getText().trim();
                    } catch (Exception ignored) {}

                    // Get all options
                    List<WebElement> options = wait.until(
                            ExpectedConditions.presenceOfAllElementsLocatedBy(optionIconTV));

                    if (options.isEmpty()) {
                        System.out.println("❌ No Options Found");
                        break;
                    }

                    // Select random option
                    int randomIndex = random.nextInt(options.size());
                    WebElement selectedOption = options.get(randomIndex);
                    String selectedAnswer = selectedOption.getText().trim();
                    selectedOption.click();
                    questionCount++;

                    // Report
                    System.out.println("=================================");
                    System.out.println("SECTION          : " + currentSection);
                    System.out.println("QUESTION NO UI   : " + questionNumber);
                    System.out.println("QUESTION TITLE   : " + currentQuestion);
                    System.out.println("SELECTED ANSWER  : " + selectedAnswer);
                    System.out.println("=================================");

                    // Every 3rd question - App switch validation
                    if (questionCount % 3 == 0) {
                        System.out.println("=================================");
                        System.out.println("APP SWITCH VALIDATION");
                        System.out.println("=================================");

                        // Press HOME
                        driver.pressKey(new KeyEvent(AndroidKey.HOME));
                        System.out.println("✅ HOME Button Pressed");
                        Thread.sleep(1000);

                        // Press APP_SWITCH
                        driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
                        System.out.println("✅ APP SWITCH Button Pressed");
                        Thread.sleep(1000);

                        // Reopen app
                        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                                "x", 550, "y", 1200
                        ));
                        System.out.println("✅ App Reopened");
                        Thread.sleep(2000);

                        // Verify same question
                        String reopenedQuestion = "N/A";
                        try {
                            WebElement reopenQuestion = driver.findElement(tvQuestion);
                            reopenedQuestion = reopenQuestion.getText().trim();
                        } catch (Exception ignored) {}

                        System.out.println("PREVIOUS QUESTION : " + currentQuestion);
                        System.out.println("REOPENED QUESTION : " + reopenedQuestion);

                        // Validation
                        if (!currentQuestion.equals(reopenedQuestion)) {
                            System.out.println("❌ ALERT : SAME QUESTION IS NOT OPEN AFTER APP SWITCH");
                            driver.pressKey(new KeyEvent(AndroidKey.HOME));
                            Thread.sleep(1000);
                            driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
                            Thread.sleep(1000);
                            throw new RuntimeException("Same Question Is Not Open After Switching The App");
                        }
                        System.out.println("✅ SAME QUESTION VERIFIED");
                    }

                    // Click Next button
                    WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(btnNext));
                    nextBtn.click();
                    System.out.println("✅ NEXT Button Clicked");
                    Thread.sleep(1200);

                } catch (Exception e) {
                    System.out.println("❌ No More Questions Found" + e.getMessage());
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed During Test Execution", e);
        }
    }

    public static void ClickQuestionList() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Random random = new Random();
        int questionCount = 0;
        String lastSectionName = "";

        try {
            // Scroll down
            driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 300, "width", 800, "height", 1200,
                    "direction", "down", "percent", 1.0
            ));
            System.out.println("✅ Scrolled Down");

            // Click Start Test
            WebElement startBtn = wait.until(ExpectedConditions.elementToBeClickable(startTestButton));
            startBtn.click();
            System.out.println("✅ START TEST Clicked");
            Thread.sleep(2000);

            // Question loop
            while (true) {
                try {
                    // Check submit button
                    List<WebElement> submitBtns = driver.findElements(submitTestBtn);
                    if (!submitBtns.isEmpty() && submitBtns.get(0).isDisplayed()) {
                        System.out.println("=================================");
                        System.out.println("✅ SUBMIT BUTTON FOUND");
                        System.out.println("TOTAL QUESTIONS ATTEMPTED : " + questionCount);
                        System.out.println("=================================");
                        break;
                    }

                    // Get section name
                    String currentSection = "N/A";
                    try {
                        WebElement section = driver.findElement(textSpinner);
                        currentSection = section.getText().trim();
                    } catch (Exception ignored) {}

                    // Show report if section changed
                    if (!currentSection.equals(lastSectionName)) {
                        System.out.println("=================================");
                        System.out.println("SECTION CHANGED : " + currentSection);
                        System.out.println("=================================");
                        lastSectionName = currentSection;
                    }

                    // Get question number
                    String questionNumber = "N/A";
                    try {
                        WebElement questionNo = driver.findElement(tvQuestionNumber);
                        questionNumber = questionNo.getText().trim();
                    } catch (Exception ignored) {}

                    // Get question title
                    String currentQuestion = "N/A";
                    try {
                        WebElement question = driver.findElement(tvQuestion);
                        currentQuestion = question.getText().trim();
                    } catch (Exception ignored) {}

                    // Get all options
                    List<WebElement> options = wait.until(
                            ExpectedConditions.presenceOfAllElementsLocatedBy(optionIconTV));

                    if (options.isEmpty()) {
                        System.out.println("❌ No Options Found");
                        break;
                    }

                    // Select random option
                    int randomIndex = random.nextInt(options.size());
                    WebElement selectedOption = options.get(randomIndex);
                    String selectedAnswer = selectedOption.getText().trim();
                    selectedOption.click();
                    questionCount++;

                    // Report
                    System.out.println("=================================");
                    System.out.println("SECTION          : " + currentSection);
                    System.out.println("QUESTION NO UI   : " + questionNumber);
                    System.out.println("QUESTION TITLE   : " + currentQuestion);
                    System.out.println("SELECTED ANSWER  : " + selectedAnswer);
                    System.out.println("QUESTION COUNT   : " + questionCount);
                    System.out.println("=================================");

                    // Every 3rd question - App switch validation
                    if (questionCount % 3 == 0) {
                        System.out.println("=================================");
                        System.out.println("APP SWITCH VALIDATION");
                        System.out.println("=================================");

                        // Press HOME
                        driver.pressKey(new KeyEvent(AndroidKey.HOME));
                        System.out.println("✅ HOME Button Pressed");
                        Thread.sleep(1000);

                        // Press APP_SWITCH
                        driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
                        System.out.println("✅ APP SWITCH Button Pressed");
                        Thread.sleep(1000);

                        // Reopen app
                        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                                "x", 550, "y", 1200
                        ));
                        System.out.println("✅ App Reopened");
                        Thread.sleep(2000);

                        // Verify same question
                        String reopenedQuestion = "N/A";
                        try {
                            WebElement reopenQuestion = driver.findElement(tvQuestion);
                            reopenedQuestion = reopenQuestion.getText().trim();
                        } catch (Exception ignored) {}

                        System.out.println("PREVIOUS QUESTION : " + currentQuestion);
                        System.out.println("REOPENED QUESTION : " + reopenedQuestion);

                        // Validation
                        if (!currentQuestion.equals(reopenedQuestion)) {
                            System.out.println("❌ ALERT : SAME QUESTION IS NOT OPEN AFTER APP SWITCH");
                            driver.pressKey(new KeyEvent(AndroidKey.HOME));
                            Thread.sleep(1000);
                            driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
                            Thread.sleep(1000);
                            throw new RuntimeException("Same Question Is Not Open After Switching The App");
                        }
                        System.out.println("✅ SAME QUESTION VERIFIED");
                    }

                    // Every 5th question - Question palette validation
                    if (questionCount % 5 == 0) {
                        System.out.println("=================================");
                        System.out.println("QUESTION PALETTE VALIDATION");
                        System.out.println("=================================");

                        // Click Question Palette
                        WebElement paletteBtn = wait.until(
                                ExpectedConditions.elementToBeClickable(alignLeftLayout));
                        paletteBtn.click();
                        System.out.println("✅ Question Palette Opened");
                        Thread.sleep(1000);

                        // Get question grid
                        WebElement grid = wait.until(
                                ExpectedConditions.visibilityOfElementLocated(rlQuestionPad));
                        List<WebElement> questionPads = grid.findElements(
                                By.className("android.widget.LinearLayout"));

                        if (!questionPads.isEmpty()) {
                            // Select random question
                            int randomQuestionIndex = random.nextInt(questionPads.size());
                            WebElement randomQuestion = questionPads.get(randomQuestionIndex);
                            String selectedQuestionNo = "N/A";
                            try {
                                selectedQuestionNo = randomQuestion.getText().trim();
                            } catch (Exception ignored) {}
                            randomQuestion.click();
                            System.out.println("✅ Random Question Selected");
                            System.out.println("SELECTED QUESTION PAD : " + selectedQuestionNo);
                            Thread.sleep(1500);

                            // Again open Question Palette
                            WebElement paletteBtnAgain = wait.until(
                                    ExpectedConditions.elementToBeClickable(alignLeftLayout));
                            paletteBtnAgain.click();
                            System.out.println("✅ Question Palette Opened Again");
                            Thread.sleep(1000);

                            // Again get grid
                            WebElement gridAgain = wait.until(
                                    ExpectedConditions.visibilityOfElementLocated(rlQuestionPad));
                            List<WebElement> questionPadsAgain = gridAgain.findElements(
                                    By.className("android.widget.LinearLayout"));

                            if (!questionPadsAgain.isEmpty()) {
                                int secondRandomIndex = random.nextInt(questionPadsAgain.size());
                                WebElement secondRandomQuestion = questionPadsAgain.get(secondRandomIndex);
                                String secondQuestionNo = "N/A";
                                try {
                                    secondQuestionNo = secondRandomQuestion.getText().trim();
                                } catch (Exception ignored) {}
                                secondRandomQuestion.click();
                                System.out.println("✅ Second Random Question Selected");
                                System.out.println("SECOND QUESTION PAD : " + secondQuestionNo);
                                Thread.sleep(1500);
                            }
                        }
                    }

                    // Click Next button
                    WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(btnNext));
                    nextBtn.click();
                    System.out.println("✅ NEXT Button Clicked");
                    Thread.sleep(1200);

                } catch (Exception e) {
                    System.out.println("❌ No More Questions Found : " + e.getMessage());
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed During Test Execution", e);
        }
    }

    public static boolean isUsefulTimerText(String timerText) {
        return timerText != null
                && TIMER_PATTERN.matcher(timerText.trim()).matches()
                && !"00:00:00".equals(timerText.trim())
                && !"00:00".equals(timerText.trim());
    }

    private static String normalizeText(String value) {
        if (value == null) return "";
        return value
                .toLowerCase()
                .replace("(", "")
                .replace(")", "")
                .replaceAll("\\s+", " ")
                .trim();
    }
}
