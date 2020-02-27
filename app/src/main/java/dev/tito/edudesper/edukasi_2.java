package dev.tito.edudesper;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;

public class edukasi_2 extends AppCompatActivity {

    Button btnselesai,btnkembali_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edukasi_2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color='#4a8fff'>eduDesper </font>"));

        btnselesai = findViewById(R.id.btnselesai);
        btnkembali_2 = findViewById(R.id.btnkembali_2);


        btnselesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(edukasi_2.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnkembali_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
