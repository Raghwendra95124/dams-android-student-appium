package tests;

import base.baseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class LoginTest extends baseTest {

    // ✅ Test 1 → Login with Mobile + OTP
    @Test
    public void testLoginWithMobileOtp() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("7300545929");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();

        HomePage home = new HomePage(driver);
        home.goToProfile();
        home.performLogout();
        Assert.assertTrue(login.isLoginScreenVisible(), "Login screen should be visible after logout");
    }

    // ✅ Test 2 → Login with Email + OTP
    @Test
    public void testLoginWithEmailOtp() {

        LoginPage login = new LoginPage(driver);
        login.clickRegisterLogin();
        login.clickLoginWithEmail();
        login.enterEmail("defendmusky@gmail.com");
        login.clickContinueEmail();

        OtpPage otp = new OtpPage(driver);
        otp.loginWithPin("2000");

        HomePage home = new HomePage(driver);
        home.goToProfile();
        home.performLogout();
        Assert.assertTrue(login.isLoginScreenVisible(), "Login screen should be visible after logout");
    }

    // ✅ Test 3 → Login with Mobile + PIN
    @Test
    public void testLoginWithPin() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("8146714184");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp = new OtpPage(driver);
        otp.loginWithPin("2000");

        HomePage home = new HomePage(driver);
        home.goToProfile();
        home.performLogout();
        Assert.assertTrue(login.isLoginScreenVisible(), "Login screen should be visible after logout");
    }

    // ✅ Test 4 → Partial Registration  with otp
    @Test
    public void PartialRegistrationfill() {
        LoginPage login = new LoginPage(driver);
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
        Assert.assertTrue(login.isLoginScreenVisible(), "Login screen should be visible after logout");
    }

    // CBT Purchase Flow  With RazorPay WithOut Registration -----------------------
    @Test
    public void testCBTFlow() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("8146714184");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();

        CbtPage cbt = new CbtPage(driver);
        cbt.completeCBTFlow();
        cbt.completePaymentFlow();
        cbt.clickTryAgain();

    }

    // CBT Purchase Flow  With Paytm WithOut Registration-------
    @Test
    public void testCBTWithPaytm() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("8146714184");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();

        CbtPage cbt = new CbtPage(driver);
        cbt.completeCBTFlow();
        cbt.completePaytmFlow();
        cbt.clickTryAgain();
    }
    @Test
    public void testCBTWithRegistration() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("4000000012");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();

        CbtPage cbt = new CbtPage(driver);
        cbt.completeCBTFlow();
        cbt.completePaytmFlow();
        cbt.clickTryAgain();
    }


    // Daily Quiz Attempt From Home Page

    @Test
    public void testdailyquiz() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("8146714184");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();
        CbtPage page = new CbtPage(driver);   // ✅ correct
        DailyQuiz.selectNeetPgMbbsCourse();
        DailyQuiz.playQuizTillFinish();
    }
    // Already Attempt On Home Screen
    @Test
    public void AllreadyAttempt() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("8146714184");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();
        CbtPage page = new CbtPage(driver);   // ✅ correct
        DailyQuiz.selectNeetPgMbbsCourse();
        DailyQuiz.handleAttemptMoreQuizFlow();
    }

    // View All Start Quiz
    @Test
    public void Viewallquiz() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("9000120004");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();
        CbtPage page = new CbtPage(driver);
        DailyQuiz.playQuizFromViewAll();
    }

    // View All Allready Attempt

    @Test
    public void AllreadyAttemptView() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("9000120004");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();
        CbtPage page = new CbtPage(driver);
        DailyQuiz.reviewAttemptedQuizFlow() ;
        DailyQuiz.applyAllFiltersLoop();
    }

    // Review Page Index Page Selecting
    @Test
    public void IndexViewquestion() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("9000120004");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();
        CbtPage page = new CbtPage(driver);
        DailyQuiz.reviewAttemptedQuizFlow();
        DailyQuiz.coverAllQuestionsFromIndex();
        DailyQuiz.coverAllQuestionsFromListView();
    }


    // Purchase Plan Subscription With Paytm

    @Test
    public void PurchasePlan() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("8146714184");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();
        CbtPage page = new CbtPage(driver);
        PlanSubscription.subscribeFlow();
        PlanSubscription.completePaytmFlow();
    }

    // Purchase Plan Subscription With Razorpay

    @Test
    public void PurchasePlanRazorPay() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("8146714184");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();
        CbtPage page = new CbtPage(driver);
        PlanSubscription.subscribeFlow();
        PlanSubscription.completeRazorPayFlow();
        PlanSubscription.clickTryAgain();
    }

    // Select Feature Date New user With Razorpay

    @Test
    public void SelectFeaturedate() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("8146714184");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();
        CbtPage page = new CbtPage(driver);
        PlanSubscription.SelectFeaturedate();
        PlanSubscription.completeRazorPayFlow();
        PlanSubscription.clickTryAgain();
    }

    // Select Feature Date New user With Paytm
    @Test
    public void SelectFeaturedatewitpaytm() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("8146714184");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();
        CbtPage page = new CbtPage(driver);
        PlanSubscription.SelectFeaturedate();
        PlanSubscription.completePaytmFlow();
        PlanSubscription.clickTryAgain();
    }

    // Cross Sale =====================================================
    @Test
    public void SelectFeaturedatewithCrosssale() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("8146714184");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();
        CbtPage page = new CbtPage(driver);
        PlanSubscription.SelectFeaturedatewithcrosssale();
        PlanSubscription.completePaytmFlow();
        PlanSubscription.clickTryAgain();
    }

// Add All Cross Sale Product In Cart
    @Test
    public void SelectFeaturedatewithCrosssaleAddallproductsincart() {

        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("8146714184");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();
        CbtPage page = new CbtPage(driver);
        PlanSubscription.SelectFeaturedatewithcrosssale2();
        PlanSubscription.completePaytmFlow();
        PlanSubscription.clickTryAgain();
    }
// Qbank Flow =====================================================================================
      @Test
      public void Qbank() {

    LoginPage login = new LoginPage(driver);
    login.enterMobileNumber("9910393406");
    login.clickGetOtp();

    OtpPage otp = new OtpPage(driver);
    otp.enterOtp("1", "9", "5", "7");
    otp.closePopupIfPresent();
    CbtPage page = new CbtPage(driver);
    Qbank.selectRandomQBank();
    Qbank.openQbankUntilStartTestFound();
    Qbank.attemptQuestionsUntilFinish();
 }



// Test Series =========================================================================================
@Test
public void TestSeries() {

    LoginPage login = new LoginPage(driver);
    login.enterMobileNumber("8146714184");
    login.clickGetOtp();

    OtpPage otp = new OtpPage(driver);
    otp.enterOtp("1", "9", "5", "7");
    otp.closePopupIfPresent();
    CbtPage page = new CbtPage(driver);
    TestSeries.openTestSeriesCourse();
    TestSeries.scrollAndClickPlanName(
            "Test Series Course (Only used for Testing)");
    TestSeries.slowScrollAndClickTestSeries(
            "FMGE 2025 (Jan)NOT FOR SALE)");
    TestSeries.startTestAndSelectRandomAnswers();

}
// Test From Home Page
@Test
public void TestSeriesFromHomePage() {

    LoginPage login = new LoginPage(driver);
    login.enterMobileNumber("8146714184");
    login.clickGetOtp();

    OtpPage otp = new OtpPage(driver);
    otp.enterOtp("1", "9", "5", "7");
    otp.closePopupIfPresent();
    CbtPage page = new CbtPage(driver);
    TestSeries.clickCategory("Test");
    TestSeries.slowScrollAndClickTestSeries(
            "Grand Test-54 (5 Sections)");
    TestSeries.ClickQuestionList() ;

}
}








