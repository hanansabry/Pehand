package com.pehand.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pehand.app.R;
import com.pehand.app.backend.services.ServicesRepository;
import com.pehand.app.common.Constants;
import com.pehand.app.pojos.Service;
import com.pehand.app.pojos.SubService;
import com.pehand.app.pojos.SubServiceDetails;
import com.pehand.app.ui.ServiceDetailsActivity;
import com.pehand.app.ui.SubServiceActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder> {

    private Context context;
    private ArrayList<Service> servicesList;
    private ServicesRepository servicesRepository;

    public ServicesAdapter(Context context, ArrayList<Service> servicesList, ServicesRepository servicesRepository) {
        this.context = context;
        this.servicesList = servicesList;
        this.servicesRepository = servicesRepository;
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
                    int id = getAdapterPosition();
                    final Service selectedService = servicesList.get(id);

                    if (selectedService.getSubCount() == 0) {
                        Toast.makeText(context, context.getString(R.string.not_available), Toast.LENGTH_LONG).show();
                    } else if (selectedService.getSubCount() == 1) {
                        final Intent subServiceIntent = new Intent(context, ServiceDetailsActivity.class);
                        servicesRepository.getAllSubServicesById(selectedService.getId(), new ServicesRepository.SubServicesRetrievingCallback() {
                            @Override
                            public void onSuccess(ArrayList<SubService> allSubServices) {
                                subServiceIntent.putExtra(Constants.SERVICE_ID, allSubServices.get(0).getId());
                                subServiceIntent.putExtra(Constants.SERVICE_NAME, allSubServices.get(0).getSubServiceName());
                                context.startActivity(subServiceIntent);
                            }

                            @Override
                            public void onFailure(String msg) {
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Intent subServiceIntent = new Intent(context, SubServiceActivity.class);
                        subServiceIntent.putExtra(Constants.SERVICE_ID, selectedService.getId());
                        subServiceIntent.putExtra(Constants.SERVICE_NAME, selectedService.getServiceName());
                        context.startActivity(subServiceIntent);
                    }
                }
            });
        }
    }
}
