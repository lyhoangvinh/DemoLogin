package lyhoangvinh.com.jocky_mvp.ui.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import lyhoangvinh.com.jocky_mvp.DatabaseHelper.DriverController;
import lyhoangvinh.com.jocky_mvp.Model.Driver;
import lyhoangvinh.com.jocky_mvp.R;
import lyhoangvinh.com.jocky_mvp.ui.Home.HomeActivity;
import lyhoangvinh.com.jocky_mvp.ui.Login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (DriverController.isLogin(SplashActivity.this)) {
            Driver driver = DriverController.getCurrentDriver(this);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 500);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 500);
        }
    }
}
