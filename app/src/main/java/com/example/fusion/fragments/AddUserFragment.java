package com.example.fusion.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.fusion.MainPageActivity;
import com.example.fusion.R;
import com.example.fusion.adapter.UserRVAdapter;
import com.example.fusion.model.DatabaseEntity;
import com.example.fusion.model.UsersData;

import java.util.ArrayList;

public class AddUserFragment extends Fragment implements UserRVAdapter.OnDelListner {
    FloatingActionButton fabAdd;
    DatabaseEntity db;
    Button btnChange;

    RecyclerView.LayoutManager layoutManager;
    UserRVAdapter rvAdapter;
    ArrayList<UsersData> userDataArrayList;
    UsersData userData;
    RecyclerView rvUserList;
 public boolean flag=false;
UserRVAdapter.OnDelListner onDelListner;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_userlist, container, false);

        userData = new UsersData();
         db=new DatabaseEntity(getActivity());


        rvUserList = view.findViewById(R.id.rv_activity_main_userlist);
fabAdd=view.findViewById(R.id.fab_activity_main_add);
btnChange=view.findViewById(R.id.btn_fragment_submituser_changeview);




        layoutManager  = new LinearLayoutManager(getActivity());
        rvUserList.setLayoutManager(layoutManager);
        userDataArrayList = new ArrayList<>();
        userDataArrayList = db.getUser();
        rvAdapter = new UserRVAdapter(userDataArrayList, (UserRVAdapter.OnDelListner) this,flag);
        rvUserList.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();



btnChange.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        if(flag)
        {
            GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
            rvUserList.setLayoutManager(gridLayoutManager);
            rvAdapter = new UserRVAdapter(userDataArrayList, (UserRVAdapter.OnDelListner) onDelListner,flag);
            rvUserList.setAdapter(rvAdapter);

            btnChange.setText(getString(R.string.listview));
            flag=false;
        }
        else {
            layoutManager = new LinearLayoutManager(getActivity());
            rvUserList.setLayoutManager(layoutManager);
            rvAdapter = new UserRVAdapter(userDataArrayList, (UserRVAdapter.OnDelListner) onDelListner,flag);
            rvUserList.setAdapter(rvAdapter);
            btnChange.setText(getString(R.string.gridview));

            flag=true;

        }
    }
});

if(userDataArrayList.size()==0)
    btnChange.setVisibility(View.GONE);

    fabAdd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainPageActivity) getActivity()).openFragment(new SubmitUserFragments());


        }
    });
        return view;
    }



    @Override
    public void onDelClick(int position) {
        db.onDelete(userDataArrayList.get(position).getName());
        if (userDataArrayList != null)
        {
            Log.d("him", "onDelClick: "+userDataArrayList.size());
        userDataArrayList.remove(position);
        rvAdapter.notifyDataSetChanged();}

    }

    @Override
    public void OnEdit(int postion) {
        if (userDataArrayList != null) {
            String name = userDataArrayList.get(postion).getName();
            String desgination = userDataArrayList.get(postion).getDesignation();
            Bundle bundle = new Bundle();
            bundle.putString(getString(R.string.name), name);
            bundle.putString(getString(R.string.desgination), desgination);
            SubmitUserFragments submitUserFragments = new SubmitUserFragments();
            submitUserFragments.setArguments(bundle);
            ((MainPageActivity) getActivity()).openFragment(submitUserFragments);
        }
    }
}
