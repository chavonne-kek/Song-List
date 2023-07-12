package sg.edu.rp.c346.id22017979.songlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Button btn5star,btnBack;
    ListView lv;
    Spinner spn;
    ArrayAdapter aaSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        btn5star = findViewById(R.id.btn5star);
        btnBack = findViewById(R.id.btnBack);
        lv = findViewById(R.id.lv);
        spn = findViewById(R.id.spinner);

        DBHelper db = new DBHelper(SecondActivity.this);
        ArrayList<Song> songs = db.getSongs();
        aaSongs = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, songs);
        db.close();
        lv.setAdapter(aaSongs);

        String txt = "";

        for (int i = 0; i > songs.size(); i++) {
            txt += songs.get(i);
            aaSongs.add(txt);
            txt = "";
        }
            btn5star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<Song> filteredSongs = db.getFilteredSongs();
                    aaSongs.clear();
                    aaSongs.addAll(filteredSongs);
                    aaSongs.notifyDataSetChanged();

                }
            });

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int
                        position, long identity) {
                    Song data = songs.get(position);
                    Intent intent = new Intent(SecondActivity.this,
                            ThirdActivity.class);
                    intent.putExtra("data", data);
                    startActivity(intent);
                }
            });

            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(SecondActivity.this,
                            MainActivity.class);
                    startActivity(i);
                }
            });

        }
    }

