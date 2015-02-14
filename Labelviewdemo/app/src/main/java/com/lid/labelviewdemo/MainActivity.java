package com.lid.labelviewdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        {
            LabelView label = new LabelView(this);
            label.setText("CHINA");
            label.setBackgroundColor(0xffE91E63);
            label.setTargetView(findViewById(R.id.image1), 20, LabelView.Gravity.LEFT_TOP);
        }


        {
            LabelView label = new LabelView(this);
            label.setText("Kunqu");
            label.setBackgroundColor(0xffE91E63);
            label.setTargetView(findViewById(R.id.image2), 25, LabelView.Gravity.RIGHT_TOP);
        }


        {
            LabelView label = new LabelView(this);
            label.setText("HD");
            label.setBackgroundColor(0xffE91E63);
            label.setPadding(40,1,40,1);
            label.setTargetView(findViewById(R.id.button), 8, LabelView.Gravity.RIGHT_TOP);
        }


        {
            LabelView label = new LabelView(this);
            label.setText("POP");
            label.setBackgroundColor(0xff03a9f4);
            label.setPadding(40,1,40,1);
            label.setTargetView(findViewById(R.id.text), 10, LabelView.Gravity.LEFT_TOP);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
