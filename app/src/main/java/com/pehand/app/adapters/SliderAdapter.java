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
import com.pehand.app.pojos.SliderImage;
import com.pehand.app.ui.MainActivity;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    public interface SliderCallback {
        void onClick(SliderImage currentImage);
    }

    private SliderCallback sliderCallback;
    private ArrayList<SliderImage> sliderImages;

    public SliderAdapter(ArrayList<SliderImage> sliderImages, SliderCallback sliderCallback) {
        this.sliderCallback = sliderCallback;
        this.sliderImages = sliderImages;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
      final SliderImage currentImage = sliderImages.get(position);
        Glide.with(viewHolder.itemView)
                .load(currentImage.getPhotoName())
                .placeholder(R.drawable.wallpaper)
                .into(viewHolder.imageViewBackground);
        viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sliderCallback.onClick(currentImage);
            }
        });
    }

    @Override
    public int getCount() {
        return sliderImages.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
