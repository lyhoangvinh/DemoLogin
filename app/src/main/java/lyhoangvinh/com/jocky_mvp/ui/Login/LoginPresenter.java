package lyhoangvinh.com.jocky_mvp.ui.Login;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import lyhoangvinh.com.jocky_mvp.Model.Driver;
import lyhoangvinh.com.jocky_mvp.thread.BackgroundThreadExecutor;
import lyhoangvinh.com.jocky_mvp.thread.UIThreadExecutor;

/**
 * Created by ADMIN on 10/18/2017.
 */

public class LoginPresenter implements ILoginPresenter {
    private static final String TAG = "LoginPresenter";
    LoginView view;
    ModelLogin modelLogin;
    Activity context;

    public LoginPresenter(Activity context, LoginView view) {
        this.view = view;
        this.context = context;
        modelLogin = new ModelLogin();
    }



    @Override
    public void CallApiLogin(String email, String pass) {
        if (view != null) {
            view.showLoading();
            modelLogin.PerformLoginAPI(context, email, pass, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    LoginRunnable loginRunnable = new LoginRunnable(response, new LoginCallBack() {
                        @Override
                        public void OnComplete(final Driver driver) {
                            UIThreadExecutor.getInstance().runOnUIThread(new Runnable() {
                                @Override
                                public void run() {
                                    view.loginSuccess();
                                }
                            });
                        }

                        @Override
                        public void OnError(Exception ex) {
                            Log.d(TAG, "OnError" + ex.getMessage());
                        }

                        @Override
                        public void OnFailed() {
                            UIThreadExecutor.getInstance().runOnUIThread(new Runnable() {
                                @Override
                                public void run() {
                                    view.loginFailed();
                                }
                            });
                        }
                    });
                    BackgroundThreadExecutor.getInstance().runOnBackground(loginRunnable);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    view.noConnectionError();
                    Log.d(TAG, "CallApiLogin onErrorResponse: " + error.getMessage());
                }
            });
        }
    }
}
