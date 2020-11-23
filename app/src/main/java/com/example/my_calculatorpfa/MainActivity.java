package com.example.my_calculatorpfa;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import java.lang.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText screen;
    private String display;
    private String currentOperator;
    //private PostfixCalculator calculator;
    //private PostfixConverter converter;
    private String displayCopy;
    Button buttonadd, buttonsub, buttonmul, buttondiv;
    float mValueOne, mValueTwo;
    private static String tag ="pavan";


    // INIT ALL VALUES:
    {
        display = "";
        currentOperator = "";
        //converter = new PostfixConverter();
       // calculator = new PostfixCalculator();
        displayCopy = "";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner1 = findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(this);

        buttonadd = (Button) findViewById(R.id.buttonadd);
        buttonsub = (Button) findViewById(R.id.buttonsub);
        buttonmul = (Button) findViewById(R.id.buttonmul);
        buttondiv = (Button) findViewById(R.id.buttondiv);
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (screen == null) {
                    screen.setText("");
                } else {
                    mValueOne = Float.parseFloat(screen.getText() + "");
                    //Addition = true;
                    screen.setText(null);
                }
            }
        });

        buttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(screen.getText() + "");
                //mSubtract = true;
                screen.setText(null);
            }
        });

        buttonmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(screen.getText() + "");
                //Multiplication = true;
                screen.setText(null);
            }
        });

        buttondiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(screen.getText() + "");
                //Division = true;
                screen.setText(null);
            }
            //@Override

        });


        // Init

        screen =  findViewById(R.id.phone);
        screen.setText(display);

        screen.addTextChangedListener(new TextWatcher(){


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i(tag, "s: " + s + " start: " + start +" count: " + count +" after: "+ after);



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(tag, "s: " + s + " start: " + start +" count: " + count );
                screen.setText(" c");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(tag, "s: " + s );
            }
        });
       /* screen.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_ENTER)) {
                    screen.requestFocus();
                    return true;
                } else
                    return false;
            }
        });*/
    }
        //screen.setText(display);
      /*  screen.setOnKeyListener(new NumberKeyListener() {
            //@Override
            public int getInputType() {
                return InputType.TYPE_CLASS_NUMBER;
            }

            // @Override
            public boolean onKeyDown(View view, int keyCode, KeyEvent event) {
                if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {

                    // Log.d(LOG_TAG, "onKeyDown()");
                    //return super.onKeyDown(keyCode, event);
                } else if (keyCode == KeyEvent.KEYCODE_DEL) {
                    Log.i("DEBUG", "delete key hit");

                }
                Log.i("DEBUG", "key was pressed");
                //  update();
                return true;
            }

            @Override
            protected char[] getAcceptedChars() {
                return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
            }
        });
         /*screen.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                   // sendMessage();
                    handled = true;
                }
                return handled;
            }
        });*/


        /*private void updateScreen () {
            screen.setText(display);
        }*/

        ArrayList<String> list = new ArrayList<>();
        String tmpNumber = "";

        // If user push one of the numbers button
       /* public void onEditorAction(View v){
            Button b = (Button) v;
            display += b.getText();
            displayCopy += display;
            updateScreen();
            flag = false;

            if (first) {
                first = false;
                tmpNumber += firstOperator + b.getText();
                firstOperator = "";
            } else tmpNumber += b.getText();
        }*/


        private boolean flag = false;
        private boolean first = true;
        private String firstOperator = "";


        // If user push +/-/÷/×
        public void onClickOperator (View v){
            Button b = (Button) v;
            list.add(tmpNumber);
            tmpNumber = "";

            if (flag) {
                currentOperator = (String) b.getText();
                display = display.substring(0, display.length() - 1) + currentOperator;
                list.set(list.size() - 2, currentOperator);
                //updateScreen();
            } else {
                display += b.getText();
                displayCopy += display;
                flag = true;
                list.add((String) b.getText());
                //updateScreen();
            }

            if (b.getText().equals("-") && first) {
                firstOperator = b.getText().toString();
            }
        }


        public void onClickEqual (View v){
            list.add(tmpNumber);
            tmpNumber = "";

            String tmp = "";
            for (String element : list) {
                Toast.makeText(getApplicationContext(), element, Toast.LENGTH_SHORT).show();
                tmp += element + " ";
            }
            Toast.makeText(getApplicationContext(), tmp, Toast.LENGTH_SHORT).show();
            display = tmp;

            Log.d("DEB", display);

            // Transform notation to postfix
            //converter = new PostfixConverter(tmp);
            // Get this postfix notation
           // String postfixNotation = converter.getPostfix();
          //  display = postfixNotation;
            // Calculate via postfix
            //calculator.startCalculate(postfixNotation);
            // Show result on screen
            //display = Double.toString(calculator.getResult());
            //updateScreen();
            Calculator c = new Calculator(tmp);
        }


        // If user push `C` (clear) button
       /* public void onClickClear (View v){
            clear(); // Clear display and operators
        }


        private void clear () {
            display = "";
            currentOperator = "";
            list.clear();
            tmpNumber = "";
            updateScreen();
        }*/


        @Override
        public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){

        }

        @Override
        public void onNothingSelected (AdapterView < ? > parent){

        }

}