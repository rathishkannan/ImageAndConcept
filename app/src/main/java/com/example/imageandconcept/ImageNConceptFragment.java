package com.example.imageandconcept;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rathish.kannan on 12/14/15.
 */
public class ImageNConceptFragment extends Fragment {
    TextView textView;
    ImageView imageView;
    LruCache<String, Bitmap> cache;
    List<String> images;
    List<String> concepts;
    int position;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("POSITION", position);
    }

    public ImageNConceptFragment() {
        Log.i("ImageNConceptFragment", "Initializing Cache");
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        cache = new LruCache<String, Bitmap>(maxMemory/8){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount()/1024;
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.imagenconcept, container, false);
        textView = (TextView) fragmentView.findViewById(R.id.textView);
        imageView = (ImageView) fragmentView.findViewById(R.id.imageView);
        Bundle args = getArguments();
        position = args.getInt("POSITION");
        if(savedInstanceState != null) {
            position = savedInstanceState.getInt("POSITION");
        }
        images = Arrays.asList(getResources().getStringArray(R.array.image_array));
        concepts = Arrays.asList(getResources().getStringArray(R.array.concept_array));
        String imagePath = "images/" + images.get(position) + ".jpg";

        Bitmap image = cache.get(imagePath);
        if(image == null) {
            AssetManager manager = inflater.getContext().getAssets();
            try {
                InputStream inStr = manager.open(imagePath);
                Bitmap img = BitmapFactory.decodeStream(inStr);
                if(img != null) {
                    cache.put(imagePath, img);
                    imageView.setImageBitmap(img);
                }

            } catch (IOException e) {
                imageView.setImageResource(R.drawable.android_logo);
                e.printStackTrace();
            }
        } else {
            imageView.setImageBitmap(image);
        }

        textView.setText(concepts.get(position));

        return fragmentView;
    }
}
