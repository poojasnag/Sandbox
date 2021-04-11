package com.sandbox.chat.core.settings;

import android.util.Log;

import com.google.android.libraries.maps.GoogleMap;

/**
 * Stores and sends settings information
 */
public class SettingsInteractor {
    /**
     * The map type selected by the user
     */
    private static int mapType;


    public static int getMapType()
    {
        if(mapType != GoogleMap.MAP_TYPE_NORMAL && mapType!=GoogleMap.MAP_TYPE_HYBRID && mapType!=GoogleMap.MAP_TYPE_SATELLITE)
        {
            return GoogleMap.MAP_TYPE_NORMAL;
        }

        return mapType;
    }

    public static void setMapType(int type)
    {
        mapType = type;
    }
}
