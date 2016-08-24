package com.example.qube.firebasechatroom;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Qube on 8/24/16.
 */
public class ColorChangeDialog {

    public void displayColorChangeDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogLayout = inflater.inflate(R.layout.dialog_color_change, null);
        builder.setView(dialogLayout);

        final AlertDialog dialog = builder.create();
        dialog.show();


    }

    public void clickMethod(View view){
        
    }
}
