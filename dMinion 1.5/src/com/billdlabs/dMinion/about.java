package com.billdlabs.dMinion;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.text.util.Linkify;


public class about extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textview = new TextView(this);
        textview.setText("This application was created by Billd Labs, which is currently a one man show. If you got this for free somehow, why not check out http://www.billdlabs.com and help support me.");
        Linkify.addLinks(textview,1);
        setContentView(textview);
        
	}
}
