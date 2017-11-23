package lyhoangvinh.com.jocky_mvp.DatabaseHelper;

import android.content.Context;

import java.util.List;

import lyhoangvinh.com.jocky_mvp.Model.Driver;

/**
 * Created by ADMIN on 11/23/2017.
 */

public class DriverController {

    public static boolean isLogin(Context context) {
        List<Driver> list = Driver.listAll(Driver.class);
        if (list.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static Driver getCurrentDriver(Context context) {
        List<Driver> list = Driver.listAll(Driver.class);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public static void clearAll(Context context) {
        Driver.deleteAll(Driver.class);
    }

}
