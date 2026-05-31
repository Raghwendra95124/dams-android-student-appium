package Qbank;

import base.baseTest;
import org.testng.annotations.Test;
import pages.CbtPage;
import pages.LoginPage;
import pages.OtpPage;

public class Qbank extends baseTest {
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
        pages.Qbank.selectRandomQBank();
        pages.Qbank.openQbankUntilStartTestFound();
        pages.Qbank.attemptQuestionsUntilFinish();
    }

    // Qbank Flow With Tab Switch  =====================================================================================

    @Test
    public void Qbank2() {
        LoginPage login = new LoginPage(driver);
        login.enterMobileNumber("9910393406");
        login.clickGetOtp();

        OtpPage otp = new OtpPage(driver);
        otp.enterOtp("1", "9", "5", "7");
        otp.closePopupIfPresent();
        CbtPage page = new CbtPage(driver);
        pages.Qbank.selectRandomQBank();
        pages.Qbank.openQbankUntilStartTestFound();
        pages.Qbank.attemptQuestionswithappswitch();
    }
}
