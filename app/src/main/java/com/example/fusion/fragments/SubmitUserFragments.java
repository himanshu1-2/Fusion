package com.example.fusion.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fusion.MainPageActivity;
import com.example.fusion.R;
import com.example.fusion.model.DatabaseEntity;

public class SubmitUserFragments extends Fragment {

    Button btnAdd;
    Spinner spDesignationList;
    DatabaseEntity db;
    EditText etName;
    private String desgination;
    private String name;
    private ArrayAdapter<String> desginationList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_submituser, container, false);
        spDesignationList = view.findViewById(R.id.sp_fragment_submituser_desgination);
        btnAdd = view.findViewById(R.id.btn_fragment_submituser_add);
        etName = view.findViewById(R.id.et_fragment_submituser_name);

        db=new DatabaseEntity(getActivity());

        String []designation = {Constant.android,Constant.Ios,Constant.Backend,Constant.Python,Constant.QA};

        desginationList = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, designation);
        spDesignationList.setAdapter(desginationList);

        Bundle bundle=getArguments();
        if(bundle!=null)
        { name=bundle.getString(getString(R.string.name));
        desgination=bundle.getString(getString(R.string.desgination));


        etName.setText(name);}

        if (desgination != null) {
            switch (desgination) {
                case Constant.android :
                    spDesignationList.setSelection(0);
                    break;
                case Constant.Backend:
                    spDesignationList.setSelection(2);
                    break;
                case Constant.Python:
                    spDesignationList.setSelection(3);
                    break;
                case Constant.Ios:
                    spDesignationList.setSelection(1);
                    break;
                case Constant.QA:
                    spDesignationList.setSelection(4);
                    break;
            }
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = etName.getText().toString();

                String desgination1 = spDesignationList.getSelectedItem().toString();
                Log.d("him", "des1:"+desgination1);
                Log.d("him", "des:"+desgination);
                if (name != null && validate(name1)) {
                    db.onUpdate(name, name1);
                    db.onUpdatedes(desgination,desgination1);
                    ((MainPageActivity) getActivity()).openFragment(new AddUserFragment());


                } else if (validate(name1)) {
                    db.addName(name1, desgination1);
                    ((MainPageActivity) getActivity()).openFragment(new AddUserFragment());

                }

            }

        });

        return view;
    }

    public boolean validate(String name1) {
        if (name1.length() == 0)
            Toast.makeText(getActivity(), "name cannot be blank", Toast.LENGTH_SHORT).show();
        else
            return true;

        return false;
    }



    }



