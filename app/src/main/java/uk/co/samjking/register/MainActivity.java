package uk.co.samjking.register;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    PhotoContainer mContainer;

    ViewPager pager;

    int[] photos = {
            R.drawable.p0557033761,
            R.drawable.p0557033766,
            R.drawable.p0557033771,
            R.drawable.p0557033776,
            R.drawable.p0557033781,
            R.drawable.p0557033786,
            R.drawable.p0557033816,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContainer = (PhotoContainer) findViewById(R.id.photo_container);

        pager = mContainer.getViewPager();
        PagerAdapter adapter = new PhotoPageAdapter(this,photos);
        pager.setAdapter(adapter);
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(adapter.getCount());
        //A little space between pages
        pager.setPageMargin(15);

        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        pager.setClipChildren(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int[] getPhotos() {
        return photos;
    }

    public void markAbsent(View view) {
        moveForward();
    }

    public void markPresent(View view) {
        moveForward();
    }

    protected void moveForward() {
        pager.setCurrentItem(pager.getCurrentItem()+1, true);
    }
}