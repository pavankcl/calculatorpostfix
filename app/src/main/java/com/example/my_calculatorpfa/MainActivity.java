package com.example.my_calculatorpfa;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//import android.view.View;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    Button buttonadd, buttonsub, buttonmul, buttondiv;
    EditText EditText;
    private TextView screen;
    private String display;
   float mValueOne, mValueTwo;
    boolean Addition, mSubtract, Multiplication, Division;
   {
       display = " ";
   }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner1 = findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener( this);


        buttonadd = (Button) findViewById(R.id.buttonadd);
        buttonsub = (Button) findViewById(R.id.buttonsub);
        buttonmul = (Button) findViewById(R.id.buttonmul);
        buttondiv = (Button) findViewById(R.id.buttondiv);

        screen = (TextView) findViewById(R.id.textView);
        screen.setText(display);
        EditText editText = (EditText) findViewById(R.id.phone);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                  //  sendMessage();
                    handled = true;
                }
                return handled;
            }
        });

        ArrayList<String> list = new ArrayList<>();
        //boolean pause = false;
         Stack s = new Stack();


        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EditText == null) {
                    EditText.setText("");
                } else {
                    mValueOne = Float.parseFloat(EditText.getText() + "");
                    Addition = true;
                    EditText.setText(null);
                }
            }
        });

        buttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(EditText.getText() + "");
                mSubtract = true;
                EditText.setText(null);
            }
        });

        buttonmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(EditText.getText() + "");
                Multiplication = true;
                EditText.setText(null);
            }
        });

        buttondiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(EditText.getText() + "");
                Division = true;
                EditText.setText(null);
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}