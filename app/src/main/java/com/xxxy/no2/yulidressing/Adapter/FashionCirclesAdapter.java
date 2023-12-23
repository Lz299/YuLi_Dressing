package com.xxxy.no2.yulidressing.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.xxxy.no2.yulidressing.R;

import java.util.List;
import java.util.Map;
//定义适配器
public class FashionCirclesAdapter extends RecyclerView.Adapter<FashionViewHolder> {
    private List<Map<String,Object>> myList;
    private Context mContext;
    private LayoutInflater mInflater;

    public FashionCirclesAdapter(List<Map<String, Object>> myList, Context mContext) {
        this.myList = myList;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public FashionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //这就是那个盘子
        View view = mInflater.inflate(R.layout.fashion_ircles_item, parent, false);//物理托盘和逻辑托盘对应
        FashionViewHolder holder = new FashionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FashionViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //盘子里东西
        Map<String, Object> map = myList.get(position);
        holder.itemTitle.setText(map.get("title").toString());
        holder.itemInfo.setText(map.get("info").toString());
        holder.itemTime.setText(map.get("time").toString());
        holder.itemImage.setImageResource((int) map.get("image"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,myList.get(position).get("title").toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
class FashionViewHolder extends RecyclerView.ViewHolder {
    TextView itemTitle,itemInfo,itemTime;
    ImageView itemImage;
    public FashionViewHolder(@NonNull View itemView) {
        super(itemView);
        itemTitle = itemView.findViewById(R.id.textView_3_1);
        itemInfo = itemView.findViewById(R.id.textView_3_2);
        itemTime = itemView.findViewById(R.id.textView_3_3);
        itemImage = itemView.findViewById(R.id.image_3_1);

    }

}
