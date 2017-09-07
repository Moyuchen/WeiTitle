package com.bwie.test.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.test.Bean.NewsType;
import com.bwie.test.R;

import java.util.List;

/**
 * User: 张亚博
 * Date: 2017-09-05 14:33
 * Description：
 */
public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder> {
    private Context context;
    private List<NewsType> list;
    private OnRecycleViewItemClickedListener OnRecycleViewItemClickListener;

    public recycleAdapter(Context context, List<NewsType> list) {
        this.context = context;
        this.list = list;
    }

    /**
     * 创建viewholder,和view绑定
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recycle_item, null);
        MyViewHolder myholder=new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnRecycleViewItemClickListener.onRecycleViewItem((Integer) view.getTag(),view);
            }
        });
        return myholder;
    }

    /**
     * 处理逻辑
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.recy_tv.setText(list.get(position).newstype);
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView recy_tv;
        private final CheckBox recy_checkb;

        public MyViewHolder(View itemView) {
            super(itemView);
            recy_tv = itemView.findViewById(R.id.recy_tv);
            recy_checkb = itemView.findViewById(R.id.recy_checkb);
        }
    }

    public void setOnRecycleViewItemClickListener(OnRecycleViewItemClickedListener onRecycleViewItemClickListener) {
        this.OnRecycleViewItemClickListener = onRecycleViewItemClickListener;
    }
/*
        自定义的回调接口
 */
    public interface OnRecycleViewItemClickedListener{

       void onRecycleViewItem(int positon,View view);
    }
}
