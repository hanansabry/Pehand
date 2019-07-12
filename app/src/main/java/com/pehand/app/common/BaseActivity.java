package com.pehand.app.common;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

    }
}
