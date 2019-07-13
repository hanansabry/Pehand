package com.pehand.app.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.pehand.app.R;
import com.pehand.app.adapters.SubServicesAdapter;
import com.pehand.app.backend.services.ServiceRepositoryImpl;
import com.pehand.app.backend.services.ServicesRepository;
import com.pehand.app.pojos.SubService;

import java.util.ArrayList;

import static com.pehand.app.common.Constants.SERVICE_ID;
import static com.pehand.app.common.Constants.SERVICE_NAME;

public class OffersActivity extends AppCompatActivity implements ServicesRepository.SubServicesRetrievingCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ServicesRepository servicesRepository = new ServiceRepositoryImpl();
        servicesRepository.getAllOffers(this);
    }

//    private ArrayList<SubService> retrieveOffersInfo() {
////        int serviceId = getIntent().getExtras().getInt(SERVICE_ID);
////        String serviceName = getIntent().getExtras().getString(SERVICE_NAME);
//
//        ArrayList<SubService> subServices = new ArrayList<>();
//        SubService sub1 = new SubService(1, "فلتر مياه 7 مراحل تايوانى بخزان 10 لتر", "2100", "جنيه شامل التركيب", "https://www.pehand.com/Files/SubService/1/1%20(1).jpg");
//        SubService sub2 = new SubService(1, "فلتر مياه 5 مراحل تايوانى", "500", "جنيه شامل التركيب", "https://www.pehand.com/Files/SubService/2/s520121314253.jpg");
//        SubService sub3 = new SubService(1, "معاينة صيانة أو تركيب فلتر أو تغير أى شمع", "25", "جنيه للمعاينة", "https://www.pehand.com/Files/SubService/8/تعمیرکار-دستگاه-تصفیه-آب (1).jpg");
//        subServices.add(sub1);
//        subServices.add(sub2);
//        subServices.add(sub3);
//        return subServices;
//    }

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
        setupSubServicesRecyclerView(allOffers);
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
