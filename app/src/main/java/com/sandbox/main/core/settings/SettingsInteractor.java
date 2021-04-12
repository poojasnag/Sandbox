package com.sandbox.main.core.settings;

import com.google.android.libraries.maps.GoogleMap;

/**
 * Stores and sends settings information
 * @author Luong Minh Quang
 */
public class SettingsInteractor implements SettingsContract.Interactor{
    /**
     * The map type selected by the user
     */
    private static int mapType;

    /**
     * Gets the map type
     * @return the map type

     */
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
