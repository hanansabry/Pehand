package com.pehand.app.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pehand.app.R;
import com.pehand.app.common.Constants;
import com.pehand.app.pojos.SubService;
import com.pehand.app.pojos.SubServiceDetails;

public class ServiceOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_order);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        SubServiceDetails subServiceDetails = getIntent().getExtras()
                .getBundle(Constants.SUB_SERVICE_DETAILS_KEY)
                .getParcelable(Constants.SUB_SERVICE_DETAILS_KEY);
        setViewsData(subServiceDetails);
    }

    private void setViewsData(SubServiceDetails details) {
        ImageView servicePhoto = findViewById(R.id.subservice_photo);
        TextView serviceName = findViewById(R.id.subservice_name);
        TextView servicePrice = findViewById(R.id.subservice_price);
        TextView servicePriceNote = findViewById(R.id.subservice_price_note);

        Glide.with(this)
                .load(details.getPhotoName())
                .into(servicePhoto);
        serviceName.setText(details.getSubServiceName());
        servicePrice.setText(details.getCurrentPrice());
        servicePriceNote.setText(details.getPriceNote());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
