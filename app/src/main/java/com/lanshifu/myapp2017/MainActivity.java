package com.lanshifu.myapp2017;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lanshifu.myapp2017.twolist.TwoListActivity;
import com.lanshifu.myapp2017.view.popumenu.PoPItem;
import com.lanshifu.myapp2017.view.popumenu.PopMenu;
import com.lanshifu.myapp2017.window.WindowService;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
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

        String res = doSomeThing();
        showShortToast(res);
    }

    private String doSomeThing() {

        return "doSomeThing";

    }


    private void initMenu() {
        mPopMenu = new PopMenu.Builder(MainActivity.this)
                .addMenuItem(new PoPItem(MainActivity.this, "双列表", R.mipmap.tabbar_compose_idea))
                .addMenuItem(new PoPItem(MainActivity.this, "菜单2", R.mipmap.tabbar_compose_photo))
                .addMenuItem(new PoPItem(MainActivity.this, "菜单3", R.mipmap.tabbar_compose_headlines))
                .addMenuItem(new PoPItem(MainActivity.this, "菜单4", R.mipmap.tabbar_compose_lbs))
                .addMenuItem(new PoPItem(MainActivity.this, "菜单5", R.mipmap.tabbar_compose_lbs))
                .addMenuItem(new PoPItem(MainActivity.this, "菜单6", R.mipmap.tabbar_compose_more))
                .setOnPopMenuItemListener(new PopMenu.PopMenuItemListener() {
                    @Override
                    public void onItemClick(PopMenu popMenu, int position) {
                        switch (position) {
                            case 0:
                                startActivity(new Intent(MainActivity.this, TwoListActivity.class));
                                break;
                        }
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

    @OnClick({R.id.btn_menu, R.id.btn_menu2, R.id.btn_seeClass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_menu:

                if (mPopMenu == null) {
                    initMenu();
                }
                if (!mPopMenu.isShowing()) {
                    mPopMenu.show();
                }

                break;
            case R.id.btn_menu2:
                break;
            case R.id.btn_seeClass:
                requestAlertWindowPermission();
                break;
        }
    }


    private Intent intent;
    private static final int REQUEST_CODE = 1;
    private  void requestAlertWindowPermission() {
        //修改
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.canDrawOverlays(this)) {
                    Log.i("111", "onActivityResult success");
                    String settings = Settings.ACTION_ACCESSIBILITY_SETTINGS;
                    startActivity(new Intent(settings));

                    //联系人 一段时间
//                    intent = new Intent(this,WindowService.class);
//                    startService(intent);
                }
            }
        }
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
