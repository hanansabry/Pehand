package com.pehand.app.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.pehand.app.R;
import com.pehand.app.adapters.ServicesAdapter;
import com.pehand.app.adapters.SliderAdapter;
import com.pehand.app.common.BaseActivity;
import com.pehand.app.pojos.Service;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupNavigationDrawer();
        setupSliderView();
        setupServicesRecyclerView();
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupServicesRecyclerView() {
        RecyclerView servicesRecyclerView = findViewById(R.id.services_recyclerView);
        servicesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        ServicesAdapter servicesAdapter = new ServicesAdapter(this, tempServicesList());
        servicesRecyclerView.setAdapter(servicesAdapter);
    }

    private void setupSliderView() {
        SliderView sliderView = findViewById(R.id.imageSlider);
        sliderView.setSliderAdapter(new SliderAdapter(this));
        sliderView.startAutoCycle();
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

    private ArrayList<Service> tempServicesList() {
        ArrayList<Service> tmpList = new ArrayList<>();
        tmpList.add(new Service(1, "خدمات التكييف", "https://www.pehand.com/files/Service/1/air-conditioner%20(2).jpg"));
        tmpList.add(new Service(2, "فلاتر المياه", "https://www.pehand.com/files/Service/6/water-filter%20(3).jpg"));
        tmpList.add(new Service(3, "دش وريسيفر", "https://www.pehand.com/files/Service/4/satellite%20(1).jpg"));
        tmpList.add(new Service(4, "صيانة مكن الخياطة", "https://www.pehand.com/files/Service/5/sewing.jpg"));
        tmpList.add(new Service(5, "الكهرباء", "https://www.pehand.com/files/Service/5/sewing.jpg"));
        tmpList.add(new Service(6, "رفع مواد البناء", "https://www.pehand.com/files/Service/3/plug.jpg"));
        tmpList.add(new Service(7, "النظافة المنزلية", "https://www.pehand.com/files/Service/7/mop%20(1).jpg"));
        tmpList.add(new Service(8, "أعمال السباكة", "https://www.pehand.com/files/Service/8/plumbing%20(1).jpg"));
        tmpList.add(new Service(9, "أسقف معلقة", "https://www.pehand.com/files/Service/9/sakf.jpg"));
        return tmpList;
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

        if (id == R.id.nav_services) {
            //startActivity(new Intent(this, MainActivity.class));
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
}
