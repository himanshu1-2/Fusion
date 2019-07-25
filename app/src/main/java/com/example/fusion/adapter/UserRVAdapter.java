package com.example.fusion.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fusion.R;
import com.example.fusion.model.UsersData;
import com.example.fusion.R;
import com.example.fusion.model.UsersData;

import java.util.ArrayList;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.MviewHolder> {
    ArrayList<UsersData> userArrayList=new ArrayList<>();
    OnDelListner onDelListner;
Boolean flag;
    public UserRVAdapter(ArrayList list, OnDelListner onDelListner, Boolean flag) {
        this.onDelListner = onDelListner;
        this.userArrayList = list;
        this.flag=flag;


    }

    @NonNull
    @Override
    public MviewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.griditem_userdata, viewGroup, false);
        View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_userdata, viewGroup, false);
        if(flag)
        return new MviewHolder(view);
        else
            return new MviewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull final MviewHolder mviewHolder, final int i) {

         mviewHolder.txtName.setText(userArrayList.get(i).getName());

        mviewHolder.txtDesignation.setText(userArrayList.get(i).getDesignation());
        mviewHolder.btnEdit.setTag(i);
        mviewHolder.btnDelete.setTag(i);
        mviewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=(int)v.getTag();
                if (onDelListner != null)
                    onDelListner.onDelClick(position);
            }
        });
        mviewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDelListner != null)
                    onDelListner.OnEdit(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class MviewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDesignation;
        Button btnEdit, btnDelete;
        OnDelListner onDelListner;

        public MviewHolder(@NonNull final View itemView) {
            super(itemView);
            if(itemView!=null)
                btnEdit = itemView.findViewById(R.id.btn_listitem_userdata_edit);
            btnDelete = itemView.findViewById(R.id.btn_listitem_userdata_delete);
            txtName = itemView.findViewById(R.id.txt_listitem_userdata_name);
            txtDesignation = itemView.findViewById(R.id.txt_listitem_userdata_desgination);

        }
    }

    public interface OnDelListner {
        void onDelClick(int position);
        void OnEdit(int postion);
    }
}
