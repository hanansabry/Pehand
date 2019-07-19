package com.pehand.app.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import com.pehand.app.R;
import com.pehand.app.common.BaseActivity;

public class ConditionTermsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_terms);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String text = String.format(getString(R.string.conditions_terms_content));
        WebView view = findViewById(R.id.textContent);;
        view.loadData(text, "text/html", "utf-8");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

}
