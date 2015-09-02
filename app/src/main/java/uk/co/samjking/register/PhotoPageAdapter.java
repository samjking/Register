package uk.co.samjking.register;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import 	android.content.Context;

import java.util.ArrayList;

/**
 * Created by sam_000 on 24/08/2015.
 */
public class PhotoPageAdapter extends PagerAdapter {

    Context context;

    ArrayList<String[]> data;

    public PhotoPageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String[] person = data.get(position);



        ImageView imageItem = new ImageView(context);
        int photo = context.getResources().getIdentifier(person[2],"drawable",context.getPackageName());
        imageItem.setImageResource(photo);
        container.addView(imageItem);

        TextView nameItem = new TextView(context);
        nameItem.setText("Hello");
        container.addView(nameItem);

        return imageItem;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public void setData(ArrayList<String[]> data) {
        this.data=data;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }
}