package dev.tito.edudesper;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;

public class edukasi_home extends AppCompatActivity {

    Button btnkembali_home,btnlanjut_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edukasi_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color='#4a8fff'>eduDesper </font>"));

        btnkembali_home = findViewById(R.id.btnkembali_home);
        btnlanjut_home = findViewById(R.id.btnlanjut_home);

        btnkembali_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnlanjut_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(edukasi_home.this,edukasi_1.class);
                startActivity(intent);
            }
        });
    }
}
