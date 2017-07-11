package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class DetailViewActivity extends Activity {

    private EditText nameField, numberField,addressField;
    private String businessType;
    private Spinner mySpinner;
    private RadioButton fisher,dist,fishmong,proc;
    private MyApplicationData appState;

    Contact receivedPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.name);
        numberField = (EditText) findViewById(R.id.number);
        mySpinner = (Spinner)findViewById(R.id.spinner2);
        fisher = (RadioButton)findViewById(R.id.Fisher);
        fishmong = (RadioButton)findViewById(R.id.FishMonger);
        addressField = (EditText) findViewById(R.id.address);
        dist = (RadioButton) findViewById(R.id.Distributor);
        proc = (RadioButton) findViewById(R.id.Processor);


        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            String inter = Integer.toString(receivedPersonInfo.BID);
             numberField.setText(inter);
            addressField.setText(receivedPersonInfo.Address);
            setSpinner(mySpinner, receivedPersonInfo.Province);
           businessType = receivedPersonInfo.BusinessType;
            if (businessType.equals("Fisher")) {
                 fisher.setChecked(true);
            }
            if (businessType.equals("Fish Monger")) {
                 fishmong.setChecked(true);
            }
            if (businessType.equals("Distributor")) {
                 dist.setChecked(true);
            }
            if (businessType.equals("Processor")) {
                 proc.setChecked(true);
            }
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.Fisher:
                if (checked)
                    businessType = "Fisher";
            case R.id.FishMonger:
                if (checked)
                    businessType = "Fish Monger";

                break;
            case R.id.Distributor:
                if (checked)
                    businessType = "Distributor";
                break;
            case R.id.Processor:
                businessType = "Processor";
                break;
        }
    }

    public void updateContact(View v){
        //TODO: Update contact funcionality
        String part1 = numberField.getText().toString();
        int part2 = Integer.parseInt(part1);
        String province = mySpinner.getSelectedItem().toString();
        appState.firebaseReference.child(receivedPersonInfo.uid).child("BID").setValue(part2);
        appState.firebaseReference.child(receivedPersonInfo.uid).child("name").setValue(nameField.getText().toString());
        appState.firebaseReference.child(receivedPersonInfo.uid).child("BusinessType").setValue(businessType);
        appState.firebaseReference.child(receivedPersonInfo.uid).child("Province").setValue(province);
        appState.firebaseReference.child(receivedPersonInfo.uid).child("Address").setValue(addressField.getText().toString());
        finish();


    }

    public void eraseContact(View v)
    {
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        finish();
    }
    private void setSpinner(Spinner spinner, String object) {

        for (int i =0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(object)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
}
