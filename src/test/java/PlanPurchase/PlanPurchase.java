package PlanPurchase;

import base.baseTest;
import org.testng.annotations.Test;
import pages.CbtPage;
import pages.LoginPage;
import pages.OtpPage;
import pages.PlanSubscription;

public class PlanPurchase extends baseTest {
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

}
