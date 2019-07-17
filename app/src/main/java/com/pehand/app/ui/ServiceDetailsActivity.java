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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pehand.app.R;
import com.pehand.app.backend.services.ServiceRepositoryImpl;
import com.pehand.app.backend.services.ServicesRepository;
import com.pehand.app.common.Constants;
import com.pehand.app.pojos.SubServiceDetails;

public class ServiceDetailsActivity extends AppCompatActivity implements ServicesRepository.SubServiceDetailsCallback {

    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        getSubServiceDetails();
        continueButton = findViewById(R.id.for_continue);
    }

    private void getSubServiceDetails() {
        int id = getIntent().getExtras().getInt(Constants.SERVICE_ID);
        String name = getIntent().getExtras().getString(Constants.SERVICE_NAME);
        setTitle(name);

        ServicesRepository servicesRepository = new ServiceRepositoryImpl();
        servicesRepository.getSubServiceDetailsById(id, this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onSuccess(final SubServiceDetails subServiceDetails) {
        ImageView photoImageView = findViewById(R.id.subservice_photo);
        TextView descTextView = findViewById(R.id.subservice_desc);

        Glide.with(this)
                .load(subServiceDetails.getPhotoName())
                .into(photoImageView);
        String descText = subServiceDetails.getDescription().replace("</br>", "\n");
        descTextView.setText(descText);

        continueButton.setEnabled(true);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putParcelable(Constants.SUB_SERVICE_DETAILS_KEY, subServiceDetails);
                Intent intent = new Intent(ServiceDetailsActivity.this, ServiceOrderActivity.class);
                intent.putExtra(Constants.SUB_SERVICE_DETAILS_KEY, b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
