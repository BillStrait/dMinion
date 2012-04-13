package com.billdlabs.dMinion;

import java.util.Random;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

//used for debugging
import android.util.Log;



public class initiative extends Activity {

	//a tag for logging
	static String tag = "Billd";

	//a variable to hold how many characters we're limited at.
	static int charLimit = 10;
	
	//we need a global to hold the character array. Limiting to 10 for no good reason.
    static character[] charList = new character[charLimit];
    
    //ints to keep tabs on the round and turn.
    static int round = 0;
    static int turn = 0;
    
    //an int to note how many characters we have.
    static int playersPerTurn = 0;
    
    //holds our 'dice roll' for initiatives.
	static int diceRoll;
    
	//holder strings used before creating a character object.
	static String holdCharacterName;
	static String holdHp;
	static String holdTempHp;
	static String holdInitRoll;
	static String holdInitBonus;
	static String holdAc;
	static String holdFort;
	static String holdReflex;
	static String holdWill;
	
	//bools needed to validate entries, and other things.
	static boolean entriesOk=false;
	static boolean needToRoll=false;
	
    //when this is loaded into memory
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.initlist);

		//define our buttons
		final Button nextBtn = (Button) findViewById(R.id.btnNext);
		final Button prevBtn = (Button) findViewById(R.id.btnPrevious);
		final Button addBtn = (Button) findViewById(R.id.btnAdd);
		
		//the next button listener
		nextBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	round++;
            	if((round>playersPerTurn)&&(playersPerTurn!=0))
            	{
            		round=0;
            		turn++;
            	}
            	updateDisplay();
            }
        });
		
		//the previous button listener
		prevBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	round--;
            	if(round<0)
            	{
            		round=0;
            		turn--;
            	}            	
            	updateDisplay();
            }
        });
		
		//the add character button listener
		addBtn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				//this block is to prep the layout for the alert dialog.
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				final View addView = inflater.inflate(R.layout.addchars, (ViewGroup) findViewById(R.id.scrollBox));
				
				
				//taking this part out to try a better way.
				
				new AlertDialog.Builder(initiative.this)
				.setTitle("Add a Character")
				.setView(addView)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int whichButton)
					{
//						//ui controls
//						AutoCompleteTextView characterName = (AutoCompleteTextView) findViewById(R.id.inCharName);
//						EditText hp = (EditText) findViewById(R.id.inHP); 
//						EditText tempHp = (EditText) findViewById(R.id.inTempHP);
//						EditText initRoll = (EditText) findViewById(R.id.inInitRoll);
//						EditText initBonus = (EditText) findViewById(R.id.inInitBonus);
//						EditText ac = (EditText) findViewById(R.id.inAC); 
//						EditText fort = (EditText) findViewById(R.id.inFort);
//						EditText reflex = (EditText) findViewById(R.id.inReflex);
//						EditText will = (EditText) findViewById(R.id.inWill);
//						CheckBox rollInit = (CheckBox) findViewById(R.id.chkRoll);
//						
//						//variables for various checks
//						entriesOk=false;
//						needToRoll=false;
//						
//						valCheck(characterName, hp, tempHp, initRoll, initBonus, ac, fort, reflex, will, rollInit);
//						btnLogic();
						
						
					}
				}).setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int whichButton)
					{
						//do nothing
					}
				}).show();
			}
			
		});
	}
	
	public void updateDisplay()
	{
		//nab the objects we want to update.
	    final TextView dName = (TextView) findViewById(R.id.dspCharacter);
		final TextView dTurn = (TextView) findViewById(R.id.dspTurn);
		final TextView dRound = (TextView) findViewById(R.id.dspRound);
		
		dName.setText(charList[round].charName);
		dTurn.setText(turn);
		dRound.setText(round);
	}
	
    //add a character to the array.
	public void addResults(character c){
		//if this is the first character loaded we'll need to refresh the screen too.
		if(charList[0]==null)
		{
			charList[0]=c;
			updateDisplay();
			
		}
		else
		{
			//we need to cycle through charList to compare c to existing characters.
			int i = 0;
			while((charList[i] != null)&&(i<charLimit))
			{
				if(charList[i].totalInit < c.totalInit)
				{
					//we know the new character belongs ahead of this element, now we find out how many elements to shift back.
					int x = 1;
					while((charList[i+x]!=null) &&(i+x<charLimit))
					{
						x++;
					}
					
					//x+i is the first null position. Now we move everything back one, assuming x+i is less than char limit.
					if (x+i<charLimit)
					{
						while(x>0)
						{
							charList[x+i]=charList[x+i-1];
							x--;
						}
					}
					//now that everything's moved back, we insert c.
					charList[i]=c;				
				}
			}
		}
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
		
		
		character c = new character();
		
		c.setCharName(holdCharacterName);
		c.setHp(Integer.parseInt(holdHp));
		c.setTempHp(Integer.parseInt(holdTempHp));
		c.setInitRoll(Integer.parseInt(holdInitRoll));
		c.setInitModifier(Integer.parseInt(holdInitBonus));
		c.setAc(Integer.parseInt(holdAc));
		c.setFortitude(Integer.parseInt(holdFort));
		c.setReflex(Integer.parseInt(holdReflex));
		c.setWill(Integer.parseInt(holdWill));
		addResults(c);
	}

	//verify all required fields are entered
	public void valCheck(AutoCompleteTextView characterName, EditText hp, EditText tempHp, EditText initRoll, EditText initBonus, EditText ac, EditText fort, EditText reflex, EditText will, CheckBox rollInit)
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

	/*
		//asynctask to update thread
		private class updateList extends AsyncTask{

			@Override
			protected Object doInBackground(Object... arg0) {
				boolean foundit = false;
				int position;
				for(position = 0; position < results.size(); position++)
				{				
					while (foundit == false)
					{
						if(results.get(position).getTotalInit() == holder.getTotalInit())
						{
							if(results.get(position).getInitModifier() == holder.getInitModifier())
							{
								int a = 0;
								int b = 0;
								Random roller = new Random();
								while (a == b)
								{
									a = roller.nextInt(20) + 1;
									b = roller.nextInt(20) + 1;
								}
								if (a > b)
								{
									insertHere = position + 1;														
									foundit = true;
								}
								else
								{
									insertHere = position;
									foundit = true;
								}
							}
						}
						if(results.get(position).getTotalInit() < holder.getTotalInit())
						{
							insertHere = position;
							foundit = true;
						}
						if(position > results.size())
						{
							insertHere = position;
							foundit = true;
						}
					}
				}
				return null;
			}
			
			protected void onPostExecute(){
				results.add(insertHere,holder);
			}
			
		}
	*/
}


