package com.example.lab2.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2.MainActivity;
import com.example.lab2.R;
import com.example.lab2.dao.ToDoDao;
import com.example.lab2.model.ToDoModel;

import java.util.ArrayList;

public class ToDo_Adapter extends RecyclerView.Adapter<MyViewHolder> {

    private final Context context;
    private ArrayList<ToDoModel> list;

    private final ToDoDao toDoDao;

    public ToDo_Adapter(Context context, ArrayList<ToDoModel> list, ToDoDao toDoDao) {
        this.context = context;
        this.list = list;
        this.toDoDao = toDoDao;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((MainActivity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_rcv_todo, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvContent.setText(list.get(position).getTitle());
        holder.tvDate.setText(list.get(position).getDate());
        if (list.get(position).getType() == 1) {
            holder.chk.setChecked(true);
            holder.tvContent.setPaintFlags(holder.tvContent.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        } else {
            holder.chk.setChecked(false);
            holder.tvContent.setPaintFlags(holder.tvContent.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        holder.iv_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = list.get(holder.getAdapterPosition()).getId();
                boolean check = toDoDao.delete(id);
                if (check){
                    Toast.makeText(context, "xoa thanh cong", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list = toDoDao.getAll();
                    notifyItemRemoved(holder.getAdapterPosition());
                }else{
                    Toast.makeText(context, "xoa that bai !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int id = list.get(holder.getAdapterPosition()).getId();
                boolean check = toDoDao.updateStatus(id, holder.chk.isChecked());
                if (check){
                    Toast.makeText(context, "da update status", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list = toDoDao.getAll();
                    notifyDataSetChanged();


                }else {
                    Toast.makeText(context, "update status that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
