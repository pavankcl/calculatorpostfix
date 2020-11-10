package com.example.my_calculatorpfa;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText screen;
    private String display;
    private String currentOperator;
    private PostfixCalculator calculator;
    private PostfixConverter converter;
    private String displayCopy;


    // INIT ALL VALUES:
    {
        display = "";
        currentOperator = "";
        //converter = new PostfixConverter();
        calculator = new PostfixCalculator();
        displayCopy = "";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner1 = findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);



        // Init
        screen = (EditText) findViewById(R.id.phone);
        screen.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                   // sendMessage();
                    handled = true;
                }
                return handled;
            }
        });
        screen.setText(display);

    }


    private void updateScreen() {
        screen.setText(display);
    }

    ArrayList<String> list = new ArrayList<>();
    String tmpNumber = "";

    // If user push one of the numbers button
    public void onEditorAction(View v) {
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
    }


    private boolean flag = false;
    private boolean first = true;
    private String firstOperator = "";


    // If user push +/-/÷/×
    public void onClickOperator(View v) {
        Button b = (Button) v;
        list.add(tmpNumber);
        tmpNumber = "";

        if (flag) {
            currentOperator = (String) b.getText();
            display = display.substring(0, display.length() - 1) + currentOperator;
            list.set(list.size() - 2, currentOperator);
            updateScreen();
        } else {
            display += b.getText();
            displayCopy += display;
            flag = true;
            list.add((String) b.getText());
            updateScreen();
        }

        if (b.getText().equals("-") && first) {
            firstOperator = b.getText().toString();
        }
    }



    public void onClickEqual(View v) {
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
        converter = new PostfixConverter(tmp);
        // Get this postfix notation
        String postfixNotation = converter.getPostfix();
        display = postfixNotation;
        // Calculate via postfix
        calculator.startCalculate(postfixNotation);
        // Show result on screen
        display = Double.toString(calculator.getResult());
        updateScreen();
    }


    // If user push `C` (clear) button
    public void onClickClear(View v) {
        clear(); // Clear display and operators
    }


    private void clear() {
        display = "";
        currentOperator = "";
        list.clear();
        tmpNumber = "";
        updateScreen();
    }


}