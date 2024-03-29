package reportportal.utils;

import reportportal.pages.LoginPage;

import java.util.function.Consumer;

public class LoginConsumer {

    private static final Consumer<LoginPage> VALID_USERNAME = username -> username.enterUsername(System.getenv("VALID_USERNAME"));
    private static final Consumer<LoginPage> INVALID_USERNAME = username -> username.enterUsername("InvalidUsername");
    private static final Consumer<LoginPage> VALID_PASSWORD = password -> password.enterPassword(System.getenv("VALID_PASSWORD"));
    private static final Consumer<LoginPage> INVALID_PASSWORD = password -> password.enterPassword("InvalidPassword");
    private static final Consumer<LoginPage> LOGIN = LoginPage::clickLogin;
    private static final Consumer<LoginPage> LOGIN_EPAM = LoginPage::clickLoginWithEPAM;
    private static final Consumer<LoginPage> USERNAME_EPAM = username -> username.enterEPAMUsername(System.getenv("EPAM_USERNAME"));
    private static final Consumer<LoginPage> PASSWORD_EPAM = password -> password.enterEPAMPassword(System.getenv("EPAM_PASSWORD"));
    private static final Consumer<LoginPage> NEXT = LoginPage::clickNext;
    public static final Consumer<LoginPage> SUCCESSFUL_LOGIN = VALID_USERNAME.andThen(VALID_PASSWORD).andThen(LOGIN);
    public static final Consumer<LoginPage> SUCCESSFUL_LOGIN_EPAM = LOGIN_EPAM.andThen(USERNAME_EPAM).andThen(NEXT).andThen(PASSWORD_EPAM).andThen(NEXT);
    public static final Consumer<LoginPage> INVALID_USERNAME_LOGIN = INVALID_USERNAME.andThen(VALID_PASSWORD).andThen(LOGIN);
    public static final Consumer<LoginPage> INVALID_PASSWORD_LOGIN = VALID_USERNAME.andThen(INVALID_PASSWORD).andThen(LOGIN);
    public static final Consumer<LoginPage> INVALID_USERNAME_PASSWORD_LOGIN = INVALID_USERNAME.andThen(INVALID_PASSWORD).andThen(LOGIN);

    public static void performLogin(Consumer<LoginPage> loginAction, LoginPage loginPage) {
        loginAction.accept(loginPage);
    }


}
