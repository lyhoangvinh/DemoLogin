package lyhoangvinh.com.jocky_mvp.ui.Login;

import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import lyhoangvinh.com.jocky_mvp.Constants.Constants;
import lyhoangvinh.com.jocky_mvp.Constants.ConstantsKey;
import lyhoangvinh.com.jocky_mvp.Constants.VolleySingleton;

/**
 * Created by ADMIN on 10/18/2017.
 */

public class ModelLogin {

    public void PerformLoginAPI(Activity context, final String email, final String pass,
                                Response.Listener<String> listener, Response.ErrorListener errorListener){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.LOGIN, listener, errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(ConstantsKey.EMAIL_KEY, email);
                params.put(ConstantsKey.PASS_KEY, pass);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}
