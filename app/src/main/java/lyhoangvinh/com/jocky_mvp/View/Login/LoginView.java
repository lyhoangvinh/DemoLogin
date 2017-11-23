package lyhoangvinh.com.jocky_mvp.View.Login;

/**
 * Created by ADMIN on 10/18/2017.
 */

public interface LoginView {
    void loginSuccess();
    void loginFailed();
    void showLoading();
    void hideLoading();
    void noConnectionError();
}
