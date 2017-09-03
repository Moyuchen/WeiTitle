package com.bwie.test.HorizontalScrollView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * User: 张亚博
 * Date: 2017-08-30 19:19
 * Description：
 */
public class HorizontalScrollTabhost extends LinearLayout implements ViewPager.OnPageChangeListener {
private Context mContext;
    private HorizontalScrollView hscrollview;
    private ViewPager viewpager;
    private LinearLayout mMenulayout;
    private List<String> mListBean;
    private List<Fragment> mListFragment;
    private int count;
    private List<TextView> topviews;
    private int mBcolor;
    private HorizontalScrollTabhost.viewpageradapter viewpageradapter;

    public HorizontalScrollTabhost(Context context) {
       this(context,null);

    }

    public HorizontalScrollTabhost(Context context, @Nullable AttributeSet attrs) {
      this(context,attrs,0);
    }

    public HorizontalScrollTabhost(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        init(context,attrs);
    }

    /**
     * 初始化自定义属性和view
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalScrollView);
        //获取颜色属性，白色
        //白色
        mBcolor = typedArray.getColor(R.styleable.HorizontalScrollView_top_background, 0xffffff);
        //将typeArray回收
        typedArray.recycle();
        //初始化view
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        //获取布局视图view
        View view = LayoutInflater.from(mContext).inflate(R.layout.horizontalscrollview, this, true);

        //获取水平滑动view
        hscrollview = view.findViewById(R.id.horizontalScrollView);
        //获取控件viewpager
        viewpager = view.findViewById(R.id.viewpager_fragment);

        //给viewpager设置页面切换监听
        viewpager.addOnPageChangeListener(this);
        //获取标题布局
        mMenulayout = view.findViewById(R.id.layout_menu);
    }
public void diaplay(List<String> listbean, List<Fragment> listfragment){
    this.mListBean=listbean;
    this.mListFragment=listfragment;
    this.count=listbean.size();
    topviews=new ArrayList<>(count);
    drawUi();


}

    /**
     * 绘制页面所有元素
     */
    private void drawUi() {
        drawHorizontal();
        drawViewPager();

    }

    /**
     * 绘制viewpager
     */
    private void drawViewPager() {
        viewpageradapter = new viewpageradapter(((FragmentActivity) mContext).getSupportFragmentManager());
        viewpager.setAdapter(viewpageradapter);
    }

    /**
     * 绘制横向滑动菜单
     */
    private void drawHorizontal() {
        //给标题菜单menu设置背景颜色
//        mMenulayout.setBackgroundColor(mBcolor);

        for (int i = 0; i <count ; i++) {
            String s = mListBean.get(i);
            //获取菜单控件textview
            final TextView tv = (TextView) View.inflate(mContext, R.layout.menu_top_item, null);
            tv.setText(s);
            final int finali=i;
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    //点击移动当前fragment
                    viewpager.setCurrentItem(finali);
                    //点击让文字居中
                    moveItemToCenter(tv);
                }
            });
            //将tv控件添加到菜单布局中
            mMenulayout.addView(tv);
            //将tv控件添加到菜单集合中，topviews用来储存textview菜单的
            topviews.add(tv);
        }
        //默认第一项为选中
        topviews.get(0).setSelected(true);
    }

    /**
     * 移动view对象到中间
     * @param tv
     */
    private void moveItemToCenter(TextView tv) {
        //获取屏幕参数对象
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        //获取屏幕宽度
        int screenWidth = displayMetrics.widthPixels;

        int[] locations=new int[2];
//        一个控件在其父窗口中的坐标位置(以父窗口左上角为原点)
        tv.getLocationInWindow(locations);

        int rwidth = tv.getWidth();
        //将控件到指定坐标
        hscrollview.smoothScrollBy((locations[0] + rwidth / 2 - screenWidth / 2),
                0);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mMenulayout!=null&&mMenulayout.getChildCount()>0) {
            for (int i = 0; i <mMenulayout.getChildCount() ; i++) {
                if (i==position){
                    mMenulayout.getChildAt(i).setSelected(true);
                }else{
                    mMenulayout.getChildAt(i).setSelected(false);
                }
            }
        }
        //移动view，水平居中
        moveItemToCenter(topviews.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * viewpager适配器
     */
    class viewpageradapter extends FragmentPagerAdapter {

        public viewpageradapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return mListFragment.get(position);
        }

        @Override
        public int getCount() {
            return mListFragment.size();
        }
    }
}
