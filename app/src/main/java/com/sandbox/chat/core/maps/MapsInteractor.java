package com.sandbox.chat.core.maps;

import android.content.Context;
import android.util.Log;

import com.sandbox.chat.models.Eatery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapsInteractor implements MapsContract.Interactor {

    private static ArrayList<Eatery> eateries;

    public MapsInteractor(ArrayList<Eatery> eateries){
        this.eateries = eateries;
    }
    public MapsInteractor(){}
    public static Eatery findEatery(String id)
    {
        Log.e("stringID", id);

        for (int i = 0; i < eateries.size(); i++) {
            Log.e("spam", eateries.get(i).getEateryID());

            if(eateries.get(i).getEateryID().equals(id) == true)
            {
                Log.e("mapsInteractor", eateries.get(i).getEateryName());
                return eateries.get(i);
            }

        }
        return new Eatery("0", "Unable to find eatery", "", "", "");
    }

    public static void initialize(Context context) throws IOException {

        eateries = new ArrayList<Eatery>();
        InputStream i = context.getAssets().open("healthier-eateries-kml.kml");
        try {
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
                    System.out.println(name);
                    System.out.println(loc_id);
                    e = new Eatery(loc_id, name, loc_address, loc_street, "8:00-21:00");
                    eateries.add(e);
                    ID +=1;
                    System.out.println("Record added");

                }
                cur = r.readLine();

            }



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }
}