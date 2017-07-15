package com.example.sharpcj.lvjitourtest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolBar;
    private RecyclerView mRecyclerViewTest;
    private List<String> mList;
    private ConvenientBanner mConvenientBanner;
    private List<Integer> mListData;
    private int[] mImgs = new int[]{
            R.mipmap.penguins,
            R.mipmap.jellyfish,
            R.mipmap.desert,
            R.mipmap.koala,
            R.mipmap.hydrangeas
    };
    private ImageView mIvScanner;
    private ImageView mIvSearch;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        init();
        initViews();
    }

    private void findViews() {
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mRecyclerViewTest = (RecyclerView) findViewById(R.id.recyclerViewTest);
        mConvenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
        mIvScanner = (ImageView) findViewById(R.id.iv_scanner);
        mIvSearch = (ImageView) findViewById(R.id.iv_search);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
    }

    private void init() {
        mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mList.add("测试数据-----" + i);
        }

        mListData = new ArrayList<>();
        for (int i = 0; i < mImgs.length; i++) {
            mListData.add(mImgs[i]);
        }
    }

    private void initViews() {
        initConvenientBanner();
        initRecylerView();
        mToolBar.setAlpha(0);
        mIvSearch.setVisibility(View.INVISIBLE);
        mIvSearch.setOnClickListener(this);
        mIvScanner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search:
                Toast.makeText(this, "搜索一下", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_scanner:
                Toast.makeText(this, "扫一扫", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initConvenientBanner() {
        mConvenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {

            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, mListData)
                .setPointViewVisible(true)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.pager_not_selected_night_mode, R.mipmap.pager_selected_night_mode})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
//        convenientBanner.setManualPageable(false);//设置不能手动影响
    }

    private void initRecylerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewTest.setLayoutManager(linearLayoutManager);
        mRecyclerViewTest.setAdapter(new RecyclerView.Adapter<MyHolder>() {

            @Override
            public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_list_item_1, null, false);
                return new MyHolder(view);
            }

            @Override
            public void onBindViewHolder(MyHolder holder, int position) {
                holder.textView.setText("测试数据-----" + position);
            }

            @Override
            public int getItemCount() {
                return mList.size();
            }
        });
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }

    class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
        }

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mConvenientBanner.startTurning(3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mConvenientBanner.stopTurning();
    }
}
