package uk.co.samjking.register;

import android.content.res.TypedArray;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import uk.co.samjking.register.entity.Student;

public class MainActivity extends AppCompatActivity {

    PhotoContainer mContainer;

    TextView headCount;
    int presentCount;

    int[] status;

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presentCount = 0;

        mContainer = (PhotoContainer) findViewById(R.id.photo_container);

        pager = mContainer.getViewPager();

        ArrayList data = readJSON();

        status = new int[data.size()];

        headCount = (TextView)findViewById(R.id.headCount);
        headCount.setText(String.valueOf(presentCount)+"/"+String.valueOf(data.size()));

        PhotoPageAdapter adapter = new PhotoPageAdapter(this);
        adapter.setData(data);

        pager.setAdapter(adapter);
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(adapter.getCount());

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Log.d("SJK window", String.valueOf(size.x));

        // Set the margins between pages to 15%
        float margin = size.x * 0.15f;
        Log.d("SJK margin",String.valueOf(Math.round(margin)));
        pager.setPageMargin(Math.round(margin));

        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        pager.setClipChildren(false);



    }

    protected ArrayList<Student> readJSON() {
        InputStream inputStream = getResources().openRawResource(R.raw.register1117849);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ArrayList<Student> data = new ArrayList<Student>();

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

        try {
            // Parse the data into jsonobject to get original data in form of json.
            JSONObject jObject = new JSONObject(
                    byteArrayOutputStream.toString());

            JSONArray students= jObject.getJSONArray("students");

            Long id;
            //String name;
            //String photo;
            for (int i = 0; i < students.length(); i++) {
                id = students.getJSONObject(i).getLong("id");
                Student s = new Student(id);
                s.setFullName(students.getJSONObject(i).getString("name"));
                s.setPhoto("p" + String.valueOf(id).substring(4));

                //name = students.getJSONObject(i).getString("name");
                //photo =  "p" + String.valueOf(id).substring(4);
                //data.add(new String[] { String.valueOf(id), name, photo });
                data.add(s);
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
        updateHeadcount(pager.getCurrentItem(),0);
        View v = pager.getChildAt(pager.getCurrentItem());
        ImageView i = (ImageView)v.findViewWithTag("img");
        i.setImageResource(R.drawable.ic_close_white_36dp);
        moveForward();
    }

    public void markPresent(View view) {
        updateHeadcount(pager.getCurrentItem(),1);
        View v = pager.getChildAt(pager.getCurrentItem());
        ImageView i = (ImageView)v.findViewWithTag("img");
        i.setImageResource(R.drawable.ic_check_white_36dp);
        moveForward();
    }

    protected void moveForward() {

        pager.setCurrentItem(pager.getCurrentItem() + 1, true);
    }

    protected void updateHeadcount(int position, int newStatus) {
        if (status[position]==0 && newStatus==1) {
            presentCount ++;
        } else if (status[position]==1 && newStatus==0) {
            presentCount --;
        }

        status[position]=newStatus;

        headCount.setText(String.valueOf(presentCount)+"/"+String.valueOf(status.length));
    }
}