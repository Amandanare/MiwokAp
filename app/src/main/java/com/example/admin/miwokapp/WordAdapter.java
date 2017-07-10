package com.example.admin.miwokapp;

import android.app.Activity;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<word> {
    private static final String LOG_TAG = WordAdapter.class.getSimpleName();


    public WordAdapter(Activity context, ArrayList<word> words) {
        super(context, 0,words);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_list_item, parent, false);
        }

        word currentWord = getItem(position);

        TextView englishTextView = (TextView) listItemView.findViewById(R.id.version_english);
        englishTextView.setText(currentWord.getmDefaultTranslation());


        TextView sothoTextView = (TextView) listItemView.findViewById(R.id.version_miwok);
        sothoTextView.setText(currentWord.getmMiwokTranslation());


        ImageView iconView = (ImageView) listItemView.findViewById(R.id.ImageResourceId);
        iconView.setImageResource(currentWord.getmImageResourceId());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        return listItemView;

    }
}
