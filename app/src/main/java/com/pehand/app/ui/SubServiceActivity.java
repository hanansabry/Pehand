package com.pehand.app.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.pehand.app.R;
import com.pehand.app.adapters.SubServicesAdapter;
import com.pehand.app.pojos.SubService;

import java.util.ArrayList;

import static com.pehand.app.common.Constants.SERVICE_ID;
import static com.pehand.app.common.Constants.SERVICE_NAME;

public class SubServiceActivity extends AppCompatActivity {

    private int serviceId;
    private String serviceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_service);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ArrayList<SubService> subServices = retrieveSubServiceInfo();
        actionBar.setTitle(serviceName);

        setupSubServicesRecyclerView(subServices);
    }

    private ArrayList<SubService> retrieveSubServiceInfo() {
        serviceId = getIntent().getExtras().getInt(SERVICE_ID);
        serviceName = getIntent().getExtras().getString(SERVICE_NAME);

        ArrayList<SubService> subServices = new ArrayList<>();
        SubService sub1 = new SubService(1, "تركيب تكييف 1.5 حصان", "250", "جنيه", "https://www.pehand.com/Files/SubService/9/air-conditioner-installation.jpg");
        SubService sub2 = new SubService(1, "تركيب تكييف 2.25 حصان", "250", "جنيه", "https://www.pehand.com/Files/SubService/9/air-conditioner-installation.jpg");
        subServices.add(sub1);
        subServices.add(sub2);
        return subServices;
    }

    private void setupSubServicesRecyclerView(ArrayList<SubService> subServices) {
        RecyclerView subservicesRecyclerView = findViewById(R.id.subservices_recyclerView);
        subservicesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        SubServicesAdapter adapter = new SubServicesAdapter(this, subServices);
        subservicesRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
