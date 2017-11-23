package lyhoangvinh.com.jocky_mvp.ui.Login;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import lyhoangvinh.com.jocky_mvp.Constants.ConstantsKey;

/**
 * Created by ADMIN on 10/18/2017.
 */

public class LoginPresenter implements ILoginPresenter{
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
    public void CallApiLogin(final String email, String pass) {
        if (view!=null){
            view.showLoading();
            modelLogin.PerformLoginAPI(context, email, pass, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString(ConstantsKey.STATUS_KEY);
                        if (status.equals(ConstantsKey.SUCCESS_KEY)) {
                            view.loginSuccess();
                        }else{
                            view.loginFailed();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    view.noConnectionError();
                    Log.d(TAG, "CallApiLogin onErrorResponse: "+ error.toString());
                }
            });
        }
    }
}
