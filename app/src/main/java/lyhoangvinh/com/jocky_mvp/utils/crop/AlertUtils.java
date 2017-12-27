package lyhoangvinh.com.jocky_mvp.utils.crop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ADMIN on 27/12/2017.
 */

public class AlertUtils {
    private static final String TAG = "AlertUtils";
    private static String cActionOK = "OK";
    private static String cActionCancel = "Cancel";

    public AlertUtils() {
    }

    public static void initActionText(@NonNull String ok, @NonNull String cancel) {
        cActionOK = ok;
        cActionCancel = cancel;
    }

    public static void showConfirmDialog(Context context, String message, DialogInterface.OnClickListener positiveListener) {
        showConfirmDialog(context, (String)null, message, positiveListener, (DialogInterface.OnClickListener)null);
    }

    public static void showConfirmDialog(Context context, String title, String message, DialogInterface.OnClickListener positiveListener) {
        showConfirmDialog(context, title, message, positiveListener, (DialogInterface.OnClickListener)null);
    }

    public static void showConfirmDialog(Context context, String message, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        showConfirmDialog(context, (String)null, message, positiveListener, negativeListener);
    }

    public static void showConfirmDialog(Context context, @Nullable String title, String message, DialogInterface.OnClickListener positiveListener, @Nullable DialogInterface.OnClickListener negativeListener) {
        showAlertDialog(context, title, message, cActionOK, cActionCancel, positiveListener, negativeListener);
    }

    public static void showAlertDialog(Context context, String message) {
        showAlertDialog(context, (String)null, message, (DialogInterface.OnClickListener)null);
    }

    public static void showAlertDialog(Context context, String title, String message) {
        showAlertDialog(context, title, message, (DialogInterface.OnClickListener)null);
    }

    public static void showAlertDialog(Context context, String title, String message, DialogInterface.OnClickListener listener) {
        showAlertDialog(context, title, message, cActionOK, listener);
    }

    public static void showAlertDialog(Context context, String message, DialogInterface.OnClickListener listener) {
        createAlertDialogBuilderInternal(context, message, cActionOK, listener).show();
    }

    public static void showAlertDialog(Context context, @Nullable String title, String message, String positive, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = createAlertDialogBuilderInternal(context, message, positive, listener);
        if(title != null) {
            builder.setTitle(title);
        }

        builder.show();
    }

    public static void showAlertDialog(Context context, @Nullable String title, @NonNull String message, @NonNull String positive, @Nullable String negative, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = createAlertDialogBuilderInternal(context, message, positive, positiveListener);
        if(title != null) {
            builder.setTitle(title);
        }

        if(negative != null) {
            builder.setNegativeButton(negative, negativeListener);
        }

        builder.show();
    }

    private static AlertDialog.Builder createAlertDialogBuilderInternal(Context context, @NonNull String message, @NonNull String positiveButton, DialogInterface.OnClickListener positiveListener) {
        return (new AlertDialog.Builder(context)).setMessage(message).setPositiveButton(positiveButton, positiveListener).setCancelable(false);
    }

    public static void showToastLongMessage(Context context, String message) {
        if(context != null) {
            Toast.makeText(context, message, 1).show();
        }

    }

    public static void showToastShortMessage(Context context, String message) {
        if(context != null) {
            Toast.makeText(context, message, 0).show();
        }

    }

    private static void showSnackBarInternal(View layout, String message, @Nullable String actionText, @Nullable android.view.View.OnClickListener actionClick, int duration) {
        if(layout != null && !TextUtils.isEmpty(message)) {
            CommonUtils.hideSoftKeyboard(layout.getContext());
            Snackbar snackbar = Snackbar.make(layout, message, duration);
            if(actionText != null && actionClick != null) {
                snackbar.setAction(actionText, actionClick);
            }

            snackbar.show();
        }

    }

    public static void showSnackBarLongMessage(View layout, String message, String actionText, android.view.View.OnClickListener actionClick) {
        showSnackBarInternal(layout, message, actionText, actionClick, 0);
    }

    public static void showSnackBarShortMessage(View layout, String message, String actionText, android.view.View.OnClickListener actionClick) {
        showSnackBarInternal(layout, message, actionText, actionClick, -1);
    }

    public static void showSnackBarLongMessage(View layout, String message) {
        showSnackBarInternal(layout, message, cActionOK, (android.view.View.OnClickListener)null, 0);
    }

    public static void showSnackBarShortMessage(View layout, String message) {
        showSnackBarInternal(layout, message, cActionOK, (android.view.View.OnClickListener)null, -1);
    }
}

