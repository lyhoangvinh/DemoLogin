package lyhoangvinh.com.jocky_mvp.ui.Login;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import lyhoangvinh.com.jocky_mvp.Constants.ConstantsKey;
import lyhoangvinh.com.jocky_mvp.Model.Driver;

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
    public void CallApiLogin(final String email, String pass) {
        if (view != null) {
            view.showLoading();
            modelLogin.PerformLoginAPI(context, email, pass, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString(ConstantsKey.STATUS_KEY);
                        if (status.equals(ConstantsKey.SUCCESS_KEY)) {
                            JSONObject objectData = jsonObject.getJSONObject(ConstantsKey.DATA_KEY);
                            String firstname = objectData.getString(ConstantsKey.FISTNAME_KEY);
                            String lastname = objectData.getString(ConstantsKey.LASTNAME_KEY);
                            String email = objectData.getString(ConstantsKey.EMAIL_KEY);
                            String mobile = objectData.getString(ConstantsKey.MOBILE_KEY);
                            String picture = objectData.getString(ConstantsKey.PICTURE);
                            String firebaseId = objectData.getString(ConstantsKey.FIREBASE_ID);
                            String role = objectData.getString(ConstantsKey.ROLE);
                            String token = objectData.getString(ConstantsKey.TOKEN_KEY);
                            Driver driver = new Driver(firstname, lastname, email, picture, firebaseId, role, token);
                            driver.save();
                            view.loginSuccess();
                        } else {
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
                    Log.d(TAG, "CallApiLogin onErrorResponse: " + error.toString());
                }
            });
        }
    }
}
