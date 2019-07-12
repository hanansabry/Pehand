package com.pehand.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pehand.app.R;
import com.pehand.app.common.Constants;
import com.pehand.app.pojos.Service;
import com.pehand.app.pojos.SubService;
import com.pehand.app.ui.SubServiceActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder> {

    private Context context;
    private ArrayList<Service> servicesList;

    public ServicesAdapter(Context context, ArrayList<Service> servicesList) {
        this.context = context;
        this.servicesList = servicesList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service_item, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service currentService = servicesList.get(position);
        holder.serviceName.setText(currentService.getServiceName());
        Glide.with(holder.itemView)
                .load(currentService.getPhotoName())
                .into(holder.serviceImage);
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder {
        private ImageView serviceImage;
        private TextView serviceName;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);

            serviceName = itemView.findViewById(R.id.service_name);
            serviceImage = itemView.findViewById(R.id.service_image);
            serviceImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent subServiceIntent = new Intent(context, SubServiceActivity.class);

                    int id = getAdapterPosition();
                    Service selectedService = servicesList.get(id);
                    subServiceIntent.putExtra(Constants.SERVICE_ID, selectedService.getId());
                    subServiceIntent.putExtra(Constants.SERVICE_NAME, selectedService.getServiceName());
                    context.startActivity(subServiceIntent);
                }
            });
        }
    }
}
