package dev.tito.edudesper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {

    TextView tvback;
    TextInputEditText etnama,ettgl,etalamat,etemail,etpass;
    TextInputLayout tlnama,tltgl,tlalamat,tlemail,tlpass;
    Button btnregis;

    private DBHelper db;
    private InputValidation inputValidation;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        tvback = findViewById(R.id.back);
        etnama = findViewById(R.id.editTextregnama);
        ettgl = findViewById(R.id.editTextregtgl);
        etalamat = findViewById(R.id.editTextreglocation);
        etemail = findViewById(R.id.editTextregemail);
        etpass = findViewById(R.id.editTextregpass);
        btnregis = findViewById(R.id.btnregis);

        tlnama = findViewById(R.id.layoutregnama);
        tltgl = findViewById(R.id.layoutregtgl);
        tlalamat = findViewById(R.id.layoutreglocation);
        tlemail = findViewById(R.id.layoutregemail);
        tlpass = findViewById(R.id.layoutregpass);

        inputValidation = new InputValidation(Register.this);
        db = new DBHelper(this);

        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()){
                    String nama = etnama.getText().toString();
                    String tgl = ettgl.getText().toString();
                    String alamat = etalamat.getText().toString();
                    String email = etemail.getText().toString();
                    String pass = etpass.getText().toString();

                    if (!db.isEmailExists(email)){

                        db.addUser(new User(null,nama,tgl,alamat,email,pass));

                        Snackbar.make(btnregis,"User Created Successfully!",Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        },Snackbar.LENGTH_LONG);
                    }else {
                        Snackbar.make(btnregis,"User Already Exists With Same Email ", Snackbar.LENGTH_LONG).show();
                    }

                }
            }
        });

    }

    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
                String nama = etnama.getText().toString();
                String tgl = ettgl.getText().toString();
                String alamat = etalamat.getText().toString();
                String email = etemail.getText().toString();
                String pass = etpass.getText().toString();

        //Handling validation for UserName field
        if (nama.isEmpty()) {
            valid = false;
            tlnama.setError("Isi Nama Lengkap Anda!");
        }
        if (tgl.isEmpty()) {
            valid = false;
            tltgl.setError("Masukan Tanggal Lahir Anda!");
        }
        if (alamat.isEmpty()) {
            valid = false;
            tlalamat.setError("Masukan Alamat Anda!");
        }

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            valid = false;
            tlemail.setError("Please enter valid email!");
        } else {
            valid = true;
            tlemail.setError(null);
        }

        //Handling validation for Password field
        if (pass.isEmpty()) {
            valid = false;
            tlpass.setError("Please enter valid password!");
        } else {
            if (pass.length() > 7) {
                valid = true;
                tlpass.setError(null);
            } else {
                valid = false;
                tlpass.setError("Password is to short!");
            }
        }


        return valid;
    }
}
