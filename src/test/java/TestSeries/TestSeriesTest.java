package TestSeries;

import base.baseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CbtPage;
import pages.LoginPage;
import pages.OtpPage;
import pages.TestSeries;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class TestSeriesTest extends baseTest {

    private WebDriverWait wait;
    private Random random;

    @Test(priority = 1)
    public void TestSeriesMainWorkflow() {
        initializeTest();
        performLogin();
        navigateToTestSeries();
        executeTestWorkflow();
    }

    private void initializeTest() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        random = new Random();
    }

    private void performLogin() {
        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("8654865455");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();
        new CbtPage(driver);
    }

    private void navigateToTestSeries() {
        TestSeries.openTestSeriesCourse();
        TestSeries.scrollAndClickPlanName("Test Series Course (Only used for Testing)");
        TestSeries.slowScrollAndClickTestSeries("FMGE 2025 Jan NOT FOR SALE");

        WebElement startBtn = wait.until(ExpectedConditions.elementToBeClickable(TestSeries.startTestButton));
        startBtn.click();
        System.out.println("Test started successfully.");
    }

    private void executeTestWorkflow() {
        boolean nextSpamScenarioValidated = false;
        boolean previousSpamScenarioValidated = false;

        while (true) {
            try {
                if (isTestCompleted()) break;

                String currentSection = getCurrentSectionName();
                String timerText = getTimerText();
                int remainingSeconds = getRemainingSeconds(timerText);

                logSectionInfo(currentSection, timerText, remainingSeconds);

                if (shouldExecuteNextSpam(nextSpamScenarioValidated, currentSection, remainingSeconds)) {
                    executeNextSpamScenario(currentSection);
                    nextSpamScenarioValidated = true;
                    continue;
                }

                if (shouldExecutePreviousSpam(previousSpamScenarioValidated, currentSection, remainingSeconds)) {
                    executePreviousSpamScenario(currentSection);
                    previousSpamScenarioValidated = true;
                    continue;
                }

                selectRandomOption(currentSection, timerText);
                clickNextButton(remainingSeconds, currentSection);

            } catch (Exception e) {
                if (e instanceof StaleElementReferenceException) {
                    System.out.println("Recoverable stale element in workflow loop, retrying next iteration.");
                    continue;
                }
                handleFlowException(e);
                Assert.fail("Critical exception in test workflow", e);
            }
        }
        System.out.println("Automation flow processed.");
    }

    private boolean isTestCompleted() {
        List<WebElement> submitBtns = driver.findElements(TestSeries.submitTestBtn);
        if (!submitBtns.isEmpty() && submitBtns.get(0).isDisplayed()) {
            System.out.println("Test completed. Submit button is visible.");
            return true;
        }
        return false;
    }

    private String getCurrentSectionName() {
        try {
            WebElement section = driver.findElement(TestSeries.textSpinner);
            String sectionName = section.getText();
            return sectionName == null ? "N/A" : sectionName.trim();
        } catch (Exception ignored) {
            return "N/A";
        }
    }

    private String getTimerText() {
        String timerText = "";

        try {
            WebElement timerEle = driver.findElement(TestSeries.tvTimer);
            timerText = timerEle.getAttribute("text");
            if (timerText == null || timerText.trim().isEmpty()) {
                timerText = timerEle.getText();
            }
        } catch (Exception ignored) {}

        timerText = timerText == null ? "" : timerText.trim();
        if (TestSeries.isUsefulTimerText(timerText)) {
            return timerText;
        }

        String fallbackTimerText = getNonZeroTimerFromVisibleTextViews();
        return !fallbackTimerText.isEmpty() ? fallbackTimerText :
                (timerText.isEmpty() ? "TimerNotReady" : timerText);
    }

    private String getNonZeroTimerFromVisibleTextViews() {
        try {
            List<WebElement> textViewsWithColon = driver.findElements(
                    By.xpath("//android.widget.TextView[contains(@text, ':')]")
            );

            for (WebElement textView : textViewsWithColon) {
                String candidate = textView.getAttribute("text");
                if (candidate == null || candidate.trim().isEmpty()) {
                    candidate = textView.getText();
                }

                candidate = candidate == null ? "" : candidate.trim();
                if (TestSeries.isUsefulTimerText(candidate)) {
                    System.out.println("Timer fallback picked visible text: " + candidate);
                    return candidate;
                }
            }
        } catch (Exception ignored) {}
        return "";
    }

    private int getRemainingSeconds(String timerText) {
        if (timerText == null || !timerText.contains(":")) {
            return -1;
        }

        try {
            String[] timeParts = timerText.split(":");
            if (timeParts.length == 3) {
                int hours = Integer.parseInt(timeParts[0].trim());
                int minutes = Integer.parseInt(timeParts[1].trim());
                int seconds = Integer.parseInt(timeParts[2].trim());
                return (hours * 3600) + (minutes * 60) + seconds;
            }

            if (timeParts.length == 2) {
                int minutes = Integer.parseInt(timeParts[0].trim());
                int seconds = Integer.parseInt(timeParts[1].trim());
                return (minutes * 60) + seconds;
            }
        } catch (Exception ignored) {}
        return -1;
    }

    private void logSectionInfo(String currentSection, String timerText, int remainingSeconds) {
        if (isSectionA(currentSection)) {
            System.out.println("Section A tracked | seconds: " + remainingSeconds + " | timer: " + timerText);
        }
        if (isSectionB(currentSection)) {
            System.out.println("Section B tracked | seconds: " + remainingSeconds + " | timer: " + timerText);
        }
    }

    private boolean shouldExecuteNextSpam(boolean alreadyValidated, String currentSection, int remainingSeconds) {
        return !alreadyValidated && isSectionA(currentSection) && isSpamTriggerTime(remainingSeconds);
    }

    private boolean shouldExecutePreviousSpam(boolean alreadyValidated, String currentSection, int remainingSeconds) {
        return !alreadyValidated && isSectionB(currentSection) && isSpamTriggerTime(remainingSeconds);
    }

    private void executeNextSpamScenario(String currentSection) {
        System.out.println("[Critical range matched] Section A timer is between 00:00:20 and 00:00:10. Starting Next click loop.");

        int clickCount = clickNextUntilSectionTimerEnds(currentSection);
        System.out.println("Next click loop completed. Total clicks: " + clickCount);

        waitForSectionChange();
        String sectionAfterSpam = getCurrentSectionName();
        System.out.println("Active section after Section A timer transition: " + sectionAfterSpam);

        Assert.assertTrue(isSectionB(sectionAfterSpam),
                "TEST CASE FAILED: Section A timer completed, but app did not move to Section B. Before: " +
                        currentSection + " | After: " + sectionAfterSpam);

        System.out.println("TEST CASE PASSED: App moved to Section B after Next spam.");
    }

    private void executePreviousSpamScenario(String currentSection) {
        System.out.println("[Critical range matched] Section B timer is between 00:00:20 and 00:00:10. Starting Previous click loop.");

        int clickCount = clickPreviousUntilSectionTimerEnds(currentSection);
        System.out.println("Previous click loop completed. Total clicks: " + clickCount);

        waitForSectionChange();
        String sectionAfterSpam = getCurrentSectionName();
        System.out.println("Active section after timer transition: " + sectionAfterSpam);

        Assert.assertTrue(isExpectedNextSectionAfterSectionB(sectionAfterSpam),
                "TEST CASE FAILED: Section B timer completed, but user did not move to the next section. Before: " +
                        currentSection + " | After: " + sectionAfterSpam);

        System.out.println("TEST CASE PASSED: App moved to the next section after Previous spam.");
    }

    private void waitForSectionChange() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void selectRandomOption(String currentSection, String timerText) {
        for (int attempt = 1; attempt <= 2; attempt++) {
            try {
                List<WebElement> options = driver.findElements(TestSeries.optionIconTV);
                if (options.isEmpty()) {
                    return;
                }

                int optionIndex = random.nextInt(options.size());
                WebElement selectedOption = options.get(optionIndex);
                selectedOption.click();
                System.out.println("[" + currentSection + "] [" + timerText + "] Option selected dynamically.");
                return;
            } catch (StaleElementReferenceException stale) {
                System.out.println("Option list became stale (attempt " + attempt + "), refetching.");
            }
        }
        System.out.println("Skipping option click due to repeated stale option references.");
    }

    private void clickNextButton(int remainingSeconds, String currentSection) {
        try {
            WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(TestSeries.btnNext));
            nextBtn.click();

            if (isSectionB(currentSection) && remainingSeconds <= 35 && remainingSeconds > 0) {
                Thread.sleep(250);
            } else {
                Thread.sleep(800);
            }
        } catch (Exception e) {
            System.out.println("Failed to click Next button: " + e.getMessage());
        }
    }

    private int clickNextUntilSectionTimerEnds(String sectionAtSpamTime) {
        long maxSpamDuration = System.currentTimeMillis() + Duration.ofSeconds(30).toMillis();
        int clickCount = 0;

        while (System.currentTimeMillis() < maxSpamDuration) {
            String activeSection = getCurrentSectionName();
            String activeTimerText = getTimerText();
            int activeRemainingSeconds = getRemainingSeconds(activeTimerText);

            if (!activeSection.equalsIgnoreCase(sectionAtSpamTime)) {
                System.out.println("Section changed during Next click loop. Before: " + sectionAtSpamTime + " | Now: " + activeSection);
                break;
            }

            if (activeRemainingSeconds == 0) {
                System.out.println("Section timer reached 00:00:00 while Next click loop was active.");
                break;
            }

            if (clickNextButtonDirectly()) {
                clickCount++;
                System.out.println("[Next click " + clickCount + "] Timer: " + activeTimerText);
            }
        }
        return clickCount;
    }

    private boolean clickNextButtonDirectly() {
        try {
            List<WebElement> nextButtons = driver.findElements(TestSeries.btnNext);
            if (!nextButtons.isEmpty()) {
                WebElement nextButton = nextButtons.get(0);
                if (nextButton.isDisplayed() && nextButton.isEnabled()) {
                    nextButton.click();
                    return true;
                }
            }
        } catch (Exception ignored) {}
        return false;
    }

    private int clickPreviousUntilSectionTimerEnds(String sectionAtSpamTime) {
        long maxSpamDuration = System.currentTimeMillis() + Duration.ofSeconds(30).toMillis();
        int clickCount = 0;

        while (System.currentTimeMillis() < maxSpamDuration) {
            String activeSection = getCurrentSectionName();
            String activeTimerText = getTimerText();
            int activeRemainingSeconds = getRemainingSeconds(activeTimerText);

            if (!activeSection.equalsIgnoreCase(sectionAtSpamTime)) {
                System.out.println("Section changed during Previous click loop. Before: " + sectionAtSpamTime + " | Now: " + activeSection);
                break;
            }

            if (activeRemainingSeconds == 0) {
                System.out.println("Section timer reached 00:00:00 while Previous click loop was active.");
                break;
            }

            if (clickPreviousButtonDirectly()) {
                clickCount++;
                System.out.println("[Previous click " + clickCount + "] Timer: " + activeTimerText);
            }
        }
        return clickCount;
    }

    private boolean clickPreviousButtonDirectly() {
        By[] previousLocators = {
                TestSeries.btnPrevious,
                TestSeries.btnPrev,
                TestSeries.previousText,
                By.xpath("//*[contains(@text, 'Previous') or contains(@content-desc, 'Previous')]")
        };

        for (By locator : previousLocators) {
            try {
                List<WebElement> previousButtons = driver.findElements(locator);
                if (!previousButtons.isEmpty()) {
                    WebElement previousButton = previousButtons.get(0);
                    if (previousButton.isDisplayed() && previousButton.isEnabled()) {
                        previousButton.click();
                        System.out.println("Previous clicked using locator: " + locator);
                        return true;
                    }
                }
            } catch (Exception ignored) {}
        }
        return false;
    }

    private boolean isSectionA(String sectionName) {
        String normalizedSectionName = normalize(sectionName);
        return normalizedSectionName.contains("sec a")
                || normalizedSectionName.contains("section a")
                || normalizedSectionName.contains("sec 1")
                || normalizedSectionName.contains("section 1");
    }

    private boolean isSectionB(String sectionName) {
        String normalizedSectionName = normalize(sectionName);
        return normalizedSectionName.contains("sec b")
                || normalizedSectionName.contains("section b")
                || normalizedSectionName.contains("sec 2")
                || normalizedSectionName.contains("section 2")
                || normalizedSectionName.contains("grand test-54");
    }

    private boolean isExpectedNextSectionAfterSectionB(String sectionAfterSpam) {
        String after = normalize(sectionAfterSpam);
        return after.contains("sec c")
                || after.contains("section c")
                || after.contains("sec 3")
                || after.contains("section 3")
                || after.contains("part c")
                || after.contains("part 3");
    }

    private boolean isSpamTriggerTime(int remainingSeconds) {
        return remainingSeconds >= 10 && remainingSeconds <= 20;
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase();
    }

    private void handleFlowException(Exception e) {
        System.out.println("Flow capture event: " + e.getMessage());
        try {
            Thread.sleep(3000);
            List<WebElement> postSubmitCheck = driver.findElements(TestSeries.submitTestBtn);
            if (!postSubmitCheck.isEmpty() && postSubmitCheck.get(0).isDisplayed()) {
                System.out.println("Exception occurred after submit became visible.");
                return;
            }
            if (postSubmitCheck.isEmpty()) {
                System.out.println("Test fully auto-submitted.");
            }
        } catch (Exception ignored) {}
    }
}
