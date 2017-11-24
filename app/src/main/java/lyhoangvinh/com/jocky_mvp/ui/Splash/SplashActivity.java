package lyhoangvinh.com.jocky_mvp.ui.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import lyhoangvinh.com.jocky_mvp.DatabaseHelper.DriverController;
import lyhoangvinh.com.jocky_mvp.R;
import lyhoangvinh.com.jocky_mvp.ui.Home.HomeActivity;
import lyhoangvinh.com.jocky_mvp.ui.Login.LoginActivity;
import lyhoangvinh.com.jocky_mvp.utils.crop.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (!isConnected()){
            checkConnectedDialogShow();
        }else {
            if (DriverController.isLogin(SplashActivity.this)) {
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
}
