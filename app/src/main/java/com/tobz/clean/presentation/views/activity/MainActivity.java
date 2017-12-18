package com.tobz.clean.presentation.views.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.tobz.clean.R;
import com.tobz.clean.presentation.injector.HasComponent;
import com.tobz.clean.presentation.injector.components.ActivityComponent;
import com.tobz.clean.presentation.views.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements HasComponent<ActivityComponent> {
    @BindView(R.id.message)
    TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       checkUserCredentials();
    }

    /**
     * Verify logged in user.
     */
    private void checkUserCredentials() {
        if(!isUserAlreadyLogin()) {
            this.navigator.navigateToLogin(this);
            finish();
        }
    }

    /**
     * Check if user already login.
     * @return boolean
     */
    private boolean isUserAlreadyLogin() {
        String token = prefUtil.getPrefToken();
        if(!TextUtils.isEmpty(token))
            return true;
        return false;
    }


    /**
     * Call intent in navigator.
     * @param context
     * @return
     */
    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    public ActivityComponent getComponent() {
        return this.getActivityComponent();
    }
}
