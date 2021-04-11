package com.sandbox.chat.core.maps;

import android.content.Context;
import android.util.Log;

import com.sandbox.chat.models.Eatery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.perfmark.Link;

/**
 * Loads and retrieves data for eateries
 */
public class MapsInteractor implements MapsContract.Interactor {

    public static ArrayList<Eatery> getEateries() {
        return eateries;
    }

    /**
     * The list of eateries.
     */
    private static ArrayList<Eatery> eateries;

    public MapsInteractor(ArrayList<Eatery> eateries){
        this.eateries = eateries;
    }
    public MapsInteractor(){}

    /**
     * Retrieves an eatery based on its ID
     * @param id The ID of the target eatery
     * @return An eatery with the target ID. Returns a default eatery if there are no eateries with the specified ID
     */
    public static Eatery findEatery(String id)
    {
        for (int i = 0; i < eateries.size(); i++) {
            if(eateries.get(i).getEateryID().equals(id) == true)
            {
                Log.e("mapsInteractor", eateries.get(i).getEateryName());
                return eateries.get(i);
            }
        }
        return new Eatery("0", "Unable to find eatery", "", "", "");
    }

    /**
     * Retrieves an eatery based on its name
     * @param name  The name of the eatery
     * @param byName Set to true to find eatery by name. If set to false, this method finds eatery by ID instead.
     * @return An eatery with the same name.
     */
    public static Eatery findEatery(String name, boolean byName)
    {
        if(!byName)
        {
            return findEatery(name);
        }

        for (int i = 0; i < eateries.size(); i++) {
            if(eateries.get(i).getEateryName().equals(name) == true)
            {
                return eateries.get(i);
            }
        }
        return new Eatery("0", "Unable to find eatery", "", "", "");
    }

    /**
     * Return the names of all eateries
     * @return  A linked list of names
     */
    public static LinkedList<String> getEateryNames()
    {
        LinkedList<String> result = new LinkedList<String>();
        for (int i = 0; i < eateries.size(); i++) {
            result.add(eateries.get(i).getEateryName());
        }
        return result;
    }



    /**
     * Initializes the list of eateries. Creates an empty list if the data file is not found.
     * @param context The current activity
     */
    public static void initialize(Context context){

        if(eateries != null && eateries.size()!=0)
        {
            return;
        }

        eateries = new ArrayList<Eatery>();

        try {
            InputStream i = context.getAssets().open("healthier-eateries-kml.kml");
            BufferedReader r = new BufferedReader(new InputStreamReader(i));

            Pattern name_p = Pattern.compile("(<SimpleData name=\"NAME\">)(.*)(</SimpleData>)");
            Pattern id_p = Pattern.compile("(<name>)(.*)(</name>)");
            Pattern loc_address_p = Pattern.compile("(<SimpleData name=\"ADDRESSBUILDINGNAME\">)(.*)(</SimpleData>)");
            Pattern loc_street_p = Pattern.compile("(<SimpleData name=\"ADDRESSSTREETNAME\">)(.*)(</SimpleData>)");

            Matcher name_m;
            Matcher id_m;
            Matcher loc_address_m;
            Matcher loc_street_m;
            String cur = r.readLine();
            String name = null, loc_id = null, loc_address = null, loc_street = null;
            Eatery e;
            int ID = 0;
            while(cur != null)
            {
                name_m = name_p.matcher(cur);
                id_m = id_p.matcher(cur);
                loc_address_m = loc_address_p.matcher(cur);
                loc_street_m = loc_street_p.matcher(cur);
                if(id_m.find())
                {
                    loc_id = id_m.group(2);

                }
                if(loc_address_m.find())
                {
                    loc_address = loc_address_m.group(2);

                }
                if(loc_street_m.find())
                {
                    loc_street = loc_street_m.group(2);

                }
                if(name_m.find())
                {


                    name = name_m.group(2);
                    e = new Eatery(loc_id, name, loc_address, loc_street, "8:00-21:00");
                    eateries.add(e);
                    ID +=1;


                }
                cur = r.readLine();

            }



        } catch (IOException e) {
            // TODO Auto-generated catch block
            eateries = new ArrayList<Eatery>();

        }
    }
}
