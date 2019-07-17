package com.pehand.app.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.pehand.app.R;
import com.pehand.app.adapters.ServicesAdapter;
import com.pehand.app.adapters.SliderAdapter;
import com.pehand.app.backend.services.ServiceRepositoryImpl;
import com.pehand.app.backend.services.ServicesRepository;
import com.pehand.app.common.BaseActivity;
import com.pehand.app.common.Constants;
import com.pehand.app.common.EmptyRecyclerView;
import com.pehand.app.pojos.Service;
import com.pehand.app.pojos.SliderImage;
import com.pehand.app.pojos.SubServiceDetails;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;

import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ServicesRepository.ServiceCallback, SliderAdapter.SliderCallback {

    private Toolbar toolbar;
    private ServicesRepository servicesRepository;
    private EmptyRecyclerView servicesRecyclerView;
    private ProgressBar progressBar;
    private View rootView;
    private LinearLayout emptyView;
    private View sliderViewContainer;
    private ArrayList<Service> allServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupNavigationDrawer();
        rootView = findViewById(android.R.id.content);
        progressBar = findViewById(R.id.progress_bar);
        emptyView = findViewById(R.id.empty_view);
        sliderViewContainer = findViewById(R.id.slider_view_container);

        servicesRepository = new ServiceRepositoryImpl();
        if (getIntent().getExtras() != null) {
            boolean showSlider = getIntent().getExtras().getBoolean(Constants.SHOW_SLIDER_KEY);
            loadData(showSlider);
        } else {
            loadData(true);
        }

        setupServicesRecyclerView();
    }

    private void loadData(boolean showSlider) {
        if (showSlider) {
            servicesRepository.getSliderImages(new ServicesRepository.ImageSliderCallback() {
                @Override
                public void onSuccess(ArrayList<SliderImage> sliderImages) {
                    setupSliderView(sliderImages);
                }

                @Override
                public void onFailure(String msg) {
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                }
            });
        }
        servicesRepository.getAllServices(this);
    }

    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupNavigationDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    private void setupServicesRecyclerView() {
        servicesRecyclerView = findViewById(R.id.services_recyclerView);
        servicesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void setupSliderView(ArrayList<SliderImage> sliderImages) {
        sliderViewContainer.setVisibility(View.VISIBLE);
        SliderView sliderView = findViewById(R.id.imageSlider);
        sliderView.setSliderAdapter(new SliderAdapter(sliderImages, this));
        sliderView.startAutoCycle();
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            sliderViewContainer.setVisibility(View.VISIBLE);
            loadData(true);
        } else if (id == R.id.nav_services) {
            sliderViewContainer.setVisibility(View.GONE);
            loadData(false);
        } else if (id == R.id.nav_offers) {
            startActivity(new Intent(this, OffersActivity.class));
        } else if (id == R.id.nav_contact_us) {
            startActivity(new Intent(this, ContactUsActivity.class));
        } else if (id == R.id.nav_facebook) {
            Intent facebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pehand"));
            startActivity(facebookIntent);
        } else if (id == R.id.nav_youtube) {
            Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCEtDcoitkZhtBclYoiEvNEQ"));
            startActivity(youtubeIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSuccess(ArrayList<Service> allServices) {
        this.allServices = allServices;
        progressBar.setVisibility(View.INVISIBLE);
        ServicesAdapter servicesAdapter = new ServicesAdapter(this, allServices, servicesRepository);
        servicesRecyclerView.setAdapter(servicesAdapter);
    }

    @Override
    public void onFailure(String msg) {
        progressBar.setVisibility(View.INVISIBLE);
        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        Snackbar.make(rootView, getString(R.string.error_msg), Snackbar.LENGTH_LONG).show();
        emptyView.setVisibility(View.VISIBLE);
        sliderViewContainer.setVisibility(View.INVISIBLE);
        servicesRecyclerView.setVisibility(View.INVISIBLE);

        Button refreshButton = findViewById(R.id.refresh_button);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.INVISIBLE);
                sliderViewContainer.setVisibility(View.VISIBLE);
                servicesRecyclerView.setVisibility(View.VISIBLE);
                loadData(true);
            }
        });
    }

    @Override
    public void onClick(SliderImage currentImage) {
        if (allServices != null) {
            String linkUrl = currentImage.getLinkUrl();
            if (linkUrl.contains(Constants.SERVICE_URL)) {
                showHomeScreen(false);
            } else if (linkUrl.contains(Constants.SUB_SERVICE_URL)) {
                try {
                    int subServiceId = Character.getNumericValue(linkUrl.charAt(linkUrl.length()-1));//to get last character of the link
                    Service imageService = getServiceById(subServiceId);
                    if (imageService != null) {
                        Intent subServiceIntent;
                        if (imageService.getSubCount() == 1) {
                            subServiceIntent = new Intent(this, SubServiceDetails.class);
                        } else {
                            subServiceIntent = new Intent(this, SubServiceActivity.class);
                        }
                        subServiceIntent.putExtra(Constants.SERVICE_ID, imageService.getId());
                        subServiceIntent.putExtra(Constants.SERVICE_NAME, imageService.getServiceName());
                        startActivity(subServiceIntent);
                    } else {
                        showHomeScreen(true);
                    }
                } catch (NumberFormatException ex) {
                    showHomeScreen(true);
                }
            } else {
                showHomeScreen(true);
            }
        }
    }

    private void showHomeScreen(boolean showSlider) {
        if (showSlider) {
            sliderViewContainer.setVisibility(View.VISIBLE);
        } else {
            sliderViewContainer.setVisibility(View.GONE);
        }
//        Intent servicesIntent = new Intent(this, MainActivity.class);
//        servicesIntent.putExtra(Constants.SHOW_SLIDER_KEY, showSlider);
    }

    private Service getServiceById(int id) {
        for (Service service : allServices) {
            if (service.getId() == id) {
                return service;
            }
        }
        return null;
    }
}
