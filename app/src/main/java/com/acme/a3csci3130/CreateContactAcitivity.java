package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, addressField, numberField;
    private String BusinessChoice;
    Spinner myspinner;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        numberField = (EditText) findViewById(R.id.number);
        myspinner = (Spinner) findViewById(R.id.spinner);
        addressField = (EditText) findViewById(R.id.address);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.Fisher:
                if (checked)
                    BusinessChoice = "Fisher";
            case R.id.FishMonger:
                if (checked)
                    BusinessChoice = "Fish Monger";

                break;
            case R.id.Distributor:
                if (checked)
                    BusinessChoice = "Distributor";
                break;
            case R.id.Processor:
                BusinessChoice = "Processor";
                break;
        }
    }



    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String address = addressField.getText().toString();
        String number = numberField.getText().toString();
        int number2 = Integer.parseInt(number);
        String province = myspinner.getSelectedItem().toString();

        Contact person = new Contact(personID,number2,name,BusinessChoice,address,province);
        Log.d("Test", Integer.toString(person.BID));

        appState.firebaseReference.child(personID).setValue(person);
        finish();

    }
}
