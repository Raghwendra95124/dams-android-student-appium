package CrossSale;

import base.baseTest;
import org.testng.annotations.Test;
import pages.CbtPage;
import pages.LoginPage;
import pages.OtpPage;
import pages.PlanSubscription;

public class CrossSale extends baseTest {
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
}
