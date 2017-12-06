package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.resource;

/**
 * Created by Opti9020-C2B on 9/8/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(Context context, ArrayList<Word> wordObject, int color) {
        super(context, 0, wordObject);
        this.mColorResourceId = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listViewItem = convertView;

        if(listViewItem == null){
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Word currentWord = getItem(position);

        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageview);
        if (currentWord.hasImage()){
            image.setImageResource(currentWord.getImageView());
            image.setVisibility(View.VISIBLE);
        }else{
            image.setVisibility(View.GONE);
        }

        View container = listViewItem.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        container.setBackgroundColor(color);

        TextView translation = (TextView) listViewItem.findViewById(R.id.textView1);
        translation.setText(currentWord.getDefaultTranslation());


        TextView miwokTranslation = (TextView) listViewItem.findViewById(R.id.textView2);
        miwokTranslation.setText(currentWord.getMiwokTranslation());

        return listViewItem;
    }
}
