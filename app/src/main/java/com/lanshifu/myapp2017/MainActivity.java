package com.lanshifu.myapp2017;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lanshifu.myapp2017.view.popumenu.PoPItem;
import com.lanshifu.myapp2017.view.popumenu.PopMenu;

import java.util.ArrayList;
import java.util.List;

import library.lanshifu.com.lsf_library.base.BaseToolBarActivity;

public class MainActivity extends BaseToolBarActivity {

    private static final String TAG = "MainActivity";
    private PopMenu mPopMenu;

    @Override
    protected int getLayoutid() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewCreate() {

        findViewById(R.id.btn_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopMenu == null) {
                    initMenu();
                }
                if (!mPopMenu.isShowing()) {
                    mPopMenu.show();
                }


            }
        });


        findViewById(R.id.btn_menu2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: menu2");

            }
        });
    }


    private void initMenu() {
        mPopMenu = new PopMenu.Builder(MainActivity.this)
                .addMenuItem(new PoPItem(MainActivity.this, "菜单1", R.mipmap.tabbar_compose_idea))
                .addMenuItem(new PoPItem(MainActivity.this, "菜单2", R.mipmap.tabbar_compose_photo))
                .addMenuItem(new PoPItem(MainActivity.this, "菜单3", R.mipmap.tabbar_compose_headlines))
                .addMenuItem(new PoPItem(MainActivity.this, "菜单4", R.mipmap.tabbar_compose_lbs))
                .addMenuItem(new PoPItem(MainActivity.this, "菜单5", R.mipmap.tabbar_compose_lbs))
                .addMenuItem(new PoPItem(MainActivity.this, "菜单6", R.mipmap.tabbar_compose_more))
                .setOnPopMenuItemListener(new PopMenu.PopMenuItemListener() {
                    @Override
                    public void onItemClick(PopMenu popMenu, int position) {
                        Toast.makeText(MainActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
    }


    private List<String> getList() {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("数据" + i);
        }
        return list;
    }


    class MyRVAdapter extends RecyclerView.Adapter {

        private final List<String> list;

        public MyRVAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.list_item, null);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TextView textView = (TextView) holder.itemView.findViewById(R.id.text);
            textView.setText(list.get(position));

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }


    class MyHolder extends RecyclerView.ViewHolder {

        public MyHolder(View itemView) {
            super(itemView);
        }
    }
}
