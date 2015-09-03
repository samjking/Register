package uk.co.samjking.register;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        //params.gravity=Gravity.CENTER_HORIZONTAL;
        layout.setLayoutParams(params);
        //layout.setGravity(Gravity.CENTER_HORIZONTAL);

        //layout.setBackgroundColor(Color.GREEN);

        TextView nameItem = new TextView(context);
        nameItem.setText(person[1]);
        layout.addView(nameItem);

        ImageView imageItem = new ImageView(context);
        int photo = context.getResources().getIdentifier(person[2], "drawable", context.getPackageName());
        imageItem.setImageResource(photo);

        /*
        ViewGroup.LayoutParams layoutParams = imageItem.getLayoutParams();
        layoutParams.width = 80;
        layoutParams.height = 80;
        imageItem.setLayoutParams(layoutParams);
        */
        layout.addView(imageItem);

        container.addView(layout);

        return layout;
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