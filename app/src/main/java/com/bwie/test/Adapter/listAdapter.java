package com.bwie.test.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.test.Bean.News;
import com.bwie.test.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * User: 张亚博
 * Date: 2017-08-30 09:17
 * Description：
 */
public class listAdapter extends BaseAdapter {
    public static final int TYPE_A=0;
    public static final int TYPE_B=1;
    private List<News.ResultBean.DataBean> list;
    private Context context;

    public listAdapter(List<News.ResultBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0) {
            return TYPE_A;
        }else{
            return TYPE_B;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
        ViewHolder holder = null;
        if (view==null) {
            switch (itemViewType){
                case TYPE_A:
                    view=View.inflate(context, R.layout.listview_item,null);
                    holder=new ViewHolder();
                    holder.imageView=view.findViewById(R.id.iv);
                    holder.tv_data=view.findViewById(R.id.tv_data);
                    holder.tv_title=view.findViewById(R.id.tv_title);
                    view.setTag(holder);
                    break;
                case TYPE_B:
                    view=View.inflate(context, R.layout.listview_item_h,null);
                    holder=new ViewHolder();
                    holder.imageView=view.findViewById(R.id.iv_h);
                    holder.tv_data=view.findViewById(R.id.tv_data_h);
                    holder.tv_title=view.findViewById(R.id.tv_title_h);
                    view.setTag(holder);
                    break;
            }

        }else{
            holder= (ViewHolder) view.getTag();
        }

        News.ResultBean.DataBean dataBean = list.get(i);
        holder.tv_title.setText(dataBean.getTitle());
        holder.tv_data.setText(dataBean.getDate());
        ImageLoader.getInstance().displayImage(dataBean.getThumbnail_pic_s(),holder.imageView);

        return view;
    }


    class ViewHolder{
        public TextView tv_title;
        public TextView tv_data;
        public ImageView imageView;
    }
}
