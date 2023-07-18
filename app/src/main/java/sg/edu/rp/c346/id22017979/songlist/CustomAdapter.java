package sg.edu.rp.c346.id22017979.songlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objects){
        super(context, resource,objects);

        parent_context = context;
        layout_id = resource;
        songList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);
        TextView tvItem = rowView.findViewById(R.id.tvSong);
        TextView tvDate = rowView.findViewById(R.id.tvDate);
        TextView tvSinger = rowView.findViewById(R.id.tvSinger);
        TextView tvStar = rowView.findViewById(R.id.tvStar);
        Song currentVersion = songList.get(position);

        tvItem.setText(currentVersion.getSong());
        tvDate.setText(currentVersion.getYear());
        tvSinger.setText(currentVersion.getSinger());
        tvStar.setText(currentVersion.getStar());
        return rowView;
    }
}

