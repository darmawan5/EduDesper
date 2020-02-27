package dev.tito.edudesper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    TextInputLayout layoutemail,layoutpass;
    TextInputEditText etemail,etpass;
    TextView forgotpass, daftarlog;
    Button btnlogin;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        layoutemail = findViewById(R.id.layoutemail);
        layoutpass = findViewById(R.id.layoutpass);
        etemail = findViewById(R.id.editTextEmail);
        etpass = findViewById(R.id.editTextpass);
        forgotpass = findViewById(R.id.tvforgot);
        daftarlog = findViewById(R.id.tvregister);

        btnlogin = findViewById(R.id.btnlogin);

        db = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()){

                    String email = etemail.getText().toString();
                    String pass = etpass.getText().toString();

                    User currentUser = db.Authenticate(new User(null,null,null,null,email,pass));
                    if (currentUser != null){
                        Snackbar.make(btnlogin, "Succesfully Logged In!", Snackbar.LENGTH_LONG).show();
                        Intent intent=new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Snackbar.make(btnlogin,"Failed To Logged In, Please Try Again", Snackbar.LENGTH_LONG).show();
                    }
                }

            }
        });

        daftarlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }


    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String Email = etemail.getText().toString();
        String Password = etpass.getText().toString();

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            layoutemail.setError("Please enter valid email!");
        } else {
            valid = true;
            layoutemail.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            layoutpass.setError("Please enter valid password!");
        } else {
            if (Password.length() > 7) {
                valid = true;
                layoutpass.setError(null);
            } else {
                valid = false;
                layoutpass.setError("Password is to short!");
            }
        }

        return valid;
    }




}
