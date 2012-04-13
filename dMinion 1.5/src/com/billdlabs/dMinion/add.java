package com.billdlabs.dMinion;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import java.util.Random;

public class add extends Activity {
	//ui controls
	AutoCompleteTextView characterName;
	EditText hp; 
	EditText tempHp;
	EditText initRoll;
	EditText initBonus;
	EditText ac;
	EditText fort;
	EditText reflex;
	EditText will;
	CheckBox rollInit;
	
	//character object that will be accessed from several methods.
	public static character c;
	
	//variables for various checks
	boolean entriesOk=false;
	boolean needToRoll=false;
	
	//variables to hold the values from the edittexts and checkbox values, to be manipulated in their own thread.
	//This is used to resolve a timeout when clicking on a button.
	String holdCharacterName;
	String holdHp;
	String holdTempHp;
	String holdInitRoll;
	String holdInitBonus;
	String holdAc;
	String holdFort;
	String holdReflex;
	String holdWill;
	
	//holds our 'dice roll' for initiatives.
	int diceRoll;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addchars);
        
        //bind the UI controls to the UI.
        characterName = (AutoCompleteTextView) findViewById(R.id.inCharName);
        hp = (EditText) findViewById(R.id.inHP);
        tempHp = (EditText) findViewById(R.id.inTempHP);
        initRoll = (EditText) findViewById(R.id.inInitRoll);
        initBonus = (EditText) findViewById(R.id.inInitBonus);
        ac = (EditText) findViewById(R.id.inAC); 
        fort = (EditText) findViewById(R.id.inFort);
        reflex = (EditText) findViewById(R.id.inReflex);
        will = (EditText) findViewById(R.id.inWill);
        rollInit = (CheckBox) findViewById(R.id.chkRoll);

        
        //handle reset button
        final Button btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	reset();
        	}
        });
        
        
        //handle the ok button
        final Button btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				valCheck();
				btnLogic();
				reset();
			}
        });
	}

	
	public void btnLogic(){
		Thread t = new Thread(){
			public void run(){
				
				if (entriesOk)
				{
					if(needToRoll)
					{
						//do a rand
						rollTheInit();
						//put the random into init
						holdInitRoll = Integer.toString(diceRoll);
						//save the info
						adder();
					}
					else
					{
						//just save the info
						adder();
					}
					
					entriesOk = false;
					needToRoll = false;
					diceRoll = 0;
					
				}	
			}
		};
		t.run();
	}
	
	public void adder(){
		
        //bind the UI controls to the UI.
		
		
		c = new character();
		
		c.setCharName(holdCharacterName);
		c.setHp(Integer.parseInt(holdHp));
		c.setTempHp(Integer.parseInt(holdTempHp));
		c.setInitRoll(Integer.parseInt(holdInitRoll));
		c.setInitModifier(Integer.parseInt(holdInitBonus));
		c.setAc(Integer.parseInt(holdAc));
		c.setFortitude(Integer.parseInt(holdFort));
		c.setReflex(Integer.parseInt(holdReflex));
		c.setWill(Integer.parseInt(holdWill));
		initiative.addResults(c);
	}

	//verify all required fields are entered
	public void valCheck()
	{
		if(characterName.getText().length() > 0)
		{
			holdCharacterName = characterName.getText().toString();
			if(hp.getText().length() > 0)
			{
				holdHp = hp.getText().toString();
				if(initBonus.getText().length() > 0)
				{
					holdInitBonus = initBonus.getText().toString();
					if(ac.getText().length() > 0)
					{
						holdAc = ac.getText().toString();
						if(fort.getText().length() > 0)
						{
							holdFort = fort.getText().toString();
							if(reflex.getText().length() > 0)
							{
								holdReflex = reflex.getText().toString();
								if(will.getText().length() > 0)
								{
									holdWill = will.getText().toString();
									if(tempHp.getText().length() == 0)
									{
										holdTempHp = "0";
										tempHp.setText("0");
									}
									else
									{
										holdTempHp = tempHp.getText().toString();
									}
									
									if(rollInit.isChecked())
									{
										entriesOk = true;
										needToRoll = true;
									}
									else
									{
										if(initRoll.getText().length() > 0)
										{
											holdInitRoll = initRoll.getText().toString();
											entriesOk = true;
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	//generate a number between 1 and 20
	public void rollTheInit()
	{
		Random roller = new Random();
		diceRoll = roller.nextInt(20) + 1;
	}
	
	
	//sets all fields to null
    public void reset()
    {        
    	characterName.setText(null);
        hp.setText(null);
        tempHp.setText(null);   
        initRoll.setText(null);
        initBonus.setText(null);
        ac.setText(null);
        fort.setText(null);
        reflex.setText(null);
        will.setText(null);
        rollInit.setChecked(false);
    }

}
