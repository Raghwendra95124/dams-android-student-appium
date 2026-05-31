package DailyQuiz;

import base.baseTest;
import org.testng.annotations.Test;
import pages.CbtPage;
import pages.LoginPage;
import pages.OtpPage;

import static pages.CbtPage.driver;
import static pages.ExtentManager.extent;

public class DailyQuiz  extends baseTest {

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
        pages.DailyQuiz.selectNeetPgMbbsCourse();
        pages.DailyQuiz.playQuizTillFinish();
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
        pages.DailyQuiz.selectNeetPgMbbsCourse();
        pages.DailyQuiz.handleAttemptMoreQuizFlow();
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
        pages.DailyQuiz.playQuizFromViewAll();
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
        pages.DailyQuiz.reviewAttemptedQuizFlow() ;
        pages.DailyQuiz.applyAllFiltersLoop();
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
        pages.DailyQuiz.reviewAttemptedQuizFlow();
        pages.DailyQuiz.coverAllQuestionsFromIndex();
        pages.DailyQuiz.coverAllQuestionsFromListView();
    }
}
