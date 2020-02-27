package dev.tito.edudesper;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;

public class edukasi_1 extends AppCompatActivity {
    Button btnkembali_1,btnlanjut_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edukasi_1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color='#4a8fff'>eduDesper </font>"));

        btnkembali_1 = findViewById(R.id.btnkembali_1);
        btnlanjut_1 = findViewById(R.id.btnlanjut_1);

        btnkembali_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnlanjut_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(edukasi_1.this,edukasi_2.class);
                startActivity(intent);
            }
        });

    }
}
