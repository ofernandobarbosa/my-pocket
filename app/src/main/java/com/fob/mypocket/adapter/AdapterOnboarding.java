package com.fob.mypocket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.fob.mypocket.R;

public class AdapterOnboarding extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    public AdapterOnboarding(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    private int titles[] = {
            R.string.onboard_title1,
            R.string.onboard_title2,
            R.string.onboard_title3,
            R.string.onboard_title4,
    };

    private int descriptions[] = {
            R.string.onboard_description1,
            R.string.onboard_description2,
            R.string.onboard_description3,
            R.string.onboard_description4,
    };

    private int images[] = {
            R.drawable.ic_nubank,
            R.drawable.ic_picpay,
            R.drawable.ic_samsung,
            R.drawable.ic_dim,
    };

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = layoutInflater.inflate(R.layout.onboard_page, container, false);

        ImageView image = v.findViewById(R.id.onboard_image);
        TextView title = v.findViewById(R.id.onboard_title);
        TextView description = v.findViewById(R.id.onboard_description);
        image.setImageResource(images[position]);
        title.setText(titles[position]);
        description.setText(descriptions[position]);

        container.addView(v);
        return v;
    }
}
