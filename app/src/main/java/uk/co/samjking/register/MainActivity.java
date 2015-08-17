package uk.co.samjking.register;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    /**
     * Define the number of items visible when the carousel is first shown.
     */
    private static final float INITIAL_ITEMS_COUNT = 1.5F;

    private LinearLayout mCarouselContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCarouselContainer = (LinearLayout) findViewById(R.id.carousel);

        // Compute the width of a carousel item based on the screen width and number of initial items.
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int imageWidth = (int) (displayMetrics.widthPixels / INITIAL_ITEMS_COUNT);

        final TypedArray photoResourcesTypedArray = getResources().obtainTypedArray(R.array.photo_array);


        // Populate the carousel with items
        ImageView imageItem;
        for (int i = 0 ; i < photoResourcesTypedArray.length() ; ++i) {
            // Create new ImageView
            imageItem = new ImageView(this);

            // Set the shadow background
            //imageItem.setBackgroundResource(R.drawable.shadow);

            // Set the image view resource
            imageItem.setImageResource(photoResourcesTypedArray.getResourceId(i, -1));

            // Set the size of the image view to the previously computed value
            imageItem.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageWidth));

            /// Add image view to the carousel container
             mCarouselContainer.addView(imageItem);
        }

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
}
