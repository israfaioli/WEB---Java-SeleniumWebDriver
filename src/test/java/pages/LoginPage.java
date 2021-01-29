package pages;

import org.junit.Assert;
import org.openqa.selenium.By;

import common.Base;

public class LoginPage extends Base {

    private static final By EMAIL_INPUT = By.id("email");
    private static final By PASSWORD_INPUT = By.id("passwd");
    private static final By SUBMIT_LOGIN_BUTTON = By.id("SubmitLogin");
    private static final By AUTHENTICATION_ERROR_MESSAGE_LABEL = By.cssSelector("#center_column > div.alert.alert-danger > ol > li");
    private static final By USER_INFO_LABEL = By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(2)");
    private static final By LOGOUT_LINK = By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(2) > a");


    public static void visitloginUrl () {
        visitUrl("systemUrl");
    }

    public static void login(String email, String password) {
        visitloginUrl();
        waitElement(EMAIL_INPUT);
        fillInput(EMAIL_INPUT, email);
        fillInput(PASSWORD_INPUT, password);
        waitElement(SUBMIT_LOGIN_BUTTON);
        click(SUBMIT_LOGIN_BUTTON);
    }

    public static void inputLoginCredentials(String email, String password) {
        visitloginUrl();
        waitElement(EMAIL_INPUT);
        fillInput(EMAIL_INPUT, email);
        fillInput(PASSWORD_INPUT, password);
    }

    public static void clickSubmitLogin() {
        waitElement(SUBMIT_LOGIN_BUTTON);
        click(SUBMIT_LOGIN_BUTTON);
    }

    public static void clickLogout() {
        waitElement(USER_INFO_LABEL);
        click(LOGOUT_LINK);
    }

    public static void checkLoginUserName(String userName) {
        By userNameLabel = By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(1) > a > span");
        waitElement(userNameLabel);
        Assert.assertEquals(userName, Base.getText(userNameLabel));
    }

    public static String getErrorMessage() {
        waitElement(AUTHENTICATION_ERROR_MESSAGE_LABEL);
        return getText(AUTHENTICATION_ERROR_MESSAGE_LABEL);
    }
}
