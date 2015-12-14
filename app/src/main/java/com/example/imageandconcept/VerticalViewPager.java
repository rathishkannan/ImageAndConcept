package com.example.imageandconcept;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * Created by rathish.kannan on 12/14/15.
 */
public class VerticalViewPager extends ViewPager {

    public VerticalViewPager(Context context) {
        super(context, null);
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(false, new ViewPagerTransformer());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    private MotionEvent swapTouchEvent(MotionEvent motionEvent) {
        float width = getWidth();
        float height = getHeight();

        float swappedX = (motionEvent.getY()/height) * width;
        float swappedY = (motionEvent.getX()/width) * height;

        motionEvent.setLocation(swappedX, swappedY);

        return motionEvent;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = super.onInterceptTouchEvent(swapTouchEvent(ev));
        swapTouchEvent(ev);
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapTouchEvent(ev));
    }
}
