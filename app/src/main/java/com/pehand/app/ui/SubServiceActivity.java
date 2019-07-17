package com.pehand.app.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.pehand.app.R;
import com.pehand.app.adapters.SubServicesAdapter;
import com.pehand.app.backend.services.ServiceRepositoryImpl;
import com.pehand.app.backend.services.ServicesRepository;
import com.pehand.app.common.Constants;
import com.pehand.app.pojos.SubService;

import java.util.ArrayList;

import static com.pehand.app.common.Constants.SERVICE_ID;
import static com.pehand.app.common.Constants.SERVICE_NAME;

public class SubServiceActivity extends AppCompatActivity implements ServicesRepository.SubServicesRetrievingCallback {

    private int serviceId;
    private String serviceName;
    private RecyclerView subservicesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_service);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        serviceId = getIntent().getExtras().getInt(SERVICE_ID);
        serviceName = getIntent().getExtras().getString(SERVICE_NAME);
        if (serviceName != null) actionBar.setTitle(serviceName);

        ServicesRepository subServiceRepository = new ServiceRepositoryImpl();
        subServiceRepository.getAllSubServicesById(serviceId, this);
        setupSubServicesRecyclerView();
    }

    private void setupSubServicesRecyclerView() {
        subservicesRecyclerView = findViewById(R.id.subservices_recyclerView);
        subservicesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onSuccess(ArrayList<SubService> allSubServices) {
        if (allSubServices.size() != 0) {
            SubServicesAdapter adapter = new SubServicesAdapter(this, allSubServices);
            subservicesRecyclerView.setAdapter(adapter);
        } else {
            finish();
            Toast.makeText(this, "No sub services, go to details directly", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ServiceDetailsActivity.class);
            intent.putExtra(Constants.SERVICE_ID, serviceId);
            intent.putExtra(Constants.SERVICE_NAME, serviceName);
            startActivity(intent);
        }
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
