package com.lanshifu.myapp2017.twolist;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lanshifu.myapp2017.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import library.lanshifu.com.lsf_library.adapter.recyclerview.CommonAdapter;
import library.lanshifu.com.lsf_library.adapter.recyclerview.base.ViewHolder;
import library.lanshifu.com.lsf_library.base.BaseToolBarActivity;

public class TwoListActivity extends BaseToolBarActivity {

    private static final String KEY_ = "";
    @Bind(R.id.rv_sort)
    RecyclerView mRecyclerView;
    private RvAdapter<String> adapter;
    private ShotDetailFragment fragment;

    //
    @Override
    protected int getLayoutid() {
        return R.layout.activity_two_list;
    }

    @Override
    protected void onViewCreate() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RvAdapter<String>(this, R.layout.item_classify_detail, getData());
        mRecyclerView.setAdapter(adapter);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragment = new ShotDetailFragment();
        fragmentTransaction.add(R.id.lin_fragment, fragment);
        fragmentTransaction.commit();


    }


    public void check(int position, boolean isScroll) {
        adapter.setcheck(position);
        mRecyclerView.scrollToPosition(position);

        if(isScroll){
            fragment.setData(position * 10 + position);
        }
    }


    private List<String> getData() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 15; i++) {
            list.add("标题" + i);
        }
        return list;
    }


     class RvAdapter<String> extends CommonAdapter{

        protected int checkPosition = -1;
        public void setcheck(int position){
            checkPosition = position;
            notifyDataSetChanged();
        }

        public RvAdapter(Context context, int layoutId, List<String> datas) {
            super(context, layoutId, datas);
        }

         @Override
         protected void convert(ViewHolder holder, Object o, final int position) {
             holder.setText(R.id.tv_name, o.toString());
             if(checkPosition == position){
                 holder.setImageResource(R.id.ivAvatar,R.mipmap.tabbar_compose_photo);
             }else {
                 holder.setImageResource(R.id.ivAvatar,R.mipmap.ic_launcher);
             }

             holder.setOnClickListener(R.id.content, new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     check(position, true);
                 }
             });


         }
     }

}
