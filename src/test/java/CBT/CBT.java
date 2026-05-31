package CBT;
import base.baseTest;
import org.testng.annotations.Test;
import pages.*;

import static pages.CbtPage.driver;
import static pages.ExtentManager.extent;

public class CBT extends baseTest{

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


}
