package lyhoangvinh.com.jocky_mvp.ui.Login;

import org.json.JSONException;
import org.json.JSONObject;

import lyhoangvinh.com.jocky_mvp.Constants.ConstantsKey;
import lyhoangvinh.com.jocky_mvp.Model.Driver;

/**
 * Created by ADMIN on 11/29/2017.
 */

public class LoginRunnable implements Runnable {

    LoginCallBack loginCallBack;
    String response;

    public LoginRunnable(String response, LoginCallBack loginCallBack) {
        this.loginCallBack = loginCallBack;
        this.response = response;
    }

    @Override
    public void run() {
        if (loginCallBack!=null){
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
                    loginCallBack.OnComplete(driver);
                    driver.save();

                } else {
                    loginCallBack.OnFailed();
                }

            } catch (JSONException e) {
                loginCallBack.OnError(e);
                e.printStackTrace();
            }
        }
    }
}
