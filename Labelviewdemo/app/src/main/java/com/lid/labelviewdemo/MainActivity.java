package com.lid.labelviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        {
            final LabelView label = new LabelView(this);
            label.setText("CHINA");
            label.setBackgroundColor(0xffE91E63);
            label.setTargetView(findViewById(R.id.image1), 10, LabelView.Gravity.LEFT_TOP);

            findViewById(R.id.image1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    label.remove();
                    Toast.makeText(MainActivity.this,"CHINA label remove",Toast.LENGTH_SHORT).show();
                }
            });
        }


        {
            final LabelView label = new LabelView(this);
            label.setText("Kunqu");
            label.setBackgroundColor(0xffE91E63);
            label.setTargetView(findViewById(R.id.image2), 12, LabelView.Gravity.RIGHT_TOP);
            findViewById(R.id.image2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    label.remove();
                    Toast.makeText(MainActivity.this,"Kunqu label remove",Toast.LENGTH_SHORT).show();
                }
            });
        }


        {
            LabelView label = new LabelView(this);
            label.setText("HD");
            label.setBackgroundColor(0xffE91E63);
            label.setTargetView(findViewById(R.id.button), 4, LabelView.Gravity.RIGHT_TOP);
            findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"button click",Toast.LENGTH_SHORT).show();
                }
            });
        }


        {
            LabelView label = new LabelView(this);
            label.setText("POP");
            label.setBackgroundColor(0xff03a9f4);
            label.setTargetView(findViewById(R.id.text), 5, LabelView.Gravity.LEFT_TOP);
            findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"please click ListView Demo",Toast.LENGTH_SHORT).show();
                }
            });
        }

        {
            LabelView label = new LabelView(this);
            label.setText("click");
            label.setBackgroundColor(0xff03a9f4);
            label.setTargetView(findViewById(R.id.click), 8, LabelView.Gravity.RIGHT_TOP);
            findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, ListViewActivity.class);
                    startActivity(i);
                }
            });
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
