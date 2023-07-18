package sg.edu.rp.c346.id22017979.songlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    EditText etSong,etSinger,etYear;
    Button btnUpdate,btnDelete,btnBack;
    RadioGroup rg;
    RadioButton r1,r2,r3,r4,r5;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_three);
        etSinger = findViewById(R.id.etSinger);
        etSong = findViewById(R.id.etSong);
        etYear = findViewById(R.id.etYear);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnBack = findViewById(R.id.btnBack);

        r1 = findViewById(R.id.radioButton1);
        r2 = findViewById(R.id.radioButton2);
        r3 = findViewById(R.id.radioButton3);
        r4 = findViewById(R.id.radioButton4);
        r5 = findViewById(R.id.radioButton5);
        rg = findViewById(R.id.rg);

        Intent intent = getIntent();
        data = (Song) intent.getSerializableExtra("data");

        etSinger.setText(data.getSinger());
        etSong.setText(data.getSong());
        etYear.setText(data.getYear());
        if (data.getRating() == 1) {
            r1.setChecked(true);
        } else if (data.getRating() == 2) {
            r2.setChecked(true);
        } else if (data.getRating() == 3) {
            r3.setChecked(true);
        } else if (data.getRating() == 4) {
            r4.setChecked(true);
        } else if (data.getRating() == 5) {
            r5.setChecked(true);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(ThirdActivity.this,
                        SecondActivity.class);
                startActivity(back);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(ThirdActivity.this);
                int selectedRgStar = rg.getCheckedRadioButtonId();
                int rating = 0;
                if (selectedRgStar == R.id.radioButton1) {
                    rating = 1;
                } else if (selectedRgStar == R.id.radioButton2) {
                    rating = 2;
                } else if (selectedRgStar == R.id.radioButton3) {
                    rating = 3;
                } else if (selectedRgStar == R.id.radioButton4) {
                    rating = 4;
                } else if (selectedRgStar == R.id.radioButton5) {
                    rating = 5;
                }

                String song = etSong.getText().toString();
                String singer = etSinger.getText().toString();
                String year = etYear.getText().toString();
                data.setSongContent(song,singer,year, rating);
                db.updateSong(data);
                db.close();
                Intent i = new Intent(ThirdActivity.this,
                        SecondActivity.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(data.getId());
                Intent i = new Intent(ThirdActivity.this,
                        SecondActivity.class);
                startActivity(i);

            }
        });

    }
}
