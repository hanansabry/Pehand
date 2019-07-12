package com.pehand.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pehand.app.R;
import com.pehand.app.common.Constants;
import com.pehand.app.pojos.SubService;
import com.pehand.app.ui.ServiceDetailsActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubServicesAdapter extends RecyclerView.Adapter<SubServicesAdapter.SubServiceViewHolder> {

    private Context context;
    private ArrayList<SubService> subServices;

    public SubServicesAdapter(Context context, ArrayList<SubService> subServices) {
        this.context = context;
        this.subServices = subServices;
    }

    @NonNull
    @Override
    public SubServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sub_service_item, parent, false);
        return new SubServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubServiceViewHolder holder, int position) {
        SubService subService = subServices.get(position);
        Glide.with(context)
                .load(subService.getPhotoName())
                .into(holder.subServiceImage);
        holder.subServiceName.setText(subService.getSubServiceName());
        holder.subServicePrice.setText(subService.getCurrentPrice());
        holder.subServicePriceNote.setText(subService.getPriceNote());
    }

    @Override
    public int getItemCount() {
        return subServices.size();
    }

    class SubServiceViewHolder extends RecyclerView.ViewHolder {
        private ImageView subServiceImage;
        private TextView subServiceName, subServicePrice, subServicePriceNote;
        private Button orderNow;
        public SubServiceViewHolder(@NonNull final View itemView) {
            super(itemView);

            subServiceImage = itemView.findViewById(R.id.subservice_photo);
            subServiceName = itemView.findViewById(R.id.subservice_name);
            subServicePrice = itemView.findViewById(R.id.subservice_price);
            subServicePriceNote = itemView.findViewById(R.id.subservice_price_note);
            orderNow = itemView.findViewById(R.id.order_now);
            orderNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = getAdapterPosition();
                    SubService selectedService = subServices.get(id);
                    Intent intent = new Intent(itemView.getContext(), ServiceDetailsActivity.class);
                    intent.putExtra(Constants.SERVICE_ID, selectedService.getId());
                    intent.putExtra(Constants.SERVICE_NAME, selectedService.getSubServiceName());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
