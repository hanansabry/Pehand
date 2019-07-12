package com.pehand.app.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pehand.app.R;
import com.pehand.app.common.Constants;
import com.pehand.app.pojos.SubService;

public class ServiceDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSubService();

        TextView desc = findViewById(R.id.subservice_desc);
        String text = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            desc.setText(Html.fromHtml("تركيب جميع انواع التكييفات شارب وكارير ويونيون اير وباور وجرى والتكييف الصحراوى وباقى انواع التكييف\\r\\n</br>\\r\\n</br>- ملحوظة:\\r\\n</br>- التركيب غير شاملة تكلفة الحامل\\r\\n</br>- الاسعار غير شاملة تكسير لتركيب تكييف ال window\\r\\n</br>- السعر غير شامل قطع غيار\\r\\n</br>- السعر لوحدة تكييف واحدة فقط", Html.FROM_HTML_MODE_COMPACT));
        } else {
            desc.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
        }

        Button orderNow = findViewById(R.id.order_now);
        orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServiceDetailsActivity.this, ServiceOrderActivity.class));
            }
        });
    }

    private void getSubService() {
        if (getIntent().getExtras() != null) {
            int id = getIntent().getExtras().getInt(Constants.SERVICE_ID);
            String name = getIntent().getExtras().getString(Constants.SERVICE_NAME);
            setTitle(name);
        } else {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
