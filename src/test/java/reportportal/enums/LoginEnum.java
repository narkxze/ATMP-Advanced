package reportportal.enums;

import reportportal.pages.LoginPage;

import java.util.function.Consumer;

import static reportportal.utils.LoginConsumer.*;

public enum LoginEnum {

    VALID_CREDENTIALS(SUCCESSFUL_LOGIN),
    INCORRECT_USERNAME(INVALID_USERNAME_LOGIN),
    INCORRECT_PASSWORD(INVALID_PASSWORD_LOGIN),

    INCORRECT_USERNAME_PASSWORD(INVALID_USERNAME_PASSWORD_LOGIN);


    private final Consumer<LoginPage> loginConsumer;

    LoginEnum(Consumer<LoginPage> loginConsumer) {
        this.loginConsumer = loginConsumer;
    }

    public Consumer<LoginPage> getLoginConsumer() {
        return loginConsumer;
    }


}
