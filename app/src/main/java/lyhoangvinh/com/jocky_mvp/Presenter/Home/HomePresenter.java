package lyhoangvinh.com.jocky_mvp.Presenter.Home;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import lyhoangvinh.com.jocky_mvp.Model.Home.ModelHome;
import lyhoangvinh.com.jocky_mvp.View.Home.HomeView;

/**
 * Created by ADMIN on 10/19/2017.
 */

public class HomePresenter {

    Context context;
    HomeView view;
    ModelHome modelHome;

    public HomePresenter(Context context, HomeView view) {
        this.context = context;
        this.view = view;
        modelHome = new ModelHome();
    }

    public void callApiUpdatePicture(String token, String uri){
        modelHome.UpdatePicture(context, token, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
