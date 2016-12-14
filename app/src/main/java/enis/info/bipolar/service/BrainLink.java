package enis.info.bipolar.service;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.neurosky.thinkgear.TGDevice;

import enis.info.bipolar.connection.Connection;
import enis.info.bipolar.connection.LoginAsyncTask;

public class BrainLink extends Service {
    public BrainLink() {
    }
    private static final String TAG = "HelloEEG";
    public static String token;
    BluetoothAdapter            bluetoothAdapter;
    TGDevice                    tgDevice;
    final boolean               rawEnabled = true;



    /** Called when the activity is first created. */
    @Override
    public void onCreate( ) {


        token= LoginAsyncTask.token;
                // Check if Bluetooth is available on the Android device
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if( bluetoothAdapter == null ) {

            // Alert user that Bluetooth is not available
            Toast.makeText( this, "Bluetooth not available", Toast.LENGTH_LONG ).show();

            return;

        } else {

            // Create the TGDevice
            tgDevice = new TGDevice( bluetoothAdapter, handler );

        }
        doStuff();
        super.onCreate();;

    } /* end onCreate() */

    public void doStuff( ) {

        if( tgDevice.getState() != TGDevice.STATE_CONNECTING &&
                tgDevice.getState() != TGDevice.STATE_CONNECTED ) {

            tgDevice.connect( rawEnabled );
        }

    } /* end doStuff() */

    @Override
    public void onDestroy() {
        tgDevice.close();
        Log.d(TAG, "Finish...\n" );
        super.onDestroy();
    }

    final Handler handler = new Handler() {

        @Override
        public void handleMessage( Message msg ) {

            switch( msg.what ) {
                case TGDevice.MSG_STATE_CHANGE:

                    switch( msg.arg1 ) {
                        case TGDevice.STATE_IDLE:
                            break;
                        case TGDevice.STATE_ERR_BT_OFF:
                            Log.d(TAG,"Bluetooth is off.  Turn on Bluetooth and try again." );
                            break;
                        case TGDevice.STATE_CONNECTING:
                            Log.d(TAG, "Connecting...\n" );
                            break;
                        case TGDevice.STATE_ERR_NO_DEVICE:
                            Log.d(TAG, "No Bluetooth devices paired.  Pair your device and try again.\n" );
                            break;
                        case TGDevice.STATE_NOT_FOUND:
                            Log.d(TAG,"Could not connect any of the paired BT devices.  Turn them on and try again.\n" );
                            break;
                        case TGDevice.STATE_CONNECTED:
                            Log.d(TAG, "Connected.\n" );

                            tgDevice.start();
                            break;
                        case TGDevice.STATE_DISCONNECTED:
                            Log.d(TAG, "Disconnected.\n" );
                    } /* end switch on msg.arg1 */

                    break;

                case TGDevice.MSG_POOR_SIGNAL:
                    Log.d(TAG,"PoorSignal: " + msg.arg1 + "\n" );
                    break;

                case TGDevice.MSG_RAW_DATA:
                	/* Handle raw EEG/EKG data here */
                    break;

                case TGDevice.MSG_HEART_RATE:
                    Log.d(TAG, "Heart rate: " + msg.arg1 + "\n" );
                    break;

                case TGDevice.MSG_ATTENTION:
                    Log.d(TAG,"Attention: " + msg.arg1 + "\n" );
                    if(msg.arg1>90)
                    {
                        StaticSend.send();
                    }
                    break;

                case TGDevice.MSG_MEDITATION:
                    Log.d(TAG, "Meditation: " + msg.arg1 + "\n" );
                    if(msg.arg1>90)
                    {
                        StaticSend.send();
                    }
                    break;

                case TGDevice.MSG_BLINK:
                    Log.d(TAG, "Blink: " + msg.arg1 + "\n" );
                    break;

                default:
                    break;

            } /* end switch on msg.what */


        } /* end handleMessage() */

    }; /* end Handler */



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
