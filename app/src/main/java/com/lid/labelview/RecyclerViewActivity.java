package com.lid.labelview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lid.lib.LabelImageView;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewActivity extends ActionBarActivity {

    public class CategoryData {
        public String image;
        public String text;
        public String label;
    }

    private List<CategoryData> datas;
    private HomeAdapter mAdapter;

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    RecyclerViewActivity.this).inflate(R.layout.list_view_item, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.tv.setText(datas.get(position).text);
            holder.labelImageView.setImageResource(Integer.parseInt(datas.get(position).image));
            holder.labelImageView.setLabelText("LID " + position);
        }

        @Override
        public int getItemCount()
        {
            return datas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            TextView tv;
            LabelImageView labelImageView;
            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.text);
                labelImageView = (LabelImageView) view.findViewById(R.id.image);
            }
        }
    }

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        datas = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());

        getCategoryData();
        getCategoryData();
        getCategoryData();

        mAdapter.notifyDataSetChanged();
    }

    private void getCategoryData() {

        {
            CategoryData item = new CategoryData();
            item.text = "Following the founding of New China, the policy of cultural reawakening offered Kunqu Opera the chance to emerge from a long period of neglect.";
            item.image = R.mipmap.k1 + "";
            item.label = "MD";
            datas.add(item);
        }

        {
            CategoryData item = new CategoryData();
            item.text = "Kunqu Opera can trace its origins back to the Ming Dynasty about six hundred years ago, and a small town south of the Yangtze, called Kunshan.";
            item.image = R.mipmap.k2 + "";
            item.label = "FC";
            datas.add(item);
        }

        {
            CategoryData item = new CategoryData();
            item.text = "The popularity of Kunqu Opera historically has a lot to do with the support it received from the imperial court, from the time of Emperor Kangxi, onwards.";
            item.image = R.mipmap.k3 + "";
            item.label = "NFC";
            datas.add(item);
        }

        {
            CategoryData item = new CategoryData();
            item.text = "Perhaps the greatest masterpiece of Kunqu Opera is “The Peony Pavilion” written by Tang Xianzu in the early years of the 17th century.";
            item.image = R.mipmap.k4 + "";
            item.label = "GBA";
            datas.add(item);
        }

        {
            CategoryData item = new CategoryData();
            item.text = "Kunqu Opera is remarkable, if for nothing else, because of the long time it has been around. It continues to exert a strong appeal today.";
            item.image = R.mipmap.k5 + "";
            item.label = "PS";
            datas.add(item);
        }

        {
            CategoryData item = new CategoryData();
            item.text = "In the late Qing Dynasty, after five hundred years of development, Kunqu faced the greatest crisis in its existence.";
            item.image = R.mipmap.k6 + "";
            item.label = "XBOX";
            datas.add(item);
        }


        {
            CategoryData item = new CategoryData();
            item.text = "During the Qing Dynasty that followed, it became so popular that it was said to have an influence at every level of society, from the imperial court, down.";
            item.image = R.mipmap.k7 + "";
            item.label = "GB";
            datas.add(item);
        }


        {
            CategoryData item = new CategoryData();
            item.text = "The Qing Dynasty’s greatest playwrights, are Hong Sheng and Kong Shangren, who wrote, respectively, “The Palace of Eternal Youth” and “The Peach Blossom Fan”.";
            item.image = R.mipmap.k8 + "";
            item.label = "N64";
            datas.add(item);
        }


        {
            CategoryData item = new CategoryData();
            item.text = "Every period of history has its own fashions and tastes in clothing, music, etc. During the Ming Dynasty, it was fashionable among the intelligentsia to enjoy Kunqu Opera.";
            item.image = R.mipmap.k9 + "";
            item.label = "PSP";
            datas.add(item);
        }

        {
            CategoryData item = new CategoryData();
            item.text = "Kunqu Opera can trace its origins back to the late Ming Dynasty and a small town south of the Yangtze, called Kunshan.";
            item.image = R.mipmap.k10 + "";
            item.label = "NDS";
            datas.add(item);
        }

    }

}
