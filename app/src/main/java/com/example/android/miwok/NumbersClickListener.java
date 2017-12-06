package com.example.android.miwok;

import android.view.View;
import android.widget.Toast;

import static android.R.id.list;

/**
 * Created by Opti9020-C2B on 9/6/2017.
 */

public class NumbersClickListener implements View.OnClickListener{

    private String mTexto;

    public NumbersClickListener(String texto){
        this.mTexto = texto;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(),this.mTexto, Toast.LENGTH_SHORT).show();
    }
}
