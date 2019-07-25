package com.example.fusion;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.Toast;

import com.example.fusion.fragments.AddUserFragment;
import com.example.fusion.fragments.SubmitUserFragments;

import java.util.Calendar;

public class MainPageActivity extends AppCompatActivity {
DatePicker datePicker;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
datePicker=findViewById(R.id.dp_activity_mainpage_attendence);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       final NavigationView navigationView =findViewById(R.id.nav1);
        drawer=findViewById(R.id.drawer_layout);
 navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
     @Override
     public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
         switch(menuItem.getItemId())
         {
             case R.id.leave:

                 openFragment(new AddUserFragment());
             navigationView.setCheckedItem(R.id.leave);
             Toast.makeText(MainPageActivity.this, "g", Toast.LENGTH_SHORT).show();

                break;

             case R.id.approve:
                 break;
         }


         return false;
     }
 });

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
       drawer.addDrawerListener(toggle);
       toggle.syncState();


        Calendar calendar = Calendar.getInstance();

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
               popup();

            }
        });


    }







     void popup()
    {

        Dialog dialog=new Dialog();
        dialog.show(getSupportFragmentManager(),"popup ");




    }


    public void openFragment(Fragment f) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_activity_main_root, f).commit();
    }
}
