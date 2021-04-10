package com.sandbox.chat.core.maps;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.sandbox.chat.models.Eatery;

import org.junit.Test;

import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.*;

public class MapsInteractorTest {
    @Test
    public void listNotNull() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MapsInteractor.initialize(appContext);
        ArrayList<Eatery> eateries = MapsInteractor.getEateries();
        assertNotNull(eateries);
    }
    @Test
    public void firstEatery() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MapsInteractor.initialize(appContext);
        ArrayList<Eatery> eateries = MapsInteractor.getEateries();
        assertThat("kml_1").isEqualTo(eateries.get(0).getEateryID());
        assertThat("McDonald's - Bugis Junction").isEqualTo(eateries.get(0).getEateryName());
        assertThat("Bugis Junction").isEqualTo(eateries.get(0).getEateryAddress());
        assertThat("Victoria Street").isEqualTo(eateries.get(0).getEateryStreet());
    }

    @Test
    public void lastEatery() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MapsInteractor.initialize(appContext);
        ArrayList<Eatery> eateries = MapsInteractor.getEateries();
        assertThat("kml_1810").isEqualTo(eateries.get(1809).getEateryID());
        assertThat("Foreword Coffee - Temasek Shophouse").isEqualTo(eateries.get(1809).getEateryName());
        assertThat("Temasek Shophouse").isEqualTo(eateries.get(1809).getEateryAddress());
        assertThat("Orchard Road").isEqualTo(eateries.get(1809).getEateryStreet());
    }

    @Test
    public void findEatery_correct()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MapsInteractor.initialize(appContext);
        assertThat("KOI - Waterway Point").isEqualTo(MapsInteractor.findEatery("kml_935").getEateryName());
    }

    @Test
    public void findEatery_incorrect()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MapsInteractor.initialize(appContext);
        assertThat("Unable to find eatery").isEqualTo(MapsInteractor.findEatery("This is not a real eatery").getEateryName());
    }
}