package com.example.root.ecodz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Splash extends AppCompatActivity {
    getDatasplash getd;
    WebView ghostwv;
    static String data1,data2,html_monnaie;
    static String[] d1,d2;
    FirebaseDatabase database;
    static DatabaseReference myRef;
    static Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("data");
        getd=new getDatasplash();
        activity=this;
        ghostwv=(WebView)findViewById(R.id.ghostwv);
        getDataFromWebsite();

    }


    public void getDataFromWebsite(){


        //ghost webview with JS
        ghostwv.getSettings().setJavaScriptEnabled(true);
        ghostwv.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        ghostwv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
        /* This call inject JavaScript into the page which just finished loading. */
                ghostwv.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            }
        });
        ghostwv.loadUrl("http://www.forexalgerie.com/");

    }


    class MyJavaScriptInterface
    {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processHTML(String html)
        {
            html_monnaie=html;
            //execute asynctask
            getd=new getDatasplash();
            getd.execute();



        }
    }


    static class getDatasplash extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Document doc= Jsoup.parse(html_monnaie);
                Elements research_monnaie1=doc.select("td.listEvenRow");
                Elements research_monnaie2=doc.select("td.listOddRow");
                data1=research_monnaie1.text();
                data2=research_monnaie2.text();

                d1=data1.split(" ");
                d2=data2.split(" ");

            }catch (Exception e){e.printStackTrace();}


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            myRef.child("euro").child("buy").setValue(String.valueOf((int)Float.parseFloat(d1[0])).toString()+" DA");
            myRef.child("euro").child("sell").setValue(String.valueOf((int)Float.parseFloat(d1[1])).toString()+" DA");

            myRef.child("usd").child("buy").setValue(String.valueOf((int)Float.parseFloat(d2[0])).toString()+" DA");
            myRef.child("usd").child("sell").setValue(String.valueOf((int)Float.parseFloat(d2[1])).toString()+" DA");

            myRef.child("cad").child("buy").setValue(String.valueOf((int)Float.parseFloat(d1[2])).toString()+" DA");
            myRef.child("cad").child("sell").setValue(String.valueOf((int)Float.parseFloat(d1[3])).toString()+" DA");

            myRef.child("uk").child("buy").setValue(String.valueOf((int)Float.parseFloat(d2[2])).toString()+" DA");
            myRef.child("uk").child("sell").setValue(String.valueOf((int)Float.parseFloat(d2[3])).toString()+" DA");

            myRef.child("chf").child("buy").setValue(String.valueOf((int)Float.parseFloat(d1[4])).toString()+" DA");
            myRef.child("chf").child("sell").setValue(String.valueOf((int)Float.parseFloat(d1[5])).toString()+" DA");

            myRef.child("tk").child("buy").setValue(String.valueOf((int)Float.parseFloat(d2[4])).toString()+" DA");
            myRef.child("tk").child("sell").setValue(String.valueOf((int)Float.parseFloat(d2[5])).toString()+" DA");

            myRef.child("yuan").child("buy").setValue(String.valueOf((int)Float.parseFloat(d1[6])).toString()+" DA");
            myRef.child("yuan").child("sell").setValue(String.valueOf((int)Float.parseFloat(d1[7])).toString()+" DA");

            myRef.child("rial").child("buy").setValue(String.valueOf((int)Float.parseFloat(d2[6])).toString()+" DA");
            myRef.child("rial").child("sell").setValue(String.valueOf((int)Float.parseFloat(d2[7])).toString()+" DA");

            myRef.child("dirhamuae").child("buy").setValue(String.valueOf((int)Float.parseFloat(d1[8])).toString()+" DA");
            myRef.child("dirhamuae").child("sell").setValue(String.valueOf((int)Float.parseFloat(d1[9])).toString()+" DA");

            myRef.child("dinartn").child("buy").setValue(String.valueOf((int)Float.parseFloat(d2[8])).toString()+" DA");
            myRef.child("dinartn").child("sell").setValue(String.valueOf((int)Float.parseFloat(d2[9])).toString()+" DA");

            myRef.child("dirhammo").child("buy").setValue(String.valueOf((int)Float.parseFloat(d1[10])).toString()+" DA");
            myRef.child("dirhammo").child("sell").setValue(String.valueOf((int)Float.parseFloat(d1[11])).toString()+" DA");
            activity.finish();
            Intent intent=new Intent(Splash.activity,Main.class);
            activity.startActivity(intent);
        }
    }


}
