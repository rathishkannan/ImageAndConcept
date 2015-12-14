package com.example.imageandconcept;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    VerticalViewPager viewPager;
    FragmentStatePagerAdapter adapter;
    List<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        images = Arrays.asList(getResources().getStringArray(R.array.image_array));
        viewPager = (VerticalViewPager) findViewById(R.id.viewpager);
        adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                ImageNConceptFragment fragment = new ImageNConceptFragment();
                Bundle b = new Bundle();
                b.putInt("POSITION", position);
                fragment.setArguments(b);
                return fragment;
            }

            @Override
            public int getCount() {
                return images.size();
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }
}
