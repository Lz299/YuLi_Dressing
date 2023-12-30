package com.xxxy.no2.yulidressing.Adapter;

import static androidx.core.content.ContextCompat.createDeviceProtectedStorageContext;
import static androidx.core.content.ContextCompat.getAttributionTag;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.xxxy.no2.yulidressing.GiveActivity;
import com.xxxy.no2.yulidressing.MorepagesActivity;
import com.xxxy.no2.yulidressing.R;

import java.util.List;
import java.util.Map;

public class PersonalAdapter extends RecyclerView.Adapter<PersonalHolder> {

    private List<Map<String, Object>> data;
    private Context context;
    public int Countnum;

    public PersonalAdapter(List<Map<String, Object>> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public PersonalAdapter() {

    }

    @NonNull
    @Override
    public PersonalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.personal_item, parent, false);
        return new PersonalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalHolder holder, int position) {
        Map<String, Object> map = data.get(position);
        int image = (int) map.get("image");
        holder.itemImage.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

class PersonalHolder extends RecyclerView.ViewHolder {

    PersonalAdapter pd  =  new PersonalAdapter();

    ImageView itemImage;
    public PersonalHolder(@NonNull View itemView) {
        super(itemView);
        itemImage = itemView.findViewById(R.id.rv_item_image);
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //Step 1：创建AlertDialog.Builder对象；
                AlertDialog.Builder builder =  new AlertDialog.Builder(view.getContext());
                //Step 2：调用setIcon()设置图标，setTitle()或setCustomTitle()设置标题；
                builder.setIcon(R.drawable.logo);
                builder.setTitle("友情提示");

                //Step 3：设置对话框的内容：setMessage()，还有其他方法来指定显示的内容setItems(),setSingleChoiceItems(),setMutilChoiceItems(),setView(),setAdapter()；
                builder.setMessage("请对选择衣物进行操作");
                //Step 4：调用setPositive/Negative/NeutralButton()设置：确定，取消，中立按钮；
                builder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //删除操作


                        Toast.makeText(view.getContext(), "已删除", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("捐赠", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                         //捐赠操作
                        Intent intent=new Intent(view.getContext(),GiveActivity.class);//你要跳转的界面

                        view.getContext().startActivity(intent);

//                        pd.Countnum++;
//                        Toast.makeText(view.getContext(), "已添加倒捐赠列表--件数:"+ pd.Countnum,+Toast.LENGTH_SHORT).show();
                    }
                });

                //Step 5：调用create()方法创建这个对象，再调用show()方法将对话框显示出来；
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return false;
            }
        });

    }

}