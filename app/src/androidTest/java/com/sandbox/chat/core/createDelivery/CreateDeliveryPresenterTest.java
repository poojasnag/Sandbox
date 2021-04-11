package com.sandbox.chat.core.createDelivery;

import org.junit.Test;

import junit.framework.TestCase;

import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;
public class CreateDeliveryPresenterTest extends TestCase {
    CreateDeliveryPresenter createDeliveryPresenter;

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

    @Test
    public void invalid_delivererFee() {
        String etaString = "2021-04-21 18:03";
        String cutoffString = "2021-04-21 15:03";
        String delivererFee = "4";
        ArrayList<String> selectedLocs = new ArrayList<String>();
        selectedLocs.add("Hall 10");
        createDeliveryPresenter = new CreateDeliveryPresenter();
        assertThat(createDeliveryPresenter.validateCreateDelivery(delivererFee, etaString, cutoffString, selectedLocs)).isTrue();
    }
}