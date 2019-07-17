package com.pehand.app.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.pehand.app.R;
import com.pehand.app.backend.services.PostDataRepository;
import com.pehand.app.backend.services.PostDataRepositoryImpl;
import com.pehand.app.backend.services.ServiceRepositoryImpl;
import com.pehand.app.backend.services.ServicesRepository;
import com.pehand.app.common.Constants;
import com.pehand.app.pojos.City;
import com.pehand.app.pojos.SubServiceDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServiceOrderActivity extends AppCompatActivity implements ServicesRepository.CityRetrievingCallback {

    private ServicesRepository serviceRepository;
    private Spinner citySpinner;
    private Spinner villageSpinner;
    private int selectedVillageId;
    private int selectedCityId;
    private EditText nameET;
    private EditText mobileET;
    private EditText addressET;
    private EditText requestTimeET;
    private EditText notesET;

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
        loadCitiesAndVillages();
    }

    private void setViewsData(final SubServiceDetails details) {
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

        //client data
        nameET = findViewById(R.id.name);
        mobileET = findViewById(R.id.mobile);
        addressET = findViewById(R.id.address);
        requestTimeET = findViewById(R.id.date);
        notesET = findViewById(R.id.notes);
        final CheckBox conditionsCheckBox = findViewById(R.id.conditions_checkbox);
        Button orderNow = findViewById(R.id.orderNow);
        orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                String mobile = mobileET.getText().toString();
                String address = addressET.getText().toString();
                String requestTime = requestTimeET.getText().toString();
                String notes = notesET.getText().toString();
                if (validateData(name, mobile, address, requestTime, notes)) {
                    if (selectedCityId == 0 || selectedVillageId == 0) {
                        Snackbar.make(findViewById(android.R.id.content), getString(R.string.city_village_required), Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if (!conditionsCheckBox.isChecked()) {
                        Snackbar.make(findViewById(android.R.id.content), getString(R.string.conditions_terms_check), Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    Map<String, String> data = new HashMap<>();
                    data.put("ClientName", name);
                    data.put("Mobile", mobile);
                    data.put("Address", address);
                    data.put("PCityId", String.valueOf(selectedCityId));
                    data.put("PVillageId", String.valueOf(selectedVillageId));
                    data.put("PSubServiceId", details.getSubServiceName());
                    data.put("RequestTime", requestTime);
                    data.put("Note", notes);
                    PostDataRepository repository = new PostDataRepositoryImpl();
                    repository.orderNow(data, new PostDataRepository.PostRequestCallback() {
                        @Override
                        public void onSuccess() {
                            Snackbar.make(findViewById(android.R.id.content),
                                    getString(R.string.order_sent_successfully),
                                    Snackbar.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(String msg) {
                            Snackbar.make(findViewById(android.R.id.content),
                                    getString(R.string.send_error),
                                    Snackbar.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    private void loadCitiesAndVillages() {
        citySpinner = findViewById(R.id.city_spinner);
        villageSpinner = findViewById(R.id.village_spinner);
        serviceRepository = new ServiceRepositoryImpl();
        serviceRepository.getAllCities(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onSuccess(ArrayList<City> cities) {
        ArrayList<String> citiesNames = new ArrayList<>();
        final ArrayList<Integer> citiesIds = new ArrayList<>();
        for (City city : cities) {
            citiesNames.add(city.getCityName());
            citiesIds.add(city.getId());
        }
        citiesNames.add(0, getString(R.string.city_prompt));
        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_dropdown_item, citiesNames){
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                if (position == 0) {
                    textView.setTextColor(Color.GRAY);
                } else {
                    textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }
                return view;
            }
        };
        citiesAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        citySpinner.setAdapter(citiesAdapter);

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    selectedCityId = citiesIds.get(position-1);
                    Toast.makeText(getApplicationContext(), "Selected : " + selectedCityId, Toast.LENGTH_SHORT).show();

                    //get villages
                    serviceRepository.getVillageOfCityById(position, new ServicesRepository.VillageRetrievingCallback() {
                        @Override
                        public void onSuccess(ArrayList<String> villages, final ArrayList<Integer> villageIds) {
                            ArrayAdapter<String> villagesAdapter = new ArrayAdapter<>(ServiceOrderActivity.this, R.layout.spinner_dropdown_item, villages);
                            villageSpinner.setAdapter(villagesAdapter);
                            villageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    selectedVillageId = villageIds.get(position);
//                                    Toast.makeText(getApplicationContext(), "Selected : " + villageIds.get(position), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }

                        @Override
                        public void onFailure(String msg) {
                            Toast.makeText(ServiceOrderActivity.this, getString(R.string.error_msg), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(this, getString(R.string.error_msg), Toast.LENGTH_SHORT).show();
    }

    private boolean validateData(String clientName,
                                 String mobile,
                                 String address,
                                 String requestTime,
                                 String note) {
        boolean validate = true;
        if (clientName == null || clientName.isEmpty()) {
            nameET.setError(getString(R.string.required_field));
            validate = false;
        }
        if (mobile == null || mobile.isEmpty()) {
            mobileET.setError(getString(R.string.required_field));
            validate = false;
        }
        if (address == null || address.isEmpty()) {
            addressET.setError(getString(R.string.required_field));
            validate = false;
        }
        if (requestTime == null || requestTime.isEmpty()) {
            requestTimeET.setError(getString(R.string.required_field));
            validate = false;
        }
        if (note == null || note.isEmpty()) {
            notesET.setError(getString(R.string.required_field));
            validate = false;
        }
        return validate;
    }
}
