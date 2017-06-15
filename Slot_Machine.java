
/*
    University of Michigan - Dearborn
    CIS 436 Mobile Application
    Prof John Baugh

    Project 1 : Slot Machine
    Yanzhen Wang
    5/14/2017

 */

package com.example.vivianwang.slotmachine;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;
import java.util.Random;


public class Slot_Machine extends Activity{

    Button set_value_btn;
    Button reset_game_btn;
    Button pullover_btn;
    TextView balance_textview;
    EditText Amount_txt;
    LinearLayout linear_display;
    TextView title_tv;
    TextView display_tv;
    TextView ramdom_num1_txtview;
    TextView ramdom_num2_txtview;
    TextView ramdom_num3_txtview;

    Random r; // create random object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot__machine);

        // get widget reference
        set_value_btn = (Button) findViewById(R.id.set_value_btn);
        reset_game_btn = (Button) findViewById(R.id.reset_game_btn);
        pullover_btn = (Button) findViewById(R.id.pullover_btn);
        title_tv = (TextView) findViewById(R.id.title_tv);
        display_tv = (TextView) findViewById(R.id.display_tv);
        linear_display=(LinearLayout) findViewById(R.id.linear_display);
        ramdom_num1_txtview =(TextView) findViewById(R.id.ramdom_num1_txtview);
        ramdom_num2_txtview =(TextView) findViewById(R.id.ramdom_num2_txtview);
        ramdom_num3_txtview =(TextView) findViewById(R.id.ramdom_num3_txtview);
        balance_textview = (TextView) findViewById(R.id.balance_textview);
        Amount_txt= (EditText) findViewById(R.id.Amount_txt);

        pullover_btn.setEnabled(false);// disable the pullover button before setting value

        set_value_btn.setOnClickListener(new View.OnClickListener(){ // implement the set_value_btn button action

            @Override
            public void onClick(View view)
            {
                int amount;

                amount = Integer.parseInt(Amount_txt.getText().toString()); // get the input from the user and store it in variable amount
                // if statement is to check the input validation
                if(amount <=500 && amount>=100)
                {
                    balance_textview.setText(Integer.toString(amount));// shows the balance on text view
                    set_value_btn.setEnabled(false);
                    Amount_txt.setEnabled(false);
                    pullover_btn.setEnabled(true);// enable the pull level button

                }
                else
                {
                    Toast.makeText(Slot_Machine.this, "             Error ! \nEnter a number between 100 and 500\n         " +
                            "   Good Luck", Toast.LENGTH_LONG).show();
                }
            }
        });

        pullover_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                int balance;

                balance = Integer.parseInt(balance_textview.getText().toString());
                balance-=5;// once pull level is clicked, it will remove 5

                r = new Random();

                // random number generator
                int rondom1 = r.nextInt(9)+1;
                int rondom2 = r.nextInt(9)+1;
                int rondom3 = r.nextInt(9)+1;

                // display the random number on text view
                ramdom_num1_txtview.setText(Integer.toString(rondom1));
                ramdom_num2_txtview.setText(Integer.toString(rondom2));
                ramdom_num3_txtview.setText(Integer.toString(rondom3));

                // check different situation of number matching
                if(rondom1 == rondom2 && rondom2 == rondom3 )// if three number are matched
                {
                    if(rondom1 <= 5)
                    balance+=40;
                    else if (rondom1>= 5 && rondom1 <=8)
                        balance+=100;
                    else if (rondom1 ==9)
                        balance+=1000;

                }
                else if(rondom1 == rondom2 || rondom1 == rondom3 || rondom3 == rondom2 )// check if two number are matched
                {
                    balance+=10;
                }

                balance_textview.setText(Integer.toString(balance));// display the balance

                // check the balance
                if(balance <=4 && balance >0)
                {
                    Toast.makeText(Slot_Machine.this ,"You do not have enough money to play" , Toast.LENGTH_LONG).show();
                    reset_game();
                }
                else if(balance==0)
                {
                    Toast.makeText(Slot_Machine.this ,"Sorry, you have lost all your money" , Toast.LENGTH_LONG).show();
                    reset_game();
                }
                else if(balance>=1000)
                {
                    Toast.makeText(Slot_Machine.this ,"You have cleared out the slot machine. " , Toast.LENGTH_LONG).show();
                    reset_game();
                }
            }
        });
        reset_game_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                reset_game();
            }
        });
    }

    // reset the game
    public void reset_game()

    {
        balance_textview.setText("0");
        Amount_txt.setText("");
        set_value_btn.setEnabled(true);
        pullover_btn.setEnabled(false);
        Amount_txt.setEnabled(true);
    }
}
