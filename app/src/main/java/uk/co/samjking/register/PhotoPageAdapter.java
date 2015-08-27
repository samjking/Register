package uk.co.samjking.register;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import 	android.content.Context;
/**
 * Created by sam_000 on 24/08/2015.
 */
public class PhotoPageAdapter extends PagerAdapter {

    Context context;

    public PhotoPageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView view = new TextView(context);
        view.setText("Item "+position);
        view.setGravity(Gravity.CENTER);
        view.setBackgroundColor(Color.argb(255, position * 50, position * 10, position * 50));

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }
}