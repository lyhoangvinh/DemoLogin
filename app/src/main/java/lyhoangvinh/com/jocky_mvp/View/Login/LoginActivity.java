package lyhoangvinh.com.jocky_mvp.View.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import lyhoangvinh.com.jocky_mvp.Presenter.Login.LoginPresenter;
import lyhoangvinh.com.jocky_mvp.R;
import lyhoangvinh.com.jocky_mvp.View.Home.HomeActivity;
import lyhoangvinh.com.jocky_mvp.utils.crop.BaseActivity;
import lyhoangvinh.com.jocky_mvp.utils.crop.CheckConnectedDialog;

public class LoginActivity extends BaseActivity implements LoginView {

    private EditText edtEmail, edtPass;
    private Button btnLogin;
    private LoginPresenter presenter;
    private CheckConnectedDialog connectedDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        initEvent();
        checkConnection();
    }

    private void init() {
        edtEmail = (EditText) findViewById(R.id.edtEmailSignIn);
        edtPass = (EditText) findViewById(R.id.edtPassSignIn);
        btnLogin = (Button) findViewById(R.id.btnSignIn);
        presenter = new LoginPresenter(LoginActivity.this, this);
        connectedDialog = new CheckConnectedDialog(this);
    }

    private void initEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myEmail = edtEmail.getText().toString();
                String myPass = edtPass.getText().toString();
                presenter.CallApiLogin(myEmail, myPass);
            }
        });
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
        dismissProgress();
    }

    @Override
    public void loginFailed() {
        showToastLong("Failed Login");
        dismissProgress();
    }

    @Override
    public void showLoading() {
        showProgress("Loading");
    }

    @Override
    public void hideLoading() {
        dismissProgress();
    }

    @Override
    public void noConnectionError() {
        showToastShort("Please check again to connect");
        dismissProgress();
    }

    private void checkConnection() {
        if (!isConnected()) connectedDialog.checkConnectedDialog().show();
    }
}
