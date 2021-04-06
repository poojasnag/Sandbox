package com.sandbox.chat.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;

public class MultiRadio extends androidx.appcompat.widget.AppCompatSpinner implements
        DialogInterface.OnClickListener, DialogInterface.OnCancelListener {


    private String[] items;
    private int selected;
    private String defaultText;
    private MultiRadioListener listener;

    public MultiRadio(Context context) {
        super(context);
    }

    public MultiRadio(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }

    public MultiRadio(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        this.selected = which;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        // refresh text on spinner
        StringBuffer spinnerBuffer = new StringBuffer();

        String spinnerText;
        if(selected != -1)
        {
            spinnerText = items[selected];
        }
        else {
            spinnerText = defaultText;
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item,
                new String[]{spinnerText});
        setAdapter(adapter);
        listener.onItemsSelected(selected);
    }

    @Override
    public boolean performClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setSingleChoiceItems(items, selected, this);
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.setOnCancelListener(this);
        builder.show();
        return true;
    }

    public void setItems(String[] items, String defaultText,
                         MultiRadioListener listener) {
        this.items = items;
        this.defaultText = defaultText;
        this.listener = listener;

        // all unselected by default
        selected = -1;


        // all text on the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, new String[]{defaultText});
        setAdapter(adapter);
    }

    public interface MultiRadioListener {
        public void onItemsSelected(int selected);
    }

    public String getItems(int index) {
        if(index == -1)
        {
            return defaultText;
        }
        return items[index];
    }
}


