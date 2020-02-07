package com.example.rs.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //History box to store the conversion data
    TextView historyBox;
    RadioGroup typesOfRadioBtn;
    TextView inputDistanceTag;
    TextView outputDistanceTag;
    String checkRadioButton = "Miles to Kilometers";
    EditText inputDistance;
    TextView finalOutput;
    String string="";
    RadioButton MtoK;
    RadioButton KtoM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        typesOfRadioBtn = (RadioGroup)findViewById(R.id.distance_radio_grp_btn);
        inputDistanceTag = (TextView)findViewById(R.id.inputDistanceTag);
        outputDistanceTag = (TextView)findViewById(R.id.outputDistanceTag);
        inputDistance = (EditText) findViewById(R.id.inputDistance);
        finalOutput = (TextView)findViewById(R.id.finalOutput);
        historyBox = (TextView) findViewById(R.id.historyBox);
        historyBox.setMovementMethod(new ScrollingMovementMethod());
        MtoK = (RadioButton) findViewById(R.id.MtoK);
        KtoM = (RadioButton) findViewById(R.id.KtoM);

    }

    //Toggle radio button
    public void selectUnit(View v) {
        checkRadioButton = ((RadioButton) v).getText().toString();
        if(checkRadioButton.trim().equals("Miles to Kilometers")) {
            inputDistanceTag.setText("Miles Value:");
            outputDistanceTag.setText("Kilometers Value:");
        }
        else {
            inputDistanceTag.setText("Kilometers Value:");
            outputDistanceTag.setText("Miles Value:");
        }
        inputDistance.setText("");
        finalOutput.setText("");
    }

    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("historyBox", string);
        outState.putString("inputDistanceTag", inputDistanceTag.getText().toString());
        outState.putString("outputDistanceTag", outputDistanceTag.getText().toString());
        outState.putString("inputDistance", inputDistance.getText().toString());
        outState.putString("finalOutput", finalOutput.getText().toString());
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Call super first
        super.onRestoreInstanceState(savedInstanceState);

        string = savedInstanceState.getString("historyBox");
        historyBox.setText(string);
        inputDistance.setText(savedInstanceState.getString("inputDistance"));
        finalOutput.setText(savedInstanceState.getString("finalOutput"));
        inputDistanceTag.setText(savedInstanceState.getString("inputDistanceTag"));
        outputDistanceTag.setText(savedInstanceState.getString("outputDistanceTag"));
        if(savedInstanceState.getString("inputDistanceTag").toString().equalsIgnoreCase("Miles To Kilometers:")){
            MtoK.setChecked(true);
            KtoM.setChecked(false);
        } else {
            MtoK.setChecked(false);
            KtoM.setChecked(true);
        }
    }

    public void onDistanceConversion (View v){
        Double distanceInput = 0.0;
        System.out.println(distanceInput);
        Double res;
        try{
            if(inputDistance.getText().toString().isEmpty()){
                throw new Exception();
            }
            else {
                 distanceInput = Double.parseDouble(inputDistance.getText().toString());
            }
        }
        catch(Exception ex){
            Toast.makeText(MainActivity.this,"Please enter valid value",Toast.LENGTH_SHORT).show();
        }

            if (MtoK.isChecked()) {
                if (inputDistance.getText().length() == 0 || inputDistance.getText().toString ().equals (".") ||
                        inputDistance.getText().toString ().equals ("-") || inputDistance.getText().toString ().equals ("+")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Distance ", Toast.LENGTH_SHORT).show();
                } else {
                    res = (distanceInput * 1.60934 );
                    finalOutput.setText(String.format("%,.1f", res));
                    double a = Double.parseDouble(inputDistance.getText().toString());
                    string = a +"Mi" +  " ==> " + (String.format("%,.1f", res)) + "Km"+ "\n" + string;
                    historyBox.setText(string);
                }
            }
            if (KtoM.isChecked()) {
                if (inputDistance.getText().length() == 0 || inputDistance.getText().toString ().equals (".") ||
                        inputDistance.getText().toString ().equals ("-") || inputDistance.getText().toString ().equals ("+") ) {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid distance ", Toast.LENGTH_SHORT).show();
                } else {
                    res = (distanceInput * 0.621371 );
                    finalOutput.setText(String.format("%,.1f", res));
                    double e =  Double.parseDouble(inputDistance.getText().toString());
                    string = e +"Km" +  " ==> " + (String.format("%,.1f", res)) + "Mi"+ "\n" + string;
                    System.out.println(string);
                    historyBox.setText(string);
                }

            }



        inputDistance.setText ("");

    }

    //set History box clear
    public void onClearFunction(View v){
        string = "";
        historyBox.setText(string);
        finalOutput.setText (string);


    }




}
