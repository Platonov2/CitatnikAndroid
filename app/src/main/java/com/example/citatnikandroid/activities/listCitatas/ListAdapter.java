package com.example.citatnikandroid.activities.listCitatas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.citatnikandroid.R;
import com.example.citatnikandroid.models.Citata;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private LayoutInflater LInflater;
    private ArrayList<Citata> list;

    ListAdapter(Context context, ArrayList<Citata> list){
        this.list = list;
        LInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = LInflater.inflate(R.layout.list_element, parent, false);

            holder.content = view.findViewById(R.id.contentElementTextView);
            holder.author = view.findViewById(R.id.authorElementTextView);
            holder.creationDate = view.findViewById(R.id.creationElementDateTextView);

            view.setTag(holder);
        }

        holder = (ViewHolder) view.getTag();
        Citata citata = getCitata(position);

        holder.content.setText(citata.getContent());
        holder.author.setText(citata.getAuthor());
        holder.creationDate.setText(citata.getCreationDate());

        return view;
    }

    private Citata getCitata(int position) {
        return ((Citata) getItem(position));
    }


    private static class ViewHolder {
        private TextView content;
        private TextView author;
        private TextView creationDate;
    }
}
