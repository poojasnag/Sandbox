package com.sandbox.main.core.createDelivery;

import android.util.Log;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;

/*
     Requirements:
     0f <= deliveryFee <= 20
     now < cutoff <= eta

 */
public class CreateDeliveryPresenterTest {
    CreateDeliveryPresenter createDeliveryPresenter;
    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime now = LocalDateTime.now();

    //Test 1
    @Test
    public void valid_createDelivery() {
        LocalDateTime cutoffDT = now.plusMinutes(10);
        String cutoffString = cutoffDT.format(f);
        LocalDateTime etaDT = now.plusMinutes(20);
        String etaString = etaDT.format(f);

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
        LocalDateTime cutoffDT = now.plusMinutes(10);
        String cutoffString = cutoffDT.format(f);
        LocalDateTime etaDT = now.plusMinutes(20);
        String etaString = etaDT.format(f);

        String delivererFee = "0";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isTrue();
    }
    //Test 3
    @Test
    public void valid_delivererFee_upper() {
        LocalDateTime cutoffDT = now.plusMinutes(10);
        String cutoffString = cutoffDT.format(f);
        LocalDateTime etaDT = now.plusMinutes(20);
        String etaString = etaDT.format(f);

        String delivererFee = "20";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isTrue();
    }
    //Test 4
    @Test
    public void invalid_delivererFee_lower() {
        LocalDateTime cutoffDT = now.plusMinutes(10);
        String cutoffString = cutoffDT.format(f);
        LocalDateTime etaDT = now.plusMinutes(20);
        String etaString = etaDT.format(f);

        String delivererFee = "-1";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isFalse();
    }
    //Test 5
    @Test
    public void invalid_delivererFee_upper() {
        LocalDateTime cutoffDT = now.plusMinutes(10);
        String cutoffString = cutoffDT.format(f);
        LocalDateTime etaDT = now.plusMinutes(20);
        String etaString = etaDT.format(f);

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
        LocalDateTime cutoffDT = now.plusMinutes(20);
        String cutoffString = cutoffDT.format(f);
        LocalDateTime etaDT = now.plusMinutes(20);
        String etaString = etaDT.format(f);

        String delivererFee = "4";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isTrue();
    }
    //Test 7
    @Test
    public void invalid_cutoff_upper() {
        LocalDateTime cutoffDT = now.plusMinutes(21);
        String cutoffString = cutoffDT.format(f);
        LocalDateTime etaDT = now.plusMinutes(20);
        String etaString = etaDT.format(f);

        String delivererFee = "4";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isFalse();
    }

    //Test 8
    @Test
    public void invalid_cutoff_lower() {
        LocalDateTime cutoffDT = now.minusMinutes(1);
        String cutoffString = cutoffDT.format(f);
        Log.e("cutoffbefnow", cutoffString);
        LocalDateTime etaDT = now.plusMinutes(20);
        String etaString = etaDT.format(f);

        String delivererFee = "4";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isFalse();
    }
    //Test 9
    @Test
    public void invalid_cutoff_lowerbound() {
        String cutoffString = now.format(f);
        LocalDateTime etaDT = now.plusMinutes(20);
        String etaString = etaDT.format(f);

        String delivererFee = "4";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isFalse();
    }

    //ETA BV and EC
    //Test 10
    @Test
    public void invalid_before_now() {
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
    //Test 11
    @Test
    public void invalid_on_now() {
        String cutoffString = now.format(f);
        String etaString = now.format(f);
        Log.e("now time", etaString);
        String delivererFee = "4";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isFalse();
    }
    //Test 12
    @Test
    public void invalid_selectloc_empty() {
        LocalDateTime cutoffDT = now.plusMinutes(10);
        String cutoffString = cutoffDT.format(f);
        LocalDateTime etaDT = now.plusMinutes(20);
        String etaString = etaDT.format(f);

        String delivererFee = "4";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isFalse();
    }

}