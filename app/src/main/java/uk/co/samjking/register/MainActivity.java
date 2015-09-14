package uk.co.samjking.register;

import android.content.res.TypedArray;
import android.graphics.Point;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    PhotoContainer mContainer;

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContainer = (PhotoContainer) findViewById(R.id.photo_container);

        pager = mContainer.getViewPager();

        ArrayList data = readJSON();

        PhotoPageAdapter adapter = new PhotoPageAdapter(this);
        adapter.setData(data);

        pager.setAdapter(adapter);
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(adapter.getCount());

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        // Set the margins between pages to 15%
        float margin = size.x * 0.15f;
        pager.setPageMargin(Math.round(margin));

        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        pager.setClipChildren(false);



    }

    protected ArrayList<String[]> readJSON() {
        InputStream inputStream = getResources().openRawResource(R.raw.register1117849);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ArrayList<String[]> data = new ArrayList<String[]>();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("SJK", byteArrayOutputStream.toString());
        try {
            // Parse the data into jsonobject to get original data in form of json.
            JSONObject jObject = new JSONObject(
                    byteArrayOutputStream.toString());

            JSONArray students= jObject.getJSONArray("students");

            String id;
            String name;
            String photo;
            for (int i = 0; i < students.length(); i++) {
                id = students.getJSONObject(i).getString("id");
                name = students.getJSONObject(i).getString("name");
                photo =  "p" + id.substring(4);
                Log.v("SJK ID", id);
                Log.v("SJK Name", name);
                data.add(new String[] { id, name, photo });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
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