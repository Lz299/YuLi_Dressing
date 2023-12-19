package com.xxxy.no2.yulidressing.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xxxy.no2.yulidressing.R;

import java.util.List;
import java.util.Map;

public class PersonalAdapter4 extends RecyclerView.Adapter<PersonalHolder4> {

    private List<Map<String, Object>> data4;
    private Context context;

    public PersonalAdapter4(List<Map<String, Object>> data4, Context context) {
        this.data4 = data4;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonalHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.personal_item, parent, false);
        return new PersonalHolder4(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalHolder4 holder, int position) {
        Map<String, Object> map = data4.get(position);
        int image = (int) map.get("image");
        holder.itemImage.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return data4.size();
    }

}

class PersonalHolder4 extends RecyclerView.ViewHolder {

    ImageView itemImage;
    public PersonalHolder4(@NonNull View itemView) {
        super(itemView);
        itemImage = itemView.findViewById(R.id.rv_item_image);

    }

}