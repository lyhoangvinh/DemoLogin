package lyhoangvinh.com.jocky_mvp.utils.crop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * Created by ADMIN on 11/23/2017.
 */

public  class CheckConnectedDialog {
    Activity activity;

    public CheckConnectedDialog(Activity activity) {
        this.activity = activity;
    }

    public void checkConnectedDialogShow(){
        checkConnectedDialog().show();
    }

    public AlertDialog.Builder checkConnectedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false);
        builder.setTitle("No Internet Connection!");
        builder.setMessage("You need to have Mobile Data or Wifi to access this.");
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finish();
            }
        });
        builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("com.android.settings", "com.android.settings.wifi.WifiSettings");
                activity.startActivity(intent);
            }
        });
        return builder;
    }
}
