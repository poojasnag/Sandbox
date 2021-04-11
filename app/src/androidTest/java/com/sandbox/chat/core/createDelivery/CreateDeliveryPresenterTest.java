package com.sandbox.chat.core.createDelivery;

import android.util.Log;

import org.junit.Test;

import junit.framework.TestCase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;
public class CreateDeliveryPresenterTest {
    CreateDeliveryPresenter createDeliveryPresenter;

    //Test 1
    @Test
    public void valid_createDelivery() {
        String etaString = "2021-04-21 18:03";
        String cutoffString = "2021-04-21 15:03";
        String delivererFee = "4";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isTrue();
    }

    // DeliveryFee BV and EC
    //Test 2
    @Test
    public void valid_delivererFee_lower() {
        String etaString = "2021-04-21 18:03";
        String cutoffString = "2021-04-21 15:03";
        String delivererFee = "0";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isTrue();
    }
    //Test 3
    @Test
    public void valid_delivererFee_upper() {
        String etaString = "2021-04-21 18:03";
        String cutoffString = "2021-04-21 15:03";
        String delivererFee = "20";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isTrue();
    }
    //Test 4
    @Test
    public void invalid_delivererFee_lower() {
        String etaString = "2021-04-21 18:03";
        String cutoffString = "2021-04-21 15:03";
        String delivererFee = "-1";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isFalse();
    }
    //Test 5
    @Test
    public void invalid_delivererFee_upper() {
        String etaString = "2021-04-21 18:03";
        String cutoffString = "2021-04-21 15:03";
        String delivererFee = "21";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isFalse();
    }
    // cutoff BV and EC

    //Test 6
    @Test
    public void valid_cutoff_upper() {
        String cutoffString = "2021-04-21 18:03";
        String etaString = "2021-04-21 18:03";

        String delivererFee = "4";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isTrue();
    }
    //Test 7
    @Test
    public void invalid_cutoff_upper() {
        String cutoffString = "2021-04-21 18:04";
        String etaString = "2021-04-21 18:03";

        String delivererFee = "4";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isFalse();
    }

    //Test 8
    @Test
    public void invalid_cutoff_lower() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime cutoffDT = now.minusMinutes(1);
        String cutoffString = cutoffDT.format(f);
        Log.e("cutoffbefnow", cutoffString);
        String etaString = "2021-04-21 18:03";

        String delivererFee = "4";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isFalse();
    }
    //Test 9
    @Test
    public void valid_cutoff_lower() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String cutoffString = now.format(f);
        String etaString = "2021-04-21 18:03";

        String delivererFee = "4";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isTrue();
    }

    //ETA BV and EC


    //Test 10
    @Test
    public void invalid_eta_lower() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime cutoffDT = now.minusMinutes(2);
        LocalDateTime etaDT = now.minusMinutes(1);
        String cutoffString = cutoffDT.format(f);
        String etaString = etaDT.format(f);
        String delivererFee = "4";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isFalse();
    }



}