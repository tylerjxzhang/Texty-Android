package tylerjxzhangtexty.texty;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by w540 on 2016-01-23.
 */
public class FabClickListener implements View.OnClickListener {
    private static final String ARG_SECTION_NUMBER = "section_number";

    private View rootView;
    private Fragment fragment;

    public FabClickListener(View rootView, Fragment fragment) {
        this.rootView = rootView;
        this.fragment = fragment;
    }

    //---sends an SMS message to another device---
    private void sendSMS(int sectionId, String[] data)
    {
        SmsManager smsManager = SmsManager.getDefault();
        String msg = "";
        switch(sectionId - 1){
            case 0:
                msg += "@translate ";
                msg += "@"+ data[1] + " @" + data[2] + " @";
                break;
            case 1:
                msg += "@currency ";
                msg += "@"+ data[1] + " @" + data[2] + " @";
                break;
            case 2:
                msg += "@temperature ";
                msg += "@" + data[2] + " @";
                break;
            case 3:
                msg += "@stock @";
                break;
        }

        msg += data[0];

        //smsManager.sendTextMessage("a12672336296", null, msg, null, null);
        Log.d("Main","Msg sent: " + msg);

    }

    @Override
    public void onClick(View view) {
        int section = this.fragment.getArguments().getInt(ARG_SECTION_NUMBER)-1;

        View rootView = this.rootView;
        EditText editTextT = (EditText) rootView.findViewById(R.id.editText);
        Spinner spinnerT = (Spinner) rootView.findViewById(R.id.spinner);
        Spinner spinner2T = (Spinner) rootView.findViewById(R.id.spinner2);

        if (editTextT.getText() != null && spinnerT.getSelectedItem() != null && spinner2T.getSelectedItem() != null) {
            String[] stringData = {editTextT.getText().toString(),
                    spinnerT.getSelectedItem().toString(),
                    spinner2T.getSelectedItem().toString()};
            Log.d("main", stringData[0] + " " + stringData[1] + " " + stringData[2]);
            sendSMS(section, stringData);
            Snackbar.make(rootView, "Sending Request, Please Wait", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            if (editTextT.getText() == null) {
                Log.d("Main", " editText null");
            }
            if (spinnerT.getSelectedItem() == null) {
                Log.d("Main", " spinner 1 null");
            }
            if (spinner2T.getSelectedItem() == null) {
                Log.d("Main", " spinner 2 null");
            }
            Snackbar.make(rootView, "Please Validate the Information", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}