package tylerjxzhangtexty.texty;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class SmsListener extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            Log.d("main", "Msg got");
            String msg_from;
            if (bundle != null){
                //---retrieve the SMS message received---
                Log.i("maihhiin", "Msg Received");
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        if(msg_from.contains("2672336296")){
                            String msgBody = msgs[i].getMessageBody();
                            Log.i("main", msgBody);
                            EditText edit2 = (EditText)((Activity)context).findViewById(R.id.editText2);
                            if(edit2 == null){
                                Log.i("error", "error");
                            }else {
                                edit2.setText(msgBody);
                            }
                        }

                    }
                }catch(Exception e){
//                      Log.d("Exception caught",e.getMessage());
                }
            }
        }
    }
}