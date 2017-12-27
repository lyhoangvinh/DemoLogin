package lyhoangvinh.com.jocky_mvp.utils.crop;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Point;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ADMIN on 27/12/2017.
 */

public class CommonUtils {
    public CommonUtils() {
    }

    public static Activity getActivityFromContext(@NonNull Context context) {
        while(context instanceof ContextWrapper) {
            if(context instanceof Activity) {
                return (Activity)context;
            }

            context = ((ContextWrapper)context).getBaseContext();
        }

        return null;
    }

    public static Point getScreenSize(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return new Point(dm.widthPixels, dm.heightPixels);
    }

    public static int getStatusBarHeight(Context activity) {
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return resourceId > 0?activity.getResources().getDimensionPixelSize(resourceId):0;
    }

    public static void hideSoftKeyboard(Context context) {
        try {
            Activity activity = getActivityFromContext(context);
            if(activity != null) {
                hideSoftKeyboard(activity);
            }
        } catch (Exception var2) {
            ;
        }

    }

    public static void hideSoftKeyboard(Activity activity) {
        try {
            View view = activity.getCurrentFocus();
            if(view != null) {
                InputMethodManager imm = (InputMethodManager)activity.getSystemService("input_method");
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception var3) {
            ;
        }

    }

    public static void showSoftKeyboard(Context context, EditText edt) {
        edt.setEnabled(true);
        edt.setFocusable(true);
        edt.setFocusableInTouchMode(true);
        edt.requestFocus();
        InputMethodManager imm = (InputMethodManager)context.getSystemService("input_method");
        imm.showSoftInput(edt, 1);
        edt.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, 0.0F, 0.0F, 0));
        edt.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, 0.0F, 0.0F, 0));
    }

    @TargetApi(21)
    public static void changeStatusBarColor(Context activity, int color) {
        if(Build.VERSION.SDK_INT >= 21) {
            Window window = ((Activity)activity).getWindow();
            window.clearFlags(67108864);
            window.addFlags(-2147483648);
            window.setStatusBarColor(color);
        }

    }

    public static String getKeHash(Activity activity) {
        String keyHash = "";

        try {
            PackageInfo info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 64);
            Signature[] var3 = info.signatures;
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Signature signature = var3[var5];
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                keyHash = Base64.encodeToString(md.digest(), 0);
                Log.d("KeyHash", keyHash);
            }
        } catch (PackageManager.NameNotFoundException var8) {
            ;
        } catch (NoSuchAlgorithmException var9) {
            ;
        }

        return keyHash;
    }
}
