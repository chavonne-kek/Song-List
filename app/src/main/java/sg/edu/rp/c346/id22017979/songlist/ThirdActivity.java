package sg.edu.rp.c346.id22017979.songlist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    EditText etSong,etSinger,etYear;
    Button btnUpdate,btnDelete,btnBack;
    RadioGroup rg;
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
        rg = findViewById(R.id.rg);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        // etSinger.setText(); i dont know where to go from here

    }
}
