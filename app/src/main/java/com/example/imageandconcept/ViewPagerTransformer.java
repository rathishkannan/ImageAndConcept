package com.example.imageandconcept;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by rathish.kannan on 12/14/15.
 */
public class ViewPagerTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {
        float alpha = 0;
        if(position >=0 && position <= 1) {
            alpha = 1 - position;
        } else if (position > -1 && position <0) {
            alpha = 1 + position;
        }
        page.setAlpha(alpha);
        page.setTranslationX(page.getWidth() * -position);
        float yposition = position * page.getHeight();
        page.setTranslationY(yposition);
    }
}
