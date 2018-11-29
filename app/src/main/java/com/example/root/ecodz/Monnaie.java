package com.example.root.ecodz;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jackandphantom.customtogglebutton.CustomToggle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


/**
 * A simple {@link Fragment} subclass.
 */
public class Monnaie extends Fragment {
    View view;
    static TextView tveuro_buy,tveuro_sell,tvus_buy,tvus_sell,tvcad_buy,tvcad_sell,tvuk_buy,tvuk_sell,tvchf_buy,tvchf_sell,tvtk_buy,tvtk_sell,tvyuan_buy,tvyuan_sell,tvrial_buy,tvrial_sell,tvdirhamuae_buy,tvdirhamuae_sell,tvdinartn_buy,tvdinartn_sell,tvdirhammo_buy,tvdirhammo_sell;
    static  String[] d1,d2;
    FirebaseDatabase database;
    DatabaseReference myRef;
    CustomToggle toggle;

    public Monnaie() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_monnaie, container, false);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("data");
        toggle=(CustomToggle)view.findViewById(R.id.customToggle);

        //instance all cell
        tveuro_buy=(TextView)view.findViewById(R.id.euro_buy);
        tveuro_sell=(TextView)view.findViewById(R.id.euro_sell);


        tvus_buy=(TextView)view.findViewById(R.id.us_buy);
        tvus_sell=(TextView)view.findViewById(R.id.us_sell);

        tvcad_buy=(TextView)view.findViewById(R.id.cad_buy);
        tvcad_sell=(TextView)view.findViewById(R.id.cad_sell);

        tvuk_buy=(TextView)view.findViewById(R.id.uk_buy);
        tvuk_sell=(TextView)view.findViewById(R.id.uk_sell);

        tvchf_buy=(TextView)view.findViewById(R.id.chf_buy);
        tvchf_sell=(TextView)view.findViewById(R.id.chf_sell);

        tvtk_buy=(TextView)view.findViewById(R.id.tk_buy);
        tvtk_sell=(TextView)view.findViewById(R.id.tk_sell);

        tvyuan_buy=(TextView)view.findViewById(R.id.yuan_buy);
        tvyuan_sell=(TextView)view.findViewById(R.id.yuan_sell);

        tvrial_buy=(TextView)view.findViewById(R.id.rial_buy);
        tvrial_sell=(TextView)view.findViewById(R.id.rial_sell);

        tvdirhamuae_buy=(TextView)view.findViewById(R.id.dirhamuae_buy);
        tvdirhamuae_sell=(TextView)view.findViewById(R.id.dirhamuae_sell);

        tvdinartn_buy=(TextView)view.findViewById(R.id.dinartn_buy);
        tvdinartn_sell=(TextView)view.findViewById(R.id.dinartn_sell);

        tvdirhammo_buy=(TextView)view.findViewById(R.id.dirhammo_buy);
        tvdirhammo_sell=(TextView)view.findViewById(R.id.dirhammo_sell);
        toggledecision();
        getValue();


        return view;
    }





    public void getValue(){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                tveuro_buy.setText(dataSnapshot.child("euro").child("buy").getValue(String.class));
                tveuro_sell.setText(dataSnapshot.child("euro").child("sell").getValue(String.class));

                tvus_buy.setText(dataSnapshot.child("usd").child("buy").getValue(String.class));
                tvus_sell.setText(dataSnapshot.child("usd").child("sell").getValue(String.class));

                tvcad_buy.setText(dataSnapshot.child("cad").child("buy").getValue(String.class));
                tvcad_sell.setText(dataSnapshot.child("cad").child("sell").getValue(String.class));

                tvuk_buy.setText(dataSnapshot.child("uk").child("buy").getValue(String.class));
                tvuk_sell.setText(dataSnapshot.child("uk").child("sell").getValue(String.class));

                tvchf_buy.setText(dataSnapshot.child("chf").child("buy").getValue(String.class));
                tvchf_sell.setText(dataSnapshot.child("chf").child("sell").getValue(String.class));

                tvtk_buy.setText(dataSnapshot.child("tk").child("buy").getValue(String.class));
                tvtk_sell.setText(dataSnapshot.child("tk").child("sell").getValue(String.class));

                tvyuan_buy.setText(dataSnapshot.child("yuan").child("buy").getValue(String.class));
                tvyuan_sell.setText(dataSnapshot.child("yuan").child("sell").getValue(String.class));

                tvrial_buy.setText(dataSnapshot.child("rial").child("buy").getValue(String.class));
                tvrial_sell.setText(dataSnapshot.child("rial").child("sell").getValue(String.class));

                tvdirhamuae_buy.setText(dataSnapshot.child("dirhamuae").child("buy").getValue(String.class));
                tvdirhamuae_sell.setText(dataSnapshot.child("dirhamuae").child("sell").getValue(String.class));

                tvdinartn_buy.setText(dataSnapshot.child("dinartn").child("buy").getValue(String.class));
                tvdinartn_sell.setText(dataSnapshot.child("dinartn").child("sell").getValue(String.class));

                tvdirhammo_buy.setText(dataSnapshot.child("dirhammo").child("buy").getValue(String.class));
                tvdirhammo_sell.setText(dataSnapshot.child("dirhammo").child("sell").getValue(String.class));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });



    }


}
