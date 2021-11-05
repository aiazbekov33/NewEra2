package com.example.newera.ui.create;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newera.databinding.ItemTaskBinding;
import com.example.newera.inter.OnItemClickListener;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    ArrayList<TaskModel> list;
    ItemTaskBinding binding;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TaskAdapter(ArrayList<TaskModel> list) {
        this.list = list;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void delete(ArrayList<TaskModel> list) {
        this.list=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TaskViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.onFill(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class TaskViewHolder extends RecyclerView.ViewHolder {
        ItemTaskBinding binding;
        View.OnClickListener onClick;

        public TaskViewHolder(ItemTaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void onFill(TaskModel model) {
            binding.titleTv.setText(model.title);
            binding.timeTv.setText(model.time);
            binding.leftColorV.setBackgroundColor(model.color);
           itemView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {
                   onItemClickListener.onItemClick(model,getAdapterPosition());
                   return false;
               }
           });
            Glide.with(binding.taskIm).load(model.image).centerCrop().into(binding.taskIm);
        }
    }

//    public void setOnClick (OnClick onClick){
//        this.onClick = onClick;
//    }


}