package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static pages.CbtPage.driver;
import static pages.CbtPage.wait;

public class DailyQuiz {
    // =========================================================
    // Course Category Select


    private static final By neetPgMbbs = By.id("com.emedicoz.app:id/tvCategoryName");

    private static final By selectCourseLayout = By.id("com.emedicoz.app:id/selectCourse");

    // Select Rendom Category
    private static final By courseRecyclerView = By.id("com.emedicoz.app:id/recyclerCourse");
    // Done Button

    private static final By doneBtn = By.id("com.emedicoz.app:id/nextBtn");

    // 🔹 START QUIZ
    // =========================================================
    public static final By startQuizBtn = By.id("com.emedicoz.app:id/btnStartQuiz");
    public static final By startQuizText = By.xpath("//android.widget.TextView[@text='START QUIZ']");
    // Allready Attempt
    public static final By attemptMoreQuizText = By.xpath("//android.widget.TextView[@text='Attempt More Quiz']");

    // =========================================================
    // 🔹 MCQ OPTIONS
    // =========================================================
    public static final By mcqOptions = By.id("com.emedicoz.app:id/mcqoptions");
    public static final By optionContainer = By.id("com.emedicoz.app:id/viewLL");
    public static final By optionItems = By.id("com.emedicoz.app:id/mainLayout");

    // =========================================================
    // 🔹 NEXT / PREVIOUS / FINISH
    // =========================================================
    public static final By nextBtn = By.id("com.emedicoz.app:id/btn_next");
    public static final By nextText = By.xpath("//android.widget.TextView[@text='Next']");

    public static final By prevBtn = By.id("com.emedicoz.app:id/btn_prev");
    public static final By prevText = By.xpath("//android.widget.TextView[@text='Previous']");

    public static final By finishBtn = By.id("com.emedicoz.app:id/btn_finish");

    // =========================================================
    // 🔹 ANALYSIS
    // =========================================================
    public static final By viewAnalysisText = By.xpath("//android.widget.TextView[@text='View Analysis']");
    public static final By analysisLayout = By.id("com.emedicoz.app:id/lytAnalysis");

    // =========================================================
    // 🔹 GRAPH
    // =========================================================
    public static final By graphImage = By.id("com.emedicoz.app:id/imgGraph");
    public static final By graphText = By.id("com.emedicoz.app:id/graphTV");

    // =========================================================
    // 🔹 BACK BUTTON
    // =========================================================
    public static final By backBtn = By.id("com.emedicoz.app:id/back_app_bar");

    // =========================================================
    // 🔹 LEADERBOARD
    // =========================================================
    public static final By leaderBoardLayout = By.id("com.emedicoz.app:id/lytLeaderBoard");
    public static final By leaderBoardText = By.xpath("//android.widget.TextView[@text='View LeaderBoard']");

    // =========================================================
    // 🔹 EXPAND LIST
    // =========================================================
    public static final By expandList = By.xpath("//android.widget.TextView[@text='Expand List']");

    // =========================================================
    // 🔹 REVIEW ANSWERS
    // =========================================================
    public static final By reviewAnswersBtn = By.id("com.emedicoz.app:id/btnReviewAnswer");
    public static final By reviewAnswersText = By.xpath("//android.widget.Button[@text='Review Answers']");

    // 🔹 View All (Quiz of the Day)
    private static final By viewAllQuiz = By.id("com.emedicoz.app:id/clickQuizOfTheDay");

    // 🔹 START QUIZ (New ID)
    private static final By startQuizTextNew = By.id("com.emedicoz.app:id/startTV");

    // Allready Attempt
    private static final By alreadyAttemptBtn = By.xpath(
            "//android.widget.Button[@text='Already Attempt' and @resource-id='com.emedicoz.app:id/btnStartQuiz']"
    );


    // 🔹 Filter Icon
    public static final By filterIcon = By.id("com.emedicoz.app:id/filter");

    // 🔹 Filter Container (optional parent)
    public static final By filterLayout = By.className("android.widget.LinearLayout");

    // 🔹 Filter Buttons
    public static final By filterAll = By.id("com.emedicoz.app:id/btnAll");

    public static final By filterCorrect = By.id("com.emedicoz.app:id/btnCorrect");

    public static final By filterIncorrect = By.id("com.emedicoz.app:id/btnIncorrect");

    public static final By filterSkipped = By.id("com.emedicoz.app:id/btnSkipped");

    public static final By filterBookmarked = By.id("com.emedicoz.app:id/btnBookmarked");

    // 🔹 Index Icon (Grid / Question Index Button)
    public static final By indexIcon =
            By.id("com.emedicoz.app:id/index");

    // 🔹 Question Number Text (e.g., 1, 2, 3...)
    public static final By questionIndexNumber =
            By.id("com.emedicoz.app:id/myImageViewText");
// List View
static By listView = By.id("com.emedicoz.app:id/listView");

    // Select Course Then Go To Home Screen
    public static void selectNeetPgMbbsCourse() {

        // 🔹 1. Click NEETPG MBBS (Home / Category if present)
        try {
            wait.until(ExpectedConditions.elementToBeClickable(neetPgMbbs)).click();
            System.out.println("✅ Clicked NEETPG MBBS Category");
        } catch (Exception e) {
            System.out.println("NEETPG MBBS not visible on this screen → skipping");
        }

        // 🔹 3. Wait for RecyclerView
        wait.until(ExpectedConditions.visibilityOfElementLocated(courseRecyclerView));

        // 🔹 4. Select NEETPG MBBS from list
        By neetPgMbbsItem = By.xpath(
                "//android.widget.TextView[@resource-id='com.emedicoz.app:id/tvItemTitle' and @text='NEETPG MBBS']"
        );

        // 🔹 Scroll inside RecyclerView
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\"com.emedicoz.app:id/recyclerCourse\"))" +
                        ".scrollIntoView(new UiSelector().text(\"NEETPG MBBS\"));"
        ));

        // 🔹 Click item
        wait.until(ExpectedConditions.elementToBeClickable(neetPgMbbsItem)).click();
        System.out.println("✅ Selected NEETPG MBBS Course");

        // 🔹 5. Click Done Button
        wait.until(ExpectedConditions.elementToBeClickable(doneBtn)).click();
        System.out.println("✅ Clicked Done");
    }

// Daily Quiz Attempt NormalFlow------
    public static void playQuizTillFinish() {

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Random random = new Random();

        // 🔹 1. Scroll & Click START QUIZ
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().resourceId(\"com.emedicoz.app:id/btnStartQuiz\"));"
            ));
        } catch (Exception e) {
            System.out.println("Scroll not needed");
        }

        shortWait.until(ExpectedConditions.elementToBeClickable(startQuizBtn)).click();
        System.out.println("✅ Quiz Started");

        // =====================================================
        // 🔥 QUIZ LOOP
        // =====================================================
        for (int i = 0; i < 100; i++) {

            try {
                // 🔹 Wait for options
                shortWait.until(ExpectedConditions.visibilityOfElementLocated(mcqOptions));

                List<WebElement> options = shortWait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(optionItems)
                );

                // 🔹 Select random option
                if (!options.isEmpty()) {
                    int index = random.nextInt(options.size());
                    WebElement option = options.get(index);

                    shortWait.until(ExpectedConditions.elementToBeClickable(option)).click();
                    System.out.println("✅ Selected Option: " + index);
                } else {
                    System.out.println("❌ No options found");
                }

                // 🔹 SMALL WAIT (important for UI update)
                Thread.sleep(500);

                // 🔹 If NEXT present → click NEXT
                if (driver.findElements(nextBtn).size() > 0) {

                    shortWait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
                    System.out.println("➡️ Clicked Next");

                }
                // 🔹 ELSE if FINISH present → click FINISH
                else if (driver.findElements(finishBtn).size() > 0) {

                    shortWait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
                    System.out.println("🏁 Clicked Finish (Last Question)");
                    break;
                }

            } catch (Exception e) {
                System.out.println("❌ Error in quiz loop → breaking");
                break;
            }
        }

        System.out.println("✅ Quiz Flow Completed");
    }

    // Allready Attempt Daily Quiz Flow
    public static void handleAttemptMoreQuizFlow() {

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 🔹 1. Scroll & Click "Attempt More Quiz"
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"Attempt More Quiz\"));"
            ));
        } catch (Exception e) {
            System.out.println("Scroll not needed");
        }

        try {
            shortWait.until(ExpectedConditions.elementToBeClickable(attemptMoreQuizText)).click();
            System.out.println("✅ Clicked Attempt More Quiz");
        } catch (Exception e) {
            System.out.println("❌ Attempt More Quiz not found");
            return;
        }

        // 🔹 2. Click "Already Attempt"
        By alreadyAttemptBtn = By.xpath(
                "//android.widget.Button[@resource-id='com.emedicoz.app:id/btnStartQuiz' and @text='Already Attempt']"
        );

        shortWait.until(ExpectedConditions.elementToBeClickable(alreadyAttemptBtn)).click();
        System.out.println("✅ Clicked Already Attempt");

        // 🔹 3. Click Analysis
        shortWait.until(ExpectedConditions.elementToBeClickable(analysisLayout)).click();
        System.out.println("✅ Clicked Analysis");

        // 🔹 4. Click Graph
        shortWait.until(ExpectedConditions.elementToBeClickable(graphText)).click();
        System.out.println("✅ Clicked Graph");

        // 🔹 5. Click Compare With Topper
        By compareWithTopper = By.id("com.emedicoz.app:id/compareWithTopperTV");

        shortWait.until(ExpectedConditions.elementToBeClickable(compareWithTopper)).click();
        System.out.println("✅ Clicked Compare With Topper");

        // 🔹 6. Back (1)
        shortWait.until(ExpectedConditions.elementToBeClickable(backBtn)).click();

        // 🔹 7. Back (2)
        shortWait.until(ExpectedConditions.elementToBeClickable(backBtn)).click();
        System.out.println("⬅️ Back to main screen");

        // 🔹 8. Click LeaderBoard
        shortWait.until(ExpectedConditions.elementToBeClickable(leaderBoardLayout)).click();
        System.out.println("✅ Clicked LeaderBoard");

        // 🔹 9. Click Expand List
        shortWait.until(ExpectedConditions.elementToBeClickable(expandList)).click();
        System.out.println("✅ Clicked Expand List");

        // 🔹 10. Scroll full list
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollToEnd(3);"
            ));
        } catch (Exception e) {
            System.out.println("Scroll end not needed");
        }

        // 🔹 11. Back (1)
        shortWait.until(ExpectedConditions.elementToBeClickable(backBtn)).click();

        // 🔹 12. Back (2)
        shortWait.until(ExpectedConditions.elementToBeClickable(backBtn)).click();

        System.out.println("✅ Attempt More Quiz Flow Completed");
    }

    // See All Click Start Quiz


    public static void playQuizFromViewAll() {

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(6));
        Random random = new Random();

        try {
            // =====================================================
            // 🔹 1. Click "View All"
            // =====================================================
            shortWait.until(ExpectedConditions.elementToBeClickable(viewAllQuiz)).click();
            System.out.println("✅ Clicked View All");

            // =====================================================
            // 🔹 2. Click START QUIZ (Card)
            // =====================================================
            shortWait.until(ExpectedConditions.elementToBeClickable(startQuizBtn)).click();
            System.out.println("✅ Clicked Start Quiz Button");

            // =====================================================
            // 🔹 3. Click START (New Screen)
            // =====================================================
            shortWait.until(ExpectedConditions.elementToBeClickable(startQuizTextNew)).click();
            System.out.println("✅ Clicked Start Quiz Text");

        } catch (Exception e) {
            System.out.println("❌ Failed to start quiz");
            return;
        }

        // =====================================================
        // 🔥 4. QUIZ LOOP
        // =====================================================
        for (int i = 0; i < 100; i++) {

            try {
                // 🔹 Wait for options
                shortWait.until(ExpectedConditions.visibilityOfElementLocated(mcqOptions));

                List<WebElement> options = shortWait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(optionItems)
                );

                // 🔹 Select random option
                if (!options.isEmpty()) {
                    int index = random.nextInt(options.size());
                    WebElement option = options.get(index);

                    shortWait.until(ExpectedConditions.elementToBeClickable(option)).click();
                    System.out.println("✅ Selected Option: " + index);
                } else {
                    System.out.println("❌ No options found");
                }

                // 🔹 Small wait for UI stability
                Thread.sleep(500);

                // 🔹 NEXT or FINISH handling
                if (driver.findElements(nextBtn).size() > 0) {

                    shortWait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
                    System.out.println("➡️ Clicked Next");

                } else if (driver.findElements(finishBtn).size() > 0) {

                    shortWait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
                    System.out.println("🏁 Clicked Finish (Last Question)");
                    break;
                }

            } catch (Exception e) {
                System.out.println("❌ Error in quiz loop → breaking");
                break;
            }
        }

        System.out.println("✅ Quiz Flow Completed");
    }

    public static void reviewAttemptedQuizFlow() {

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // 🔹 1. Attempt More Quiz
            shortWait.until(ExpectedConditions.elementToBeClickable(attemptMoreQuizText)).click();
            System.out.println("✅ Clicked Attempt More Quiz");

            // 🔹 2. Already Attempt
            shortWait.until(ExpectedConditions.elementToBeClickable(alreadyAttemptBtn)).click();
            System.out.println("✅ Clicked Already Attempt");

            // 🔹 3. Review Answers
            shortWait.until(ExpectedConditions.elementToBeClickable(reviewAnswersBtn)).click();
            System.out.println("✅ Clicked Review Answers");

        } catch (Exception e) {
            System.out.println("❌ Failed to open Review Screen");
            return;
        }

        // =====================================================
        // 🔥 4. SMART FORWARD NAVIGATION (NEXT)
        // =====================================================
        for (int i = 0; i < 150; i++) {

            try {
                String before = driver.getPageSource();

                if (driver.findElements(nextBtn).size() > 0) {

                    shortWait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
                    System.out.println("➡️ Next Question");

                    Thread.sleep(500);

                    String after = driver.getPageSource();

                    // 🔥 If Next NOT moving → break and go to Previous loop
                    if (before.equals(after)) {
                        System.out.println("⚠️ Next not moving → switching to Previous");
                        break;
                    }

                } else {
                    System.out.println("🏁 Reached Last Question");
                    break;
                }

            } catch (Exception e) {
                System.out.println("❌ Next loop break");
                break;
            }
        }

        // =====================================================
        // 🔥 5. SMART BACKWARD NAVIGATION (PREVIOUS)
        // =====================================================
        for (int i = 0; i < 150; i++) {

            try {
                String before = driver.getPageSource();

                if (driver.findElements(prevBtn).size() > 0) {

                    shortWait.until(ExpectedConditions.elementToBeClickable(prevBtn)).click();
                    System.out.println("⬅️ Previous Question");

                    Thread.sleep(500);

                    String after = driver.getPageSource();

                    // 🔥 Stop when Previous stops changing screen
                    if (before.equals(after)) {
                        System.out.println("🔁 Reached First Question → STOP");
                        break;
                    }

                } else {
                    System.out.println("🔁 No Previous button → STOP");
                    break;
                }

            } catch (Exception e) {
                System.out.println("❌ Previous loop break");
                break;
            }
        }

        System.out.println("✅ Review Flow Completed");
    }

    // Filter Option In Review Page
    public static void applyAllFiltersLoop() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By[] filters = { filterAll, filterCorrect, filterIncorrect, filterSkipped, filterBookmarked };
        String[] names = { "All", "Correct", "Incorrect", "Skipped", "Bookmarked" };

        for (int i = 0; i < filters.length; i++) {

            try {
                wait.until(ExpectedConditions.elementToBeClickable(filterIcon)).click();

                wait.until(ExpectedConditions.elementToBeClickable(filters[i])).click();

                System.out.println("✅ Applied Filter: " + names[i]);

                Thread.sleep(700);

            } catch (Exception e) {
                System.out.println("❌ Failed Filter: " + names[i]);
            }
        }
    }
// Review Page Click List View
    public static void  coverAllQuestionsFromIndex(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        for (int i = 0; i < 100; i++) { // safety loop

            try {
                // 🔹 Open Index
                wait.until(ExpectedConditions.elementToBeClickable(indexIcon)).click();
                System.out.println("📊 Opened Index");

                // 🔹 Get all question numbers
                List<WebElement> questions = wait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(questionIndexNumber)
                );

                if (questions.isEmpty()) {
                    System.out.println("❌ No questions found");
                    break;
                }

                // 🔹 If all covered → break
                if (i >= questions.size()) {
                    System.out.println("🏁 All questions covered");
                    break;
                }

                // 🔹 Click question by index
                WebElement q = questions.get(i);

                String qNo = q.getText();

                wait.until(ExpectedConditions.elementToBeClickable(q)).click();
                System.out.println("✅ Opened Question: " + qNo);

                Thread.sleep(500); // allow UI load

            } catch (Exception e) {
                System.out.println("❌ Error at question index: " + i);
                break;
            }
        }

        System.out.println("✅ All Questions Covered via Index");
    }

    // List View On Review Page

    public static void coverAllQuestionsFromListView() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        for (int i = 0; i < 150; i++) { // safety loop

            try {
                // 🔹 Open List View
                wait.until(ExpectedConditions.elementToBeClickable(listView)).click();
                System.out.println("📋 Opened List View");

                // 🔹 Get all questions
                List<WebElement> questions = wait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(questionIndexNumber)
                );

                if (questions.isEmpty()) {
                    System.out.println("❌ No questions found");
                    break;
                }

                // 🔹 Stop when all covered
                if (i >= questions.size()) {
                    System.out.println("🏁 All questions covered");
                    break;
                }

                // 🔹 Get question number text
                String qNo = questions.get(i).getText();

                // 🔥 Re-locate element (avoid stale element issue)
                By dynamicQuestion = By.xpath(
                        "//android.widget.TextView[@resource-id='com.emedicoz.app:id/myImageViewText' and @text='" + qNo + "']"
                );

                // 🔹 Click question
                wait.until(ExpectedConditions.elementToBeClickable(dynamicQuestion)).click();
                System.out.println("✅ Opened Question: " + qNo);

                Thread.sleep(500); // allow UI to load

            } catch (Exception e) {
                System.out.println("❌ Error at index: " + i);
                break;
            }
        }

        System.out.println("✅ All Questions Covered via List View");
    }
}





