package lyhoangvinh.com.jocky_mvp.utils.crop;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import lyhoangvinh.com.jocky_mvp.R;

/**
 * Created by ADMIN on 11/21/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Context context;
    public ProgressDialog progressDialog;

    protected void showProgress(String msg) {
        if (progressDialog != null && progressDialog.isShowing())
            dismissProgress();

        progressDialog = ProgressDialog.show(this, getResources().getString(R.string.app_name), msg);
    }

    protected void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    protected void showAlert(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.app_name))
                .setMessage(msg)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
    }


    protected boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mobile != null && mobile.isConnectedOrConnecting() ||
                    wifi!=null && wifi.isConnectedOrConnecting()){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

}
