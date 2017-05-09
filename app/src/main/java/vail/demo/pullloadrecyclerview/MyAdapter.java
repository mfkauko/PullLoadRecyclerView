package vail.demo.pullloadrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by VailWei on 2017/5/9/009.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        MyHolder holder = new MyHolder(view);
        holder.txt = (TextView) view.findViewById(R.id.item_text);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.txt.setText("position "  + (position + 1));
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        public MyHolder(View itemView) {
            super(itemView);
        }

        public TextView txt;
    }
}
