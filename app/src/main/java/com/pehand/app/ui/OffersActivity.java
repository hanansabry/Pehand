package com.pehand.app.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.pehand.app.R;
import com.pehand.app.adapters.SubServicesAdapter;
import com.pehand.app.backend.services.ServiceRepositoryImpl;
import com.pehand.app.backend.services.ServicesRepository;
import com.pehand.app.pojos.SubService;

import java.util.ArrayList;

import static com.pehand.app.common.Constants.SERVICE_ID;
import static com.pehand.app.common.Constants.SERVICE_NAME;

public class OffersActivity extends AppCompatActivity implements ServicesRepository.SubServicesRetrievingCallback {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.progress_bar);

        ServicesRepository servicesRepository = new ServiceRepositoryImpl();
        servicesRepository.getAllOffers(this);
    }

    private void setupSubServicesRecyclerView(ArrayList<SubService> subServices) {
        RecyclerView subservicesRecyclerView = findViewById(R.id.offers_recyclerView);
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

    @Override
    public void onSuccess(ArrayList<SubService> allOffers) {
        progressBar.setVisibility(View.INVISIBLE);
        setupSubServicesRecyclerView(allOffers);
    }

    @Override
    public void onFailure(String msg) {
        progressBar.setVisibility(View.INVISIBLE);
        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.error_msg), Snackbar.LENGTH_LONG).show();
    }
}
