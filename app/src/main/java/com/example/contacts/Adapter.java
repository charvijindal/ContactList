package com.example.contacts;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private final  RecyclerViewInterface recyclerViewInterface;
    private List<ModelClass> userList;
    Activity activity;

    public Adapter(Activity activity, List<ModelClass> userList, RecyclerViewInterface recyclerViewInterface){
        this.userList = userList;
        this.activity = activity;
        this.recyclerViewInterface = recyclerViewInterface;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.block_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
//        String name = userList.get(position).getContact1();
//        String number = userList.get(position).getContact2();
//
//        holder.setData(name, number);

        ModelClass model = userList.get(position);
        holder.contact1.setText(model.getContact1());
        holder.contact2.setText(model.getContact2());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView contact1;
        private TextView contact2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            contact1 = itemView.findViewById(R.id.contact1);
            contact2 = itemView.findViewById(R.id.contact2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos!= RecyclerView.NO_POSITION){
                            recyclerViewInterface.OnItemClick(pos);
                        }
                    }
                }
            });
        }

        public void setData(String name, String number) {
            contact1.setText(name);
            contact2.setText(number);
        }
    }
}
