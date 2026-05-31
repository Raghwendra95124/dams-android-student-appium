package pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static pages.CbtPage.driver;
import static pages.CbtPage.wait;
import static pages.DailyQuiz.finishBtn;
import static pages.DailyQuiz.nextBtn;
import static pages.TestSeries.tvQuestion;

public class Qbank {

    // ====================================
// LOCATORS
// ====================================

    // Total MCQS Count
    public static final By qbTotalQuestionsCount =
            By.id("com.emedicoz.app:id/qbTotalQb");
   // MCQ Text
    public static final By qbMcqsText =
            By.id("com.emedicoz.app:id/qbtxtQuestions");

    public static final By topicCount17of48 =
            By.xpath("//android.widget.TextView[@resource-id='com.emedicoz.app:id/txtSubTotalCount' and @text='17/48 topics']");

    // Category Layout
    public static final By qBankCategoryText = By.xpath("//android.widget.TextView[@text='Qbank' and @resource-id='com.emedicoz.app:id/tvCatName']");

    // QBank Layout
    public static final By qBankLyt =
            By.id("com.emedicoz.app:id/qBankLyt");

    // Start Test Button
    public static final By startTestBtn =
            By.id("com.emedicoz.app:id/crdStartTest");

    // PRO Lock Text
    public static final By proLockText =
            By.id("com.emedicoz.app:id/tvLock");

    // Progress Percentage Bar
    public static final By progressPercentageBar =
            By.id("com.emedicoz.app:id/progressPercentage");

    // Completed Test Icon
    public static final By completedTestIcon =
            By.id("com.emedicoz.app:id/imCompletedTest");

// QBANK RECYCLER VIEW

    public static final By qBankRecyclerView =
            By.id("com.emedicoz.app:id/rvqBankItem");

    // Back Button
    public static final By btnBack =
            By.id("com.emedicoz.app:id/btnBack");

    // Question Number
    private static By questionNumberText =
            By.id("com.emedicoz.app:id/tv_questionnumber");

    //Qbank Category Titile
    private static By txtqbItems =
            By.id("com.emedicoz.app:id/txtqb_items");

    public static final By rootLayout =
            By.id("com.emedicoz.app:id/root_layout");

    public static final By mcqOptions =
            By.id("com.emedicoz.app:id/mcqoptions");

    public static final By ivExpUrl =
            By.id("com.emedicoz.app:id/ivExpUrl");

    public static final By exoClose =
            By.id("com.emedicoz.app:id/exoClose");

    public static final By btnNext =
            By.id("com.emedicoz.app:id/btn_next");

    public static final By imgBookmark =
            By.id("com.emedicoz.app:id/img_bookmark");

    public static final By btnFinish =
            By.id("com.emedicoz.app:id/btn_finish");

    private static By submitBtn = By.id("com.emedicoz.app:id/btn_submit");
// Qbank Titile
    private static By qbTxtHeader =
            By.id("com.emedicoz.app:id/qbTxtHeader");

    // QUIZ TITLE
    public static final By quizTitleTv =
            By.id("com.emedicoz.app:id/quiz_title_tv");
    private static By txtSubTotalCount;
    private static By txtqb_items;
    private static By rvNumberTestItem;
    private static By categoryLayout;

    // ====================================
// METHOD : CLICK QBANK CATEGORY
// AND SELECT RANDOM QBANK
// ====================================
    public static void selectRandomQBank() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        // ====================================
        // CLICK QBANK CATEGORY
        // ====================================

        wait.until(ExpectedConditions.elementToBeClickable(qBankCategoryText))
                .click();

        System.out.println("✅ QBank Category Clicked");

        // ====================================
        // GET QBANK CATEGORY TITLE
        // ====================================

        String qbankCategoryTitle = "N/A";

        try {

            WebElement categoryTitle = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            txtqbItems));

            qbankCategoryTitle =
                    categoryTitle.getText().trim();

        } catch (Exception ignored) {}

        // ====================================
        // PRINT CATEGORY EVERY TIME
        // ====================================

        System.out.println("=================================");
        System.out.println("QBANK CATEGORY : " + qbankCategoryTitle);
        System.out.println("=================================");

        ReportManager.logStep(
                "QBank",
                "QBank Category : " + qbankCategoryTitle,
                true
        );

        // ====================================
        // SCROLL QBANK LIST
        // ====================================

        try {

            Dimension size =
                    driver.manage().window().getSize();

            int startY = (int) (size.height * 0.8);
            int endY = (int) (size.height * 0.3);
            int centerX = size.width / 2;

            new TouchAction<>(driver)
                    .press(PointOption.point(centerX, startY))
                    .waitAction(
                            WaitOptions.waitOptions(
                                    Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(centerX, endY))
                    .release()
                    .perform();

            System.out.println("✅ QBank List Scrolled");

        } catch (Exception ignored) {}

        // ====================================
        // WAIT FOR QBANK LIST
        // ====================================

        wait.until(ExpectedConditions.visibilityOfElementLocated(qBankLyt));

        List<WebElement> qBankList =
                driver.findElements(qBankLyt);

        // ====================================
        // SELECT RANDOM QBANK
        // ====================================

        if (!qBankList.isEmpty()) {

            int randomIndex =
                    new Random().nextInt(qBankList.size());

            WebElement selectedQbank =
                    qBankList.get(randomIndex);

            // ====================================
            // GET QBANK NAME
            // ====================================

            String selectedQbankName = "N/A";

            try {

                selectedQbankName =
                        selectedQbank.findElement(
                                        txtqb_items)
                                .getText()
                                .trim();

            } catch (Exception ignored) {}

            // ====================================
            // GET QBANK TOTAL COUNT
            // ====================================

            String qbankTotalCount = "N/A";

            try {

                qbankTotalCount =
                        selectedQbank.findElement(
                                        txtSubTotalCount)
                                .getText()
                                .trim();

            } catch (Exception ignored) {}

            // ====================================
            // SHOW REPORT BEFORE CLICK
            // ====================================

            System.out.println("=================================");
            System.out.println(
                    "QBANK CATEGORY   : "
                            + qbankCategoryTitle);

            System.out.println(
                    "SELECTED QBANK   : "
                            + selectedQbankName);

            System.out.println(
                    "QBANK TOTAL COUNT: "
                            + qbankTotalCount);

            System.out.println("=================================");

            ReportManager.logStep(
                    "QBank",
                    "QBank Category : "
                            + qbankCategoryTitle
                            + " | Selected QBank : "
                            + selectedQbankName
                            + " | QBank Total Count : "
                            + qbankTotalCount,
                    true
            );

            // ====================================
            // CLICK RANDOM QBANK
            // ====================================

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            selectedQbank));

            selectedQbank.click();

            System.out.println(
                    "✅ Random QBank Selected");

            try {

                Thread.sleep(3000);

            } catch (InterruptedException ignored) {}

            // ====================================
            // GET QBANK TITLE WHOSE START BUTTON
            // IS PRESENT
            // ====================================

            String qbankTitle = "N/A";

            try {

                List<WebElement> startButtons =
                        driver.findElements(startTestBtn);

                if (!startButtons.isEmpty()
                        && startButtons.get(0).isDisplayed()) {

                    WebElement title = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    qbTxtHeader));

                    qbankTitle =
                            title.getText().trim();
                }

            } catch (Exception ignored) {}

            // ====================================
            // FINAL REPORT
            // ====================================

            System.out.println("=================================");
            System.out.println(
                    "QBANK TITLE : "
                            + qbankTitle);
            System.out.println("=================================");

            ReportManager.logStep(
                    "QBank",
                    "QBank Title : "
                            + qbankTitle,
                    true
            );

        } else {

            System.out.println("❌ No QBank Found");

            ReportManager.logStep(
                    "QBank",
                    "No QBank Found",
                    false
            );

        }
    }
// ====================================
// METHOD : HANDLE QBANK TEST FLOW  Codition Wise  Start, Inprogress, Completed , Pro================================================
// ====================================

    public static void handleQbankTestFlow() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        try {

            // ====================================
            // WAIT FOR QBANK PAGE
            // ====================================

            wait.until(ExpectedConditions.visibilityOfElementLocated(qBankRecyclerView));

            // ====================================
            // CONDITION 1 : START BUTTON
            // ====================================

            List<WebElement> startButtons =
                    driver.findElements(startTestBtn);

            if (!startButtons.isEmpty()) {

                wait.until(ExpectedConditions.elementToBeClickable(startButtons.get(0)))
                        .click();

                System.out.println("✅ Start Test Button Clicked");

                return;
            }

            // ====================================
            // CONDITION 2 : PRO LOCK
            // ====================================

            List<WebElement> proLocks =
                    driver.findElements(proLockText);

            if (!proLocks.isEmpty()) {

                wait.until(ExpectedConditions.elementToBeClickable(proLocks.get(0)))
                        .click();

                System.out.println("✅ PRO Locked QBank Clicked");

                return;
            }

            // ====================================
            // CONDITION 3 : PROGRESS BAR
            // ====================================

            List<WebElement> progressBars =
                    driver.findElements(progressPercentageBar);

            if (!progressBars.isEmpty()) {

                wait.until(ExpectedConditions.elementToBeClickable(progressBars.get(0)))
                        .click();

                System.out.println("✅ In Progress QBank Clicked");

                return;
            }

            // ====================================
            // CONDITION 4 : COMPLETED TEST
            // ====================================

            List<WebElement> completedTests =
                    driver.findElements(completedTestIcon);

            if (!completedTests.isEmpty()) {

                wait.until(ExpectedConditions.elementToBeClickable(completedTests.get(0)))
                        .click();

                System.out.println("✅ Completed Test QBank Clicked");

                return;
            }

            // ====================================
            // NO QBANK FOUND
            // ====================================

            System.out.println("⚠️ Alert: No Qbank Found On This Qbank Page");

        } catch (Exception e) {

            System.out.println("❌ Unable To Open Any QBank");
            e.printStackTrace();
        }
    }

    public static void openQbankUntilStartTestFound() {

        while (true) {

            try {

                // =====================================================
                // WAIT FOR PAGE LOAD
                // =====================================================

                Thread.sleep(2000);

                // =====================================================
                // GET QBANK CATEGORY NAME
                // =====================================================

                String qbankCategoryName = "N/A";

                try {

                    WebElement categoryTitle = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    txtqbItems));

                    qbankCategoryName =
                            categoryTitle.getText().trim();

                    // ================================================
                    // SHOW CATEGORY NAME EVERY LOOP
                    // ================================================

                    System.out.println("=================================");
                    System.out.println(
                            "QBANK CATEGORY : "
                                    + qbankCategoryName);
                    System.out.println("=================================");

                    ReportManager.logStep(
                            "QBank",
                            "QBank Category Opened : "
                                    + qbankCategoryName,
                            true
                    );

                } catch (Exception ignored) {}

                // =====================================================
                // GET CURRENT QBANK TITLE
                // =====================================================

                String qbankTitle = "N/A";

                try {

                    WebElement title = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    qbTxtHeader));

                    qbankTitle =
                            title.getText().trim();

                } catch (Exception e) {

                    System.out.println(
                            "Unable To Fetch QBank Title");
                }

                // =====================================================
                // GET CURRENT QBANK QUESTION COUNT
                // =====================================================

                String totalQuestions = "N/A";

                try {

                    WebElement totalQues = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    qbTotalQuestionsCount));

                    totalQuestions =
                            totalQues.getText().trim();

                } catch (Exception ignored) {}

                // =====================================================
                // GET MCQS TEXT
                // =====================================================

                String mcqsText = "N/A";

                try {

                    WebElement mcqs = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    qbMcqsText));

                    mcqsText =
                            mcqs.getText().trim();

                } catch (Exception ignored) {}

                // =====================================================
                // CHECK START TEST BUTTON
                // =====================================================

                List<WebElement> startButtons =
                        driver.findElements(startTestBtn);

                if (!startButtons.isEmpty()
                        && startButtons.get(0).isDisplayed()) {

                    // =================================================
                    // GET START BUTTON
                    // =================================================

                    WebElement startBtn = wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    startTestBtn));

                    // =================================================
                    // PRINT MATCHED QBANK DETAILS
                    // =================================================

                    System.out.println("=================================");
                    System.out.println(
                            "QBANK CATEGORY      : "
                                    + qbankCategoryName);

                    System.out.println(
                            "MATCHED QBANK TITLE : "
                                    + qbankTitle);

                    System.out.println(
                            "MATCHED QBANK COUNT : "
                                    + totalQuestions);

                    System.out.println(
                            "MATCHED MCQS TEXT   : "
                                    + mcqsText);

                    System.out.println("=================================");

                    // =================================================
                    // REPORTING
                    // =================================================

                    ReportManager.logStep(
                            "QBank",
                            "QBank Category : "
                                    + qbankCategoryName
                                    + " | QBank Title : "
                                    + qbankTitle
                                    + " | QBank Count : "
                                    + totalQuestions
                                    + " | MCQS Text : "
                                    + mcqsText,
                            true
                    );

                    // =================================================
                    // CLICK START TEST BUTTON
                    // =================================================

                    startBtn.click();

                    System.out.println(
                            "✅ Start Test Button Clicked For QBank : "
                                    + qbankTitle);

                    ReportManager.logStep(
                            "QBank",
                            "Start Test Button Clicked For QBank : "
                                    + qbankTitle,
                            true
                    );

                    break;
                }

            } catch (Exception ignored) {}

            // =========================================================
            // START TEST NOT FOUND
            // =========================================================

            try {

                // CLICK BACK BUTTON
                WebElement backBtn = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                btnBack));

                backBtn.click();

                System.out.println(
                        "Clicked Back Button");

                ReportManager.logStep(
                        "QBank",
                        "Back To QBank List",
                        true
                );

                Thread.sleep(2000);

                // =====================================================
                // GET ALL QBANKS
                // =====================================================

                List<WebElement> qbanks = wait.until(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(
                                qBankLyt));

                if (qbanks.isEmpty()) {

                    throw new RuntimeException(
                            "No QBank Found");
                }

                // =====================================================
                // SCROLL QBANK LIST
                // =====================================================

                try {

                    Dimension size =
                            driver.manage().window().getSize();

                    int startY =
                            (int) (size.height * 0.8);

                    int endY =
                            (int) (size.height * 0.3);

                    int centerX =
                            size.width / 2;

                    new TouchAction<>(driver)
                            .press(PointOption.point(centerX, startY))
                            .waitAction(
                                    WaitOptions.waitOptions(
                                            Duration.ofSeconds(1)))
                            .moveTo(
                                    PointOption.point(centerX, endY))
                            .release()
                            .perform();

                    System.out.println(
                            "QBank List Scrolled");

                } catch (Exception ignored) {}

                // =====================================================
                // CLICK RANDOM QBANK
                // =====================================================

                int randomIndex =
                        new java.util.Random().nextInt(
                                qbanks.size());

                WebElement randomQBank =
                        qbanks.get(randomIndex);

                String selectedQbankName = "N/A";

                try {

                    selectedQbankName =
                            randomQBank.getText().trim();

                } catch (Exception ignored) {}

                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                randomQBank));

                randomQBank.click();

                // =====================================================
                // SHOW SELECTED QBANK NAME
                // =====================================================

                System.out.println(
                        "Clicked Random QBank : "
                                + selectedQbankName);

                ReportManager.logStep(
                        "QBank",
                        "Random QBank Selected : "
                                + selectedQbankName,
                        true
                );

                Thread.sleep(3000);

            } catch (Exception e) {

                throw new RuntimeException(
                        "Failed While Searching Start Test Button",
                        e);
            }
        }
    }
    public static void attemptQuestionsUntilFinish() {

        int questionCount = 1;

        while (true) {

            try {

                // =====================================================
                // SELECT RANDOM OPTION
                // =====================================================

                List<WebElement> options = wait.until(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(
                                mcqOptions));

                if (!options.isEmpty()) {

                    int randomOption =
                            new java.util.Random().nextInt(options.size());

                    WebElement selectedOption =
                            options.get(randomOption);

                    wait.until(ExpectedConditions.elementToBeClickable(
                            selectedOption));

                    selectedOption.click();

                    System.out.println(
                            "Question "
                                    + questionCount
                                    + " -> Random Option Selected");

                    ReportManager.logStep(
                            "Quiz",
                            "Question "
                                    + questionCount
                                    + " -> Random Option Selected",
                            true);
                }

                // =====================================================
                // CHECK FINISH BUTTON
                // =====================================================

                List<WebElement> finishButtons =
                        driver.findElements(finishBtn);

                if (!finishButtons.isEmpty()
                        && finishButtons.get(0).isDisplayed()) {

                    wait.until(ExpectedConditions.elementToBeClickable(
                            finishButtons.get(0)));

                    finishButtons.get(0).click();

                    System.out.println(
                            "Finish Button Found & Clicked");

                    // =====================================================
                    // CLICK SUBMIT BUTTON
                    // =====================================================

                    try {

                        WebElement submit = wait.until(
                                ExpectedConditions.elementToBeClickable(
                                        submitBtn));

                        if (submit.isDisplayed()) {

                            submit.click();

                            System.out.println(
                                    "Submit Button Clicked");

                            ReportManager.logStep(
                                    "Quiz",
                                    "Submit Button Clicked",
                                    true);
                        }

                    } catch (Exception e) {

                        System.out.println(
                                "Submit Button Not Found");
                    }

                    ReportManager.logStep(
                            "Quiz",
                            "Finish Button Clicked",
                            true);

                    break;
                }

                // =====================================================
                // SCROLL ROOT LAYOUT
                // =====================================================

                try {

                    WebElement root = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    rootLayout));

                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView(true);",
                            root);

                } catch (Exception ignored) {}

                // =====================================================
                // SCROLL DOWN
                // =====================================================

                try {

                    Dimension size =
                            driver.manage().window().getSize();

                    int startY = (int) (size.height * 0.8);
                    int endY = (int) (size.height * 0.3);
                    int centerX = size.width / 2;

                    new TouchAction<>(driver)
                            .press(PointOption.point(centerX, startY))
                            .waitAction(
                                    WaitOptions.waitOptions(
                                            Duration.ofSeconds(1)))
                            .moveTo(PointOption.point(centerX, endY))
                            .release()
                            .perform();

                } catch (Exception ignored) {}

                // =====================================================
                // VIDEO SOLUTION
                // =====================================================

                try {

                    List<WebElement> videoBtns =
                            driver.findElements(ivExpUrl);

                    if (!videoBtns.isEmpty()
                            && videoBtns.get(0).isDisplayed()) {

                        wait.until(ExpectedConditions.elementToBeClickable(
                                videoBtns.get(0)));

                        videoBtns.get(0).click();

                        System.out.println(
                                "Video Solution Opened");

                        Thread.sleep(5000);

                        WebElement closeBtn = wait.until(
                                ExpectedConditions.elementToBeClickable(
                                        exoClose));

                        closeBtn.click();

                        System.out.println(
                                "Video Solution Closed");

                        ReportManager.logStep(
                                "Quiz",
                                "Video Solution Played",
                                true);

                        // =====================================================
                        // RESELECT OPTION AFTER VIDEO CLOSE
                        // =====================================================

                        List<WebElement> reselectOptions = wait.until(
                                ExpectedConditions.presenceOfAllElementsLocatedBy(
                                        mcqOptions));

                        if (!reselectOptions.isEmpty()) {

                            int randomReselectOption =
                                    new java.util.Random().nextInt(
                                            reselectOptions.size());

                            WebElement selectedReselectOption =
                                    reselectOptions.get(randomReselectOption);

                            wait.until(
                                    ExpectedConditions.elementToBeClickable(
                                            selectedReselectOption));

                            selectedReselectOption.click();

                            System.out.println(
                                    "Option Re-Selected After Video Close");
                        }

                    } else {

                        System.out.println(
                                "No Video Solution is Add");

                        ReportManager.logStep(
                                "Quiz",
                                "No Video Solution Available",
                                true);
                    }

                } catch (Exception e) {

                    System.out.println(
                            "No Video Solution is Add");
                }

                // =====================================================
                // BOOKMARK EVERY 5 QUESTION
                // =====================================================

                try {

                    if (questionCount % 5 == 0) {

                        WebElement bookmark = wait.until(
                                ExpectedConditions.elementToBeClickable(
                                        imgBookmark));

                        bookmark.click();

                        System.out.println(
                                "Bookmark Clicked On Question "
                                        + questionCount);

                        ReportManager.logStep(
                                "Quiz",
                                "Bookmark Clicked On Question "
                                        + questionCount,
                                true);
                    }

                } catch (Exception ignored) {}

                // =====================================================
                // CLICK NEXT
                // =====================================================

                WebElement next = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                nextBtn));

                next.click();

                System.out.println(
                        "Next Button Clicked");

                questionCount++;

                Thread.sleep(3000);

            } catch (Exception e) {

                throw new RuntimeException(
                        "Quiz Flow Failed",
                        e);
            }
        }
    }

    private static class ReportManager {
        public static void logStep(String qBank, String s, boolean b) {
        }


    }

    // Validate Question Count
    public static void validateQuestionCount() {

        try {

            // Get Question Number Text (Example: 1/53)
            String questionText = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(questionNumberText))
                    .getText()
                    .trim();

            System.out.println("Question Text: " + questionText);

            // Extract Total Question Count From 1/53
            String totalQuestionCount =
                    questionText.split("/")[1].trim();

            System.out.println("Total Questions From Quiz: " + totalQuestionCount);

            // Get Qbank Total Count
            String qbankCount = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(qbTotalQuestionsCount))
                    .getText()
                    .replaceAll("[^0-9]", "")
                    .trim();

            System.out.println("Qbank Total Count: " + qbankCount);

            // Compare Counts
            if (totalQuestionCount.equals(qbankCount)) {

                System.out.println("✅ Qbank Count Matched");

            } else {

                System.out.println("❌ ALERT: Qbank Count Is Mismatched");
                System.out.println("Quiz Count: " + totalQuestionCount);
                System.out.println("Qbank Count: " + qbankCount);
            }

        } catch (Exception e) {

            System.out.println("Error While Validating Question Count");
            e.printStackTrace();
        }
    }

    public static void attemptQuestionswithappswitch() {

        int questionCount = 1;

        Random random = new Random();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        while (true) {

            try {

                // =====================================================
                // GET CURRENT QUESTION
                // =====================================================

                String currentQuestion = "N/A";

                try {

                    currentQuestion = wait.until(
                                    ExpectedConditions.visibilityOfElementLocated(
                                            tvQuestion))
                            .getText()
                            .trim();

                    System.out.println(
                            "CURRENT QUESTION : "
                                    + currentQuestion);

                } catch (Exception ignored) {}

                // =====================================================
                // SELECT RANDOM OPTION
                // =====================================================

                List<WebElement> options = wait.until(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(
                                mcqOptions));

                if (!options.isEmpty()) {

                    WebElement selectedOption =
                            options.get(
                                    random.nextInt(options.size()));

                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    selectedOption));

                    selectedOption.click();

                    System.out.println(
                            "Question "
                                    + questionCount
                                    + " -> Random Option Selected");

                    ReportManager.logStep(
                            "Quiz",
                            "Question "
                                    + questionCount
                                    + " -> Random Option Selected",
                            true);
                }

                // =====================================================
                // EVERY 3RD QUESTION
                // APP SWITCH VALIDATION
                // =====================================================

                if (questionCount % 3 == 0) {

                    System.out.println(
                            "========== APP SWITCH VALIDATION ==========");

                    // =================================================
                    // HOME BUTTON
                    // =================================================

                    driver.pressKey(
                            new KeyEvent(AndroidKey.HOME));

                    System.out.println(
                            "✅ HOME Button Pressed");

                    Thread.sleep(500);

                    // =================================================
                    // APP SWITCH
                    // =================================================

                    driver.pressKey(
                            new KeyEvent(AndroidKey.APP_SWITCH));

                    System.out.println(
                            "✅ APP SWITCH Button Pressed");

                    Thread.sleep(500);

                    // =================================================
                    // REOPEN APP
                    // =================================================

                    driver.activateApp("com.emedicoz.app");

                    System.out.println(
                            "✅ App Reopened");

                    Thread.sleep(1500);

                    // =================================================
                    // VERIFY SAME QUESTION
                    // =================================================

                    String reopenedQuestion = "N/A";

                    try {

                        reopenedQuestion = wait.until(
                                        ExpectedConditions.visibilityOfElementLocated(
                                                tvQuestion))
                                .getText()
                                .trim();

                    } catch (Exception ignored) {}

                    System.out.println(
                            "PREVIOUS QUESTION : "
                                    + currentQuestion);

                    System.out.println(
                            "REOPENED QUESTION : "
                                    + reopenedQuestion);

                    // =================================================
                    // VALIDATION
                    // =================================================

                    if (!currentQuestion.equals(
                            reopenedQuestion)) {

                        System.out.println(
                                "❌ SAME QUESTION NOT OPEN AFTER APP SWITCH");

                        ReportManager.logStep(
                                "Quiz",
                                "❌ Same Question Not Open After App Switch",
                                false);

                        throw new RuntimeException(
                                "Same Question Is Not Open After Switching The App");
                    }

                    System.out.println(
                            "✅ SAME QUESTION VERIFIED");

                    ReportManager.logStep(
                            "Quiz",
                            "✅ Same Question Verified After App Switch",
                            true);
                }

                // =====================================================
                // CHECK FINISH BUTTON
                // =====================================================

                List<WebElement> finishButtons =
                        driver.findElements(finishBtn);

                if (!finishButtons.isEmpty()
                        && finishButtons.get(0).isDisplayed()) {

                    WebElement finish =
                            finishButtons.get(0);

                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    finish));

                    finish.click();

                    System.out.println(
                            "Finish Button Found & Clicked");

                    // =================================================
                    // SUBMIT BUTTON
                    // =================================================

                    try {

                        WebElement submit = wait.until(
                                ExpectedConditions.elementToBeClickable(
                                        submitBtn));

                        if (submit.isDisplayed()) {

                            submit.click();

                            System.out.println(
                                    "Submit Button Clicked");

                            ReportManager.logStep(
                                    "Quiz",
                                    "Submit Button Clicked",
                                    true);
                        }

                    } catch (Exception e) {

                        System.out.println(
                                "Submit Button Not Found");
                    }

                    ReportManager.logStep(
                            "Quiz",
                            "Finish Button Clicked",
                            true);

                    break;
                }

                // =====================================================
                // FAST SCROLL
                // =====================================================

                try {

                    Dimension size =
                            driver.manage().window().getSize();

                    int startY = (int) (size.height * 0.75);
                    int endY = (int) (size.height * 0.35);
                    int centerX = size.width / 2;

                    new TouchAction<>(driver)
                            .press(PointOption.point(centerX, startY))
                            .waitAction(
                                    WaitOptions.waitOptions(
                                            Duration.ofMillis(300)))
                            .moveTo(PointOption.point(centerX, endY))
                            .release()
                            .perform();

                } catch (Exception ignored) {}

                // =====================================================
                // VIDEO SOLUTION
                // =====================================================

                try {

                    List<WebElement> videoBtns =
                            driver.findElements(ivExpUrl);

                    if (!videoBtns.isEmpty()
                            && videoBtns.get(0).isDisplayed()) {

                        WebElement videoBtn =
                                videoBtns.get(0);

                        wait.until(
                                ExpectedConditions.elementToBeClickable(
                                        videoBtn));

                        videoBtn.click();

                        System.out.println(
                                "Video Solution Opened");

                        Thread.sleep(2500);

                        wait.until(
                                        ExpectedConditions.elementToBeClickable(
                                                exoClose))
                                .click();

                        System.out.println(
                                "Video Solution Closed");

                        ReportManager.logStep(
                                "Quiz",
                                "Video Solution Played",
                                true);

                        // =================================================
                        // RESELECT OPTION
                        // =================================================

                        List<WebElement> reselectOptions =
                                wait.until(
                                        ExpectedConditions.presenceOfAllElementsLocatedBy(
                                                mcqOptions));

                        if (!reselectOptions.isEmpty()) {

                            WebElement reselectOption =
                                    reselectOptions.get(
                                            random.nextInt(
                                                    reselectOptions.size()));

                            wait.until(
                                    ExpectedConditions.elementToBeClickable(
                                            reselectOption));

                            reselectOption.click();

                            System.out.println(
                                    "Option Re-Selected After Video Close");
                        }

                    } else {

                        System.out.println(
                                "No Video Solution Added");

                        ReportManager.logStep(
                                "Quiz",
                                "No Video Solution Available",
                                true);
                    }

                } catch (Exception e) {

                    System.out.println(
                            "No Video Solution Added");
                }

                // =====================================================
                // BOOKMARK EVERY 5 QUESTION
                // =====================================================

                try {

                    if (questionCount % 5 == 0) {

                        wait.until(
                                        ExpectedConditions.elementToBeClickable(
                                                imgBookmark))
                                .click();

                        System.out.println(
                                "Bookmark Clicked On Question "
                                        + questionCount);

                        ReportManager.logStep(
                                "Quiz",
                                "Bookmark Clicked On Question "
                                        + questionCount,
                                true);
                    }

                } catch (Exception ignored) {}

                // =====================================================
                // NEXT BUTTON
                // =====================================================

                wait.until(
                                ExpectedConditions.elementToBeClickable(
                                        nextBtn))
                        .click();

                System.out.println(
                        "Next Button Clicked");

                questionCount++;

                Thread.sleep(1200);

            } catch (Exception e) {

                throw new RuntimeException(
                        "Quiz Flow Failed",
                        e);
            }
        }
    }
}


