package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public String uid;
    public  int BID;
    public  String name;
    public  String BusinessType;
    public String Address;
    public String Province;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Contact(String uid, int id, String name, String business, String address, String prov){
        this.uid = uid;
        this.BID = id;
        this.name = name;
        this.BusinessType = business;
        this.Address = address;
        this.Province = prov;
    }

    @Exclude
    public Map<String, Object> toMap(){
        String Bid2 = Integer.toString(BID);
        HashMap<String, Object> result = new HashMap<>();
        result.put("UID", uid);
        result.put("BID", Bid2);
        result.put("name", name);
        result.put("Business Type", BusinessType);
        result.put("Address", Address);
        result.put("Province", Province);

        return result;
    }
}
