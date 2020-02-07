package com.example.carbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carbuddy.database.DatabaseManager;
import com.example.carbuddy.database.dao.UserDao;
import com.example.carbuddy.database.model.User;
import com.example.carbuddy.util.SharedPreferencess;

public class LogInActivity extends AppCompatActivity {
    private Button registerLogIn ;
    private Button btnLogIn;
    private EditText et_email;
    private EditText et_parola;
    private SharedPreferencess shPref;

    private DatabaseManager database;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        shPref = new SharedPreferencess(getApplicationContext());
        database = Room.databaseBuilder(this, DatabaseManager.class, "iua_db")
                .allowMainThreadQueries()
                .build();

        userDao = database.getUserDao();

        initComponents1();

    }
    private void initComponents1() {
        registerLogIn = findViewById(R.id.buttonLogIn_register);
        et_email = findViewById(R.id.et_email);
        et_parola = findViewById(R.id.et_parola);
        btnLogIn = findViewById(R.id.btnLogIn);
        registerLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                Intent intent3 = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent3);

            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emptyFields()) {
                    Toast.makeText(LogInActivity.this, R.string.string_fillFields, Toast.LENGTH_LONG).show();
                }
                else{
                    /// !!!!!!
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user = userDao.getUser(et_email.getText().toString(), et_parola.getText().toString());
                            //daca s-a gasit userul in baza de date
                            if(user!=null){
                                //userul va putea accesa DashboardActivity
                                shPref.setUser(user.getId());
                                Intent intent = new Intent(LogInActivity.this, DashboardActivity.class);
                                intent.putExtra("User", user);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(LogInActivity.this, R.string.string_chechemailpass, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 1000);                }
            }
        });

    }

    private boolean emptyFields() {
        if (et_email.getText().toString().isEmpty() || et_parola.getText().toString().isEmpty()) {
            return true;
        }else {
            return false;
        }
    }
}
