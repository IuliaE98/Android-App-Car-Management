package com.example.carbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.carbuddy.database.DatabaseManager;
import com.example.carbuddy.database.dao.UserDao;
import com.example.carbuddy.database.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class  RegisterActivity extends AppCompatActivity {
    private Button redirectLogIn;
    private EditText et_firstname;
    private EditText et_name;
    private EditText et_email;
    private EditText et_password;
    private EditText et_confirmpassword;
    private EditText et_carmodel;
    private Button   btnRegister;
    private RadioGroup rgGender;
    private Intent   intent;
    public static final String ADD_USER_KEY = "addUserKey";
    private UserDao userDao;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Registering...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
////////////////////////////////!!!!!!!
               userDao = Room.databaseBuilder(this, DatabaseManager.class, "iua_db")
                .allowMainThreadQueries()
                .build()
                .getUserDao();


        intent = getIntent();

        if (intent.hasExtra(ADD_USER_KEY)) {
            User user = intent.getParcelableExtra(ADD_USER_KEY);

        }
    }


    private void addGender(User user) {
        if (user.getSex().equals(getString(R.string.radioButtonWoman))) {
            rgGender.check(R.id.radioButtonWoman);
        } else {
            rgGender.check(R.id.radioButtonMan);
        }
    }
    private void initComponents(){
        et_firstname =findViewById(R.id.editTextFirstName);
        et_name=findViewById(R.id.editTextName);
        et_email=findViewById(R.id.editTextMail);
        et_password=findViewById(R.id.editTextPassword);
        et_carmodel=findViewById(R.id.editTextCarModel);
        rgGender = findViewById(R.id.radioGroup);
        btnRegister=findViewById(R.id.btnRegister);
        et_confirmpassword=findViewById(R.id.editTextConfirmPassword);
        redirectLogIn = findViewById(R.id.button_redictionarelogin);
        redirectLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LogInActivity.class);
                startActivity(intent);
            }
        });
//////////////////////////////////!!!!!!!!!!!!!
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    progressDialog.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user = createUserFromView();
                            userDao.insert(user);
                            progressDialog.dismiss();
                            startActivity(new Intent(RegisterActivity.this, LogInActivity.class));
                        }
                    }, 1000);

                } else {
                    Toast.makeText(RegisterActivity.this, R.string.sign_up_failed, Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private User createUserFromView() {
        String nume = et_firstname.getText().toString();
        String prenume = et_name.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String modelMasina = et_carmodel.getText().toString();

        RadioButton selectedGender =
                findViewById(rgGender.getCheckedRadioButtonId());
        String sex = selectedGender.getText().toString();

        return new User(nume, prenume, email, password, modelMasina,sex);
    }
    private boolean validate(){
        if(et_firstname.getText()==null || et_firstname.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),getString(R.string.toast_firstNameNotCompleted),Toast.LENGTH_LONG).show();
            return false;
        }
        if(et_carmodel.getText()==null || et_carmodel.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),R.string.toast_ErrCarmodel,Toast.LENGTH_LONG).show();
            return false;
        }
        if(et_name.getText()==null || et_name.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.toast_NameNotCompleted,Toast.LENGTH_LONG).show();
            return false;
        }
        if(et_email.getText()==null || et_email.getText().toString().trim().isEmpty() || !et_email.getText().toString().contains("@")){
            Toast.makeText(getApplicationContext(), R.string.toast_emailNotCompleted,Toast.LENGTH_LONG).show();
            return false;
        }
        if(et_password.getText()==null || et_password.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.toast_incorrectPassword,Toast.LENGTH_LONG).show();
            return false;
        }
        if(et_confirmpassword.getText()== null || et_confirmpassword.getText().toString().trim().isEmpty()
          || et_password.getText().toString().compareTo(et_confirmpassword.getText().toString())!=0){
            Toast.makeText(getApplicationContext(), R.string.ToastErrDifferentPAsswords,Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }




}
