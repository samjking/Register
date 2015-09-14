package uk.co.samjking.register;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
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

        layout.setBackgroundColor(Color.GREEN);


//260dp
        TextView nameItem = new TextView(context);
        nameItem.setText(person[1]);
        layout.addView(nameItem);
        nameItem.setGravity(Gravity.CENTER);

        ImageView imageItem = new ImageView(context);
        int photo = context.getResources().getIdentifier(person[2], "drawable", context.getPackageName());
        imageItem.setImageResource(photo);
        layout.addView(imageItem);

        ImageView box = new ImageView(context);
        box.setImageResource(R.drawable.ic_crop_square_white_36dp);
        layout.addView(box);

        ViewGroup.LayoutParams layoutParams = imageItem.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        //Log.d("SJK",String.valueOf(ViewGroup.LayoutParams.MATCH_PARENT));
        imageItem.setLayoutParams(layoutParams);

        container.addView(layout);



        return layout;
    }

    @Override
    public float getPageWidth(int page) {
        return 0.65f;
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