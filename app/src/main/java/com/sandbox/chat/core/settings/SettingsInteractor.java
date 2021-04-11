package com.sandbox.chat.core.settings;

import android.util.Log;

import com.google.android.libraries.maps.GoogleMap;

public class SettingsInteractor {

    private static int mapType;

    public static int getMapType()
    {
        if(mapType != GoogleMap.MAP_TYPE_NORMAL && mapType!=GoogleMap.MAP_TYPE_HYBRID && mapType!=GoogleMap.MAP_TYPE_SATELLITE)
        {
            Log.w("GetMapType", "Map type set to "+ mapType);
            return GoogleMap.MAP_TYPE_NORMAL;
        }

        return mapType;
    }

    public static void setMapType(int type)
    {
        mapType = type;
        Log.w("mapTypeChange","map type is now " + type);
    }
}
