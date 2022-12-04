package com.example.learnenglishtoday;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.PagerAdapter;

public class CustomSlider extends PagerAdapter {

    Context context;
    String[] headings;
    String[] descriptions;
    int [] images = {R.drawable.thi, R.drawable.dieukien, R.drawable.danhdongtu, R.drawable.quanhe, R.drawable.tuongthuat, R.drawable.thanhngu, R.drawable.caudo};

    public CustomSlider(Context context, String[] headings, String[] descriptions){
        this.context=context;
        this.headings= headings;
        this.descriptions= descriptions;
    }



    @Override
    public int getCount() { return headings.length;}

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_slider, container, false);
        ImageView slider_image = view.findViewById(R.id.slider_image_view);
        TextView slider_heading = view.findViewById(R.id.slider_heading);
        TextView slider_description = view.findViewById(R.id.slider_description);
        slider_image.setImageResource(images[position]);
        slider_heading.setText(headings[position]);
        slider_description.setText(descriptions[position]);

        slider_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sliderPromptDialog(position);
            }
        });

        container.addView(view);

        return view;
    }

    private void sliderPromptDialog(int position) {
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setTitle("Make a selection!");
        final int slider_title_arrays[]= {
                R.array.slider_title_tenses,
                R.array.slider_title_conditionals,
                R.array.slider_title_gerunds,
                R.array.slider_title_relative,
                R.array.slider_title_directspeech,
                R.array.slider_title_idioms,
                R.array.slider_title_tests,
                R.array.slider_title_usermanual};

        }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);
    }
}
