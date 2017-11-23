package lyhoangvinh.com.jocky_mvp.ui.Home;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import lyhoangvinh.com.jocky_mvp.Constants.Constants;
import lyhoangvinh.com.jocky_mvp.Constants.ConstantsKey;
import lyhoangvinh.com.jocky_mvp.Constants.VolleySingleton;

/**
 * Created by ADMIN on 10/19/2017.
 */

public class ModelHome {

    public void UpdatePicture(Context context, final String token, final String uri, Response.Listener<String> response){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.UPLOAD_PICTURE, response, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(ConstantsKey.REQUEST_TOKEN_KEY, token);
                params.put(ConstantsKey.PICTURE, uri);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void UpdateProfile(Context context, final String token,
                              final String uri, final String fistname,
                              final String lastname,
                              final String mobile,
                              final String pass,
                              Response.Listener<String> response){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.UPLOAD_PROFILE, response, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(ConstantsKey.REQUEST_TOKEN_KEY, token);
                params.put(ConstantsKey.FISTNAME_KEY, fistname);
                params.put(ConstantsKey.LASTNAME_KEY, lastname);
                params.put(ConstantsKey.MOBILE_KEY, mobile);
                params.put(ConstantsKey.PASS_KEY, pass);
                params.put(ConstantsKey.PROFILE_IMAGE, uri);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}
