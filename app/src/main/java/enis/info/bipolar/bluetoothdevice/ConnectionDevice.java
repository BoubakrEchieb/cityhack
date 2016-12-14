package enis.info.bipolar.bluetoothdevice;

import android.bluetooth.BluetoothAdapter;
import android.widget.Toast;

import com.neurosky.thinkgear.TGDevice;

/**
 * Created by be on 16/10/16.
 */

public class ConnectionDevice {

    private static final String TAG = "HelloEEG";

    BluetoothAdapter bluetoothAdapter;
    TGDevice tgDevice;
    final boolean               rawEnabled = true;


    public void establishConnection(){
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if( bluetoothAdapter == null ) {

            return;

        } else {

            // Create the TGDevice
            //tgDevice = new TGDevice( bluetoothAdapter, handler );

        }
    }

}
