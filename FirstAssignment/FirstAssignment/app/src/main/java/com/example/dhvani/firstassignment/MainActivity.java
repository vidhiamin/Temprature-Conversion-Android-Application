package com.example.dhvani.firstassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private RadioButton CtoF;
    private RadioButton FtoC;
    private EditText input;
    private TextView output;
    private TextView inputUnit;
    private TextView outputUnit;
    private TextView ConversionHistory;
    private Button convert;
    private Button clear;
    private String string="";
    private String checkRadioBtn = "Fahrenheit to Celsius";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CtoF = (RadioButton) findViewById(R.id.CtoF);
        FtoC = (RadioButton) findViewById(R.id.FtoC);
        input = (EditText) findViewById(R.id.input);
        output = (TextView) findViewById(R.id.output);
        inputUnit = (TextView) findViewById(R.id.inputUnit);
        outputUnit = (TextView) findViewById(R.id.outputUnit);
        ConversionHistory = (TextView) findViewById(R.id.textView5);
        convert =(Button) findViewById(R.id.button2);
        ConversionHistory.setMovementMethod(new ScrollingMovementMethod());
    }

    public void selectUnit(View v) {
        checkRadioBtn = ((RadioButton) v).getText().toString();
        if(checkRadioBtn.trim().equals("Fahrenheit to Celsius")) {
            inputUnit.setText("Fahrenheit Degree");
            outputUnit.setText("Celsius Degree");
        }
        else {
            inputUnit.setText("Celsius Degree");
            outputUnit.setText("Fahrenheit Degree");
        }
        input.setText("");
        output.setText("");
    }


        public void onClickClear(View v) {
            string="";
            ConversionHistory.setText(string);
        }
    public void TempConversion (View v){
        Double TempInput = Double.parseDouble(input.getText().toString());
        System.out.println(TempInput);
        Double res;
        if (CtoF.isChecked()) {
            if (input.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Please Enter Temperature in Celsius", Toast.LENGTH_SHORT).show();
            } else {
                res = (TempInput * 1.8) + 32;
                output.setText(String.format("%,.1f", res));
                double a = Double.parseDouble(input.getText().toString());
                string = "C to F: " + (String.format("%,.1f", a)) + " -> " + (String.format("%,.1f", res)) + "\n" + string;
                ConversionHistory.setText(string);
            }
        }
        if (FtoC.isChecked()) {
            if (input.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Please Enter Temperature in Fahrenheit ", Toast.LENGTH_SHORT).show();
            } else {
                res = (TempInput - 32) / 1.8;
                output.setText(String.format("%,.1f", res));
                double e =  Double.parseDouble(input.getText().toString());
                string = "F to C: " + (String.format("%,.1f", e)) + " -> " + (String.format("%,.1f", res)) + "\n" + string;
                System.out.println(string);
                ConversionHistory.setText(string);
            }

        }
    }

    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("ConversionHistory", string);
        outState.putString("inputUnit", inputUnit.getText().toString());
        outState.putString("outputUnit", outputUnit.getText().toString());
        outState.putString("inputVal", input.getText().toString());
        outState.putString("outputVal", output.getText().toString());
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Call super first
        super.onRestoreInstanceState(savedInstanceState);

        string = savedInstanceState.getString("ConversionHistory");
        ConversionHistory.setText(string);
        input.setText(savedInstanceState.getString("inputVal"));
        output.setText(savedInstanceState.getString("outputVal"));
        inputUnit.setText(savedInstanceState.getString("inputUnit"));
        outputUnit.setText(savedInstanceState.getString("outputUnit"));
        if(savedInstanceState.getString("inputUnit").toString().equalsIgnoreCase("Celsius Degree")){
            CtoF.setChecked(true);
            FtoC.setChecked(false);
        } else {
            CtoF.setChecked(false);
            FtoC.setChecked(true);
        }
    }


}
