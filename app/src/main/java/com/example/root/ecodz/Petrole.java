package com.example.root.ecodz;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Petrole extends Fragment {
    static TextView tvbaril_cost,taux,tvlundi,tvmardi,tvmercredi,tvjeudi,tvvendredi;
    static String baril_cost,str_taux;
    static String[] lundi,datelundi,mardi,datemardi,mercredi,datemercredi,jeudi,datejeudi,vendredi,datevendredi;
    WebView browser;
    getDataPetrole getd;
    static RelativeLayout viewColor;
    static LinearLayout pLayout;
    ScrollView scrollView;
    static int state=0; //0==positif, 1==négative
    public Petrole() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            //Restore the fragment's state here
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's state here
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();

        View view =lf.inflate(R.layout.fragment_petrole, container, false);
        tvbaril_cost=(TextView)view.findViewById(R.id.prix_baril);
        taux=(TextView)view.findViewById(R.id.taux);
        browser=(WebView)view.findViewById(R.id.webview_petrole);
        viewColor=(RelativeLayout)view.findViewById(R.id.view_color);
        pLayout=(LinearLayout) view.findViewById(R.id.principal_layout);
        scrollView=(ScrollView)view.findViewById(R.id.scrollview);
        tvlundi=(TextView)view.findViewById(R.id.lundi);
        tvmardi=(TextView)view.findViewById(R.id.mardi);
        tvmercredi=(TextView)view.findViewById(R.id.mercredi);
        tvjeudi=(TextView)view.findViewById(R.id.jeudi);
        tvvendredi=(TextView)view.findViewById(R.id.vendredi);

        //webview & settings
        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.getAllowContentAccess();
        browser.loadUrl("https://s.tradingview.com/widgetembed/?symbol=FX_IDC%3AUSDBRO&interval=D&saveimage=0&studies=%5B%5D&hideideas=1&theme=White&style=3&timezone=Europe%2FParis&withdateranges=1&studies_overrides=%7B%7D&overrides=%7B%7D&enabled_features=%5B%5D&disabled_features=%5B%5D");

        //view color

        //scrollview on top
        scrollView.requestFocus(View.FOCUS_UP);
        scrollView.scrollTo(0,0);
        //execute asyncktask
        getd=new getDataPetrole();
        getd.execute();



        return view;
    }


    public static void viewcolor(){
        if(state==0){
            viewColor.setBackgroundColor(staticContext.getAppContext().getResources().getColor(R.color.V));
            taux.setTextColor(staticContext.getAppContext().getResources().getColor(R.color.V));
        }
        else {
            viewColor.setBackgroundColor(staticContext.getAppContext().getResources().getColor(R.color.R));
            taux.setTextColor(staticContext.getAppContext().getResources().getColor(R.color.R));

        }
    }



    static class getDataPetrole extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc= Jsoup.connect("https://prixdubaril.com").get();
                Elements research_taux=doc.select("span[style=font-size: 8pt;color: #c0c0c0;]");
                Elements research_baril=doc.select("span[style=background-color: #c0c0c0; color: #ffffff;]");

                //les dates
                Elements research_lundi=doc.select("div.cell_j11o");
                Elements research_datlundi=doc.select("div.cell_j1o");
                Elements research_mardi=doc.select("div.cell_j22o");
                Elements research_datemardi=doc.select("div.cell_j2o");
                Elements research_mercredi=doc.select("div.cell_j33o");
                Elements research_datemercredi=doc.select("div.cell_j3o");
                Elements research_jeudi=doc.select("div.cell_j44o");
                Elements research_datejeudi=doc.select("div.cell_j4o");
                Elements research_verndredi=doc.select("div.cell_j55o");
                Elements research_dateverndredi=doc.select("div.cell_j5o");
                datelundi=research_datlundi.text().split(" ");
                lundi=research_lundi.text().split(" ");
                datemardi=research_datemardi.text().split(" ");
                mardi=research_mardi.text().split(" ");
                datemercredi=research_datemercredi.text().split(" ");
                mercredi=research_mercredi.text().split(" ");
                datejeudi=research_datejeudi.text().split(" ");
                jeudi=research_jeudi.text().split(" ");
                datevendredi=research_dateverndredi.text().split(" ");
                vendredi=research_verndredi.text().split(" ");


                baril_cost=research_baril.text();
                str_taux=research_taux.text();//""+research_taux2.text();
            } catch (IOException e) {
                e.printStackTrace();
            }
//<span style="font-size: 8pt;color: #c0c0c0;"> (<span style="color: #ff0000;">-0.13</span> <span style="background-color: #ff0000; color: #ffffff;"><span class="en_vert"><strong> 0.20% </strong></span></span>)</span>
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            String[] txtbaril=baril_cost.split(" ");
            str_taux=str_taux.replaceAll("[()]","");
            String indice;
            //state + ou -
            if(str_taux.contains("+")){
                state=0;
                indice="▲";
                viewcolor();

            }
            else {
                state=1;
                indice="▼";
                viewcolor();
            }

            //put value
            tvbaril_cost.setText(indice+txtbaril[0]+"$");
            String[] txttaux=str_taux.split(" ");
            taux.setText(txttaux[0]+" | "+txttaux[1]);

            //animation
            pLayout.setVisibility(View.VISIBLE);
            pLayout.animate().alpha(1).setDuration(300);


            //date put
         //   tvlundi.setText(datelundi[0]+" Lundi "+lundi[2]+"$");
          //  tvmardi.setText(datemardi[0]+" Mardi "+mardi[2]+"$");
          //  tvmercredi.setText(datemercredi[0]+" Mercredi "+mercredi[2]+"$");
          //  tvjeudi.setText(datejeudi[0]+" Jeudi "+jeudi[2]+"$");
          //  tvvendredi.setText(datevendredi[0]+" Vendredi "+vendredi[2]+"$");


        }
    }



}
