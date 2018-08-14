package com.zhixun.mvptest.view.psw;

import com.jungly.gridpasswordview.*;

/**
 * Created by Administrator on 2018/7/24.
 */

 interface PasswordView {
    //void setError(String error);

    String getPassWord();

    void clearPassword();

    void setPassword(String password);

    void setPasswordVisibility(boolean visible);

    void togglePasswordVisibility();

    void setOnPasswordChangedListener(com.jungly.gridpasswordview.GridPasswordView.OnPasswordChangedListener listener);

    void setPasswordType(PasswordType passwordType);
}
