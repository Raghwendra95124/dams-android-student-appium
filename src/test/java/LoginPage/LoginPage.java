package LoginPage;

import base.baseTest;
import org.testng.annotations.Test;
import pages.*;


public class LoginPage extends baseTest {


    // ✅ Test 1 → Login with Mobile + OTP
    @Test
    public void testLoginWithMobileOtp() {

        pages.LoginPage login = new pages.LoginPage(driver);
        login.enterMobileNumber("7300545929");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();

        HomePage home = new HomePage(driver);
        home.goToProfile();
        home.performLogout();
    }

    // ✅ Test 2 → Login with Email + OTP
    @Test
    public void testLoginWithEmailOtp() {

        pages.LoginPage login = new pages.LoginPage(driver);
        login.clickRegisterLogin();
        login.clickLoginWithEmail();
        login.enterEmail("defendmusky@gmail.com");
        login.clickContinueEmail();

        OtpPage otp = new OtpPage(driver);
        otp.loginWithPin("2000");

        HomePage home = new HomePage(driver);
        home.goToProfile();
        home.performLogout();
    }

    // ✅ Test 3 → Login with Mobile + PIN
    @Test
    public void testLoginWithPin() {

        pages.LoginPage login = new pages.LoginPage(driver);
        login.enterMobileNumber("8146714184");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp = new OtpPage(driver);
        otp.loginWithPin("2000");

        HomePage home = new HomePage(driver);
        home.goToProfile();
        home.performLogout();
    }

    // ✅ Test 4 → Partial Registration  with otp
    @Test
    public void PartialRegistrationfill() {
        pages.LoginPage login = new pages.LoginPage(driver);
        login.enterMobileNumber("9999998888");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();

        CourseSelectionPage1 coursePage = new CourseSelectionPage1(driver);

        coursePage.isCoursePageDisplayed();
        coursePage.selectCourseAndProceed();
        if (coursePage.isStateCollegePopupPresent()) {
            coursePage.selectStateCollegeYearAndSave();
        } else {
            System.out.println("State/College popup not present → skipping");
        }
        coursePage.clickCloseIfPresent();
        HomePage home = new HomePage(driver);
        home.goToProfile();
        home.performLogout();
    }
}
