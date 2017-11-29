package lyhoangvinh.com.jocky_mvp.ui.Login;

import lyhoangvinh.com.jocky_mvp.Model.Driver;

/**
 * Created by ADMIN on 11/29/2017.
 */

public interface LoginCallBack {
    void OnComplete(Driver driver);
    void OnError(Exception ex);
    void OnFailed();
}
