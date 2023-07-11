package sg.edu.rp.c346.id22017979.songlist;

import android.content.Intent;
import android.os.Bundle;
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
    ArrayList<Song> sl = new ArrayList<>();
    ArrayList<Song> al = new ArrayList<Song>();
    Button btn5star;
    ListView lv;
    Spinner spn;
    ArrayAdapter aaSongs;
    ArrayAdapter aaYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        btn5star = findViewById(R.id.btn5star);
        lv = findViewById(R.id.lv);
        spn = findViewById(R.id.spinner);

        DBHelper db = new DBHelper(SecondActivity.this);
        ArrayList<Song> songs = db.getSongs();
        aaSongs = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, songs);
        db.close();
        lv.setAdapter(aaSongs); //i dont know why or how to get the rating to show as "****"

        String txt = "";

        for (int i = 0; i > al.size(); i++) { //this part of the code SHOULDNT be working
            txt += songs.get(i);
            aaSongs.add(txt);
            txt = "";

            btn5star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper db = new DBHelper(SecondActivity.this);
                    ArrayList<Song> info = db.getSongs();
                    db.close();

                    aaSongs.clear();
                    aaSongs.addAll(info);
                    aaSongs.notifyDataSetChanged();

                }
            });

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int
                        position, long identity) {
                    Song data = al.get(position);
                    Intent i = new Intent(SecondActivity.this,
                            ThirdActivity.class);
                    i.putExtra("data", data);
                    startActivity(i); //i dont know why its not swtiching to the next activity
                }
            });


        }
    }
}
