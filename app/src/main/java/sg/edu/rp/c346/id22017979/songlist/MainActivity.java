package sg.edu.rp.c346.id22017979.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etSong,etSinger,etYear;
    Button btnInsert,btnList;
    RadioGroup rg;
    ArrayList<String> al = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSinger = findViewById(R.id.etSinger);
        etSong = findViewById(R.id.etSong);
        etYear = findViewById(R.id.etYear);
        btnInsert = findViewById(R.id.btnInsert);
        btnList = findViewById(R.id.btnList);
        rg = findViewById(R.id.rg);


        etSinger.setText("");
        etSong.setText("");
        etYear.setText("");
        rg.clearCheck();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioID = rg.getCheckedRadioButtonId();
                int rating = 0;
                if (radioID == R.id.radioButton1){
                    rating = 1;
                } else if (radioID == R.id.radioButton2){
                    rating = 2;
                } else if (radioID == R.id.radioButton3) {
                    rating = 3;
                } else if (radioID == R.id.radioButton4) {
                    rating = 4;
                } else if (radioID == R.id.radioButton5) {
                    rating = 5;
                }

                DBHelper db = new DBHelper(MainActivity.this);

                db.insertSong(etSong.getText().toString(), etSinger.getText().toString(),
                        etYear.getText().toString(), rating);

                etSinger.setText("");
                etSong.setText("");
                etYear.setText("");
                rg.setId(-1);

            }

        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        SecondActivity.class);
                startActivity(i);
            }
        });




    }
}