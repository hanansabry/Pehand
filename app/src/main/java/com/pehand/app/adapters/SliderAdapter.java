package com.pehand.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pehand.app.R;
import com.pehand.app.pojos.SliderImage;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private ArrayList<SliderImage> sliderImages;

    public SliderAdapter(Context context, ArrayList<SliderImage> sliderImages) {
        this.context = context;
        this.sliderImages = sliderImages;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        switch (position) {
            case 0:
                Glide.with(viewHolder.itemView)
                        .load("https://www.pehand.com/Files/HomeSlider/2/Wallpaper.jpg")
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                Glide.with(viewHolder.itemView)
                        .load("https://www.pehand.com/Files/HomeSlider/1/22.jpg")
                        .into(viewHolder.imageViewBackground);
                break;

        }
//        Glide.with(viewHolder.itemView)
//                .load(sliderImages.get(position).getPhotoName())
//                .into(viewHolder.imageViewBackground);

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
