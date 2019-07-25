package com.example.fusion;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    TextView txtName;

    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName=findViewById(R.id.txt_activity_main_name);
        etUsername = findViewById(R.id.et_activity_main_name);
        etPassword = findViewById(R.id.et_activity_main_password);
        btnLogin = findViewById(R.id.btn_activity_main_login);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginActivity.this,MainPageActivity.class);

                startActivity(intent);

            }
        });







    }





    boolean vaildation(String name, String password) {
        if (name.length() < 6)
            Toast.makeText(LoginActivity.this, " Username should br minimum 6 character", Toast.LENGTH_SHORT).show();
        else if (password.length() < 6)
            Toast.makeText(LoginActivity.this, "Minimum 6 character password", Toast.LENGTH_SHORT).show();
        else if (!name.equals(password))
            Toast.makeText(LoginActivity.this, "Username and password must be same", Toast.LENGTH_SHORT).show();
        else if (name.equals(password))
            return true;
        return false;
    }

    public void openFragment(Fragment f) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_activity_main_root, f).commit();
    }

}
