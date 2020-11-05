package com.example.my_calculatorpfa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity   {
    Button buttonadd, buttonsub, buttonmul, buttondiv;
    EditText EditText;
    float mValueOne, mValueTwo;

    boolean Addition, mSubtract, Multiplication, Division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner1 = findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        buttonadd = (Button) findViewById(R.id.buttonadd);
        buttonsub = (Button) findViewById(R.id.buttonsub);
        buttonmul = (Button) findViewById(R.id.buttonmul);
        buttondiv = (Button) findViewById(R.id.buttondiv);

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText.setText(EditText.getText() + "+");
            }
        });

        buttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText.setText(EditText.getText() + "-");
            }
        });

        buttonmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText.setText(EditText.getText() + "*");
            }
        });

        buttondiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText.setText(EditText.getText() + "/");
            }
        });


    }



}