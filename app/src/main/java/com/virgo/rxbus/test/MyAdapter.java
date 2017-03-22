package com.virgo.rxbus.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.virgo.rxbus.R;
import com.virgo.rxbus.rxbus.Events;
import com.virgo.rxbus.rxbus.RxBus;
import com.virgo.rxbus.rxbus.UserEvent;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/22.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public List<String> mData;
    private Context mContext;





    public MyAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view, null);
        final ViewHolder vh = new ViewHolder(view);
        RxView.clicks(view)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        RxBus.getInstance().send(Events.EVENT_CODE_MY, new UserEvent(mData.get(vh.getAdapterPosition()),vh.getAdapterPosition()));
                    }
                });

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

      private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }

        public void setData(String data) {
            textView.setText(data);
        }
    }
}
