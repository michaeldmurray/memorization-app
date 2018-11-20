package csc472.depaul.edu.micvalmoy.tools;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import csc472.depaul.edu.micvalmoy.R;

public class HelperFunctions {





    public static void createNewPin(Context context)
    {
        final EditText pin = new EditText(context);
        pin.setHint("****");
        pin.setTextSize(24);
        pin.setTypeface(null, Typeface.BOLD);
        pin.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        pin.setFilters(new InputFilter[] {
                // Maximum 2 characters.
                new InputFilter.LengthFilter(4),
        });
        // Digits only & use numeric soft-keyboard.
        pin.setKeyListener(DigitsKeyListener.getInstance(Locale.getDefault()));

        final TextView pinTitle = new TextView(context);
        //pinTitle.setText(context.getResources().getString(R.string.create_new_pin));
        pinTitle.setText("this is an coded texxtview");
        pinTitle.setTextSize(24);
        pinTitle.setTypeface(null, Typeface.BOLD);
        pinTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        final AlertDialog createPin = new AlertDialog.Builder(context)
                .setCustomTitle(pinTitle)
                .setView(pin)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                    }
                })
                .setOnKeyListener(new DialogInterface.OnKeyListener()
                {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
                    {
                        if (keyCode==KeyEvent.KEYCODE_ENTER)
                        {
                            if (pin.getText().length() == 4)
                            {
                                dialog.dismiss();

                                //Save your PIN here
                            }
                        }

                        return false;
                    }
                }).create();
        createPin.show();

        createPin.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (pin.getText().length() == 4)
                {
                    createPin.dismiss();

                    //Save You pin
                }
            }
        });;

        createPin.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        createPin.show();
    }


}
