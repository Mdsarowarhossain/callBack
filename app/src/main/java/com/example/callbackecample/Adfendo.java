package com.example.callbackecample;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Adfendo extends Activity {

    private Context context;
    private LinearLayout ln1;
    private TextView tv1;
    private View bannerView;

    public Adfendo(Context context) {

    }

    public void adViewBanner(Context context, AdViewBanner view, OnClickItem onClickItem) {

        LayoutInflater linf;

        linf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linf = LayoutInflater.from(context);

        final View viewx = linf.inflate(R.layout.banner_layout_1, null);
        view.addView(viewx);

        final TextView installBtn = viewx.findViewById(R.id.textview1);

        installBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

                onClickItem.onclick();
            }
        });
    }

    public void AdLoadInterstitial(Context context) {
        Toast.makeText(context, "Show Reward", Toast.LENGTH_SHORT).show();
    }

}