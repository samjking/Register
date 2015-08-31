package uk.co.samjking.register;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import 	android.content.Context;

/**
 * Created by sam_000 on 24/08/2015.
 */
public class PhotoPageAdapter extends PagerAdapter {

    Context context;

    int[] photos = {
            R.drawable.p0557033761,
            R.drawable.p0557033766,
            R.drawable.p0557033771,
            R.drawable.p0557033776,
            R.drawable.p0557033781,
            R.drawable.p0557033786,
            R.drawable.p0557033816,
    };

    public PhotoPageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageItem = new ImageView(context);
        imageItem.setImageResource(photos[position]);

        /*TextView view = new TextView(context);
        view.setText("Item "+position);
        view.setGravity(Gravity.CENTER);
        view.setBackgroundColor(Color.argb(255, position * 50, position * 10, position * 50));
        */

        container.addView(imageItem);
        return imageItem;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return photos.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }
}