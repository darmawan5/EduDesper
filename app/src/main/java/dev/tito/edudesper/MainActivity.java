package dev.tito.edudesper;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView edukasi,konsul,video,games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color='#4a8fff'>eduDesper </font>"));

        edukasi = findViewById(R.id.cvedu);
        konsul = findViewById(R.id.cvkonsul);
        video = findViewById(R.id.cvvisual);
        games = findViewById(R.id.cvgames);

        edukasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, edukasi_home.class);
                startActivity(intent);
            }
        });

        konsul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, progress.class);
                startActivity(intent);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, progress.class);
                startActivity(intent);
            }
        });

        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, progress.class);
                startActivity(intent);
            }
        });
    }
}
