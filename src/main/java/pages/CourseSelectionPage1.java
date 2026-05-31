package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class CourseSelectionPage1 extends BasePage {

    public CourseSelectionPage1(AndroidDriver driver) {
        super(driver);
    }

    // 🔹 Locators
    private final By recyclerCourse = By.id("com.emedicoz.app:id/recyclerCourse");
    private final By parentCourse = By.id("com.emedicoz.app:id/parent");

    private final By doneBtn = By.id("com.emedicoz.app:id/nextBtn");

    private final By popupClose = By.id("com.emedicoz.app:id/ivClose");
    private final By dialogClose = By.id("com.emedicoz.app:id/imgCloseDialog");

    private final By stateDropdown = By.id("com.emedicoz.app:id/tilIndianState");
    private final By collegeDropdown = By.id("com.emedicoz.app:id/tilIndianCollege");
    private final By yearDropdown = By.id("com.emedicoz.app:id/tilAddMissionYear");

    private final By listItems = By.xpath("//android.widget.ListView//android.widget.TextView");

    private final By saveBtn = By.id("com.emedicoz.app:id/saveBtn");
    private final By btnClose = By.id("com.emedicoz.app:id/btnClose");

    public boolean isCoursePageDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(recyclerCourse)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectCourseAndProceed() {
        if (!isCoursePageDisplayed()) {
            System.out.println("Course page not displayed");
            return;
        }

        List<WebElement> courses = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(parentCourse)
        );

        if (courses.size() == 0) {
            throw new RuntimeException("No courses available");
        }

        int index = new Random().nextInt(courses.size());
        WebElement course = courses.get(index);

        wait.until(ExpectedConditions.elementToBeClickable(course)).click();

        // Wait for Done button to be enabled
        wait.until(ExpectedConditions.elementToBeClickable(doneBtn)).click();
    }

    public void handlePopupIfPresent() {

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement popup = shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(popupClose)
            );

            if (popup.isDisplayed()) {
                popup.click();
                System.out.println("Popup closed");
            }

        } catch (Exception e) {
            System.out.println("Popup not displayed, continuing...");
        }
    }

    public void selectStateCollegeYearAndSave() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Random random = new Random();

        // 🔹 1. Select Random State
        wait.until(ExpectedConditions.elementToBeClickable(stateDropdown)).click();

        List<WebElement> states = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(listItems)
        );

        if (states.isEmpty()) {
            throw new RuntimeException("No states found");
        }

        states.get(random.nextInt(states.size())).click();

        // ✅ WAIT after state selection (IMPORTANT)
        wait.until(ExpectedConditions.visibilityOfElementLocated(collegeDropdown));


        // 🔹 2. Select Random College
        wait.until(ExpectedConditions.elementToBeClickable(collegeDropdown)).click();

        List<WebElement> colleges = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(listItems)
        );

        if (colleges.isEmpty()) {
            throw new RuntimeException("No colleges found");
        }

        colleges.get(random.nextInt(colleges.size())).click();

        // ✅ WAIT after college selection
        wait.until(ExpectedConditions.visibilityOfElementLocated(yearDropdown));


        // 🔹 3. Select Random Year
        wait.until(ExpectedConditions.elementToBeClickable(yearDropdown)).click();

        List<WebElement> years = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(listItems)
        );

        if (years.isEmpty()) {
            throw new RuntimeException("No years found");
        }

        years.get(random.nextInt(years.size())).click();


        // 🔹 4. Click Save
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }

    public void clickDialogIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            WebElement dialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(dialogClose)
            );

            assert dialog != null;
            dialog.click();

        } catch (TimeoutException e) {
            // popup not present → ignore
        }
    }

    public boolean isStateCollegePopupPresent() {
        return driver.findElements(stateDropdown).size() > 0;
    }

    public void clickCloseIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            WebElement closeBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(btnClose)
            );

            closeBtn.click();
            System.out.println("Close button clicked");

        } catch (TimeoutException e) {
            System.out.println("Close button not present → skipping");
        }
    }
}




class courseSelectionPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    Random random = new Random();

    public courseSelectionPage(AndroidDriver driver) {
        super(driver);
    }

    // 🔹 LOCATORS (MUST BE INSIDE CLASS)
    private final By recyclerCourse = By.id("com.emedicoz.app:id/recyclerCourse");
    private final By parentCourse = By.id("com.emedicoz.app:id/parent");
    private final By doneBtn = By.id("com.emedicoz.app:id/nextBtn");

    public void selectRandomCourse() {
        List<WebElement> courses = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(parentCourse)
        );

        if (courses.isEmpty()) {
            throw new RuntimeException("No courses found");
        }

        courses.get(random.nextInt(courses.size())).click();
    }

    public void clickDoneButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(doneBtn)
        ).click();
    }


}
