package com.holopro;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.p003v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.holopro.R;
import com.holopro.ref.ButtonOnClickFac;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {
    private final String MY_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    /* access modifiers changed from: private */
    public String address;
    /* access modifiers changed from: private */
    public ImageButton displayButton;
    private ButtonOnClickFac fac;
    /* access modifiers changed from: private */
    public BluetoothAdapter mBluetoothAdapter = null;
    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        /* JADX WARNING: type inference failed for: r2v4, types: [java.lang.CharSequence, java.lang.String] */
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            BluetoothActivity.this.mBluetoothAdapter.cancelDiscovery();
            String charSequence = ((TextView) view).getText().toString();
            String unused = BluetoothActivity.this.address = charSequence.substring(charSequence.length() - 17);
            TextView access$200 = BluetoothActivity.this.reminder_ii;
            access$200.setText("Address: " + BluetoothActivity.this.address);
            BluetoothActivity.this.displayButton.setVisibility(0);
        }
    };
    /* access modifiers changed from: private */
    public ArrayAdapter<String> mNewDevicesArrayAdapter;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.bluetooth.device.action.FOUND".equals(action)) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                String name = bluetoothDevice.getName();
                if (bluetoothDevice.getBondState() != 12 && name != null) {
                    ArrayAdapter access$400 = BluetoothActivity.this.mNewDevicesArrayAdapter;
                    access$400.add(name + "\n" + bluetoothDevice.getAddress());
                }
            } else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                if (BluetoothActivity.this.mNewDevicesArrayAdapter.getCount() == 0) {
                    BluetoothActivity.this.mNewDevicesArrayAdapter.add("There is no new available devices.");
                }
                BluetoothActivity.this.progressBar.setVisibility(8);
                BluetoothActivity.this.reminder.setText("Searching Finish.");
            }
        }
    };
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    /* access modifiers changed from: private */
    public TextView reminder;
    /* access modifiers changed from: private */
    public TextView reminder_ii;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_bluetooth);
        Context applicationContext = getApplicationContext();
        this.fac = new ButtonOnClickFac(this);
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.address = BuildConfig.FLAVOR;
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar_bt);
        this.progressBar.setVisibility(8);
        this.displayButton = (ImageButton) findViewById(R.id.displayButton_bt);
        this.displayButton.setVisibility(8);
        this.reminder = (TextView) findViewById(R.id.reminder_bt);
        this.reminder_ii = (TextView) findViewById(R.id.reminder_bt_ii);
        if (this.mBluetoothAdapter == null) {
            Toast.makeText(applicationContext, "Bluetooth is not supported.", 0).show();
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, 17367043);
        this.mNewDevicesArrayAdapter = new ArrayAdapter<>(this, 17367043);
        ListView listView = (ListView) findViewById(R.id.new_devices);
        listView.setAdapter(this.mNewDevicesArrayAdapter);
        listView.setOnItemClickListener(this.mDeviceClickListener);
        registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.device.action.FOUND"));
        registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED"));
        Set<BluetoothDevice> bondedDevices = this.mBluetoothAdapter.getBondedDevices();
        if (!this.mBluetoothAdapter.isEnabled()) {
            Set<BluetoothDevice> bondedDevices2 = this.mBluetoothAdapter.getBondedDevices();
            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 1);
            loadAvailableDevices(bondedDevices2, arrayAdapter);
            ListView listView2 = (ListView) findViewById(R.id.paired_devices);
            listView2.setAdapter(arrayAdapter);
            listView2.setOnItemClickListener(this.mDeviceClickListener);
            return;
        }
        loadAvailableDevices(bondedDevices, arrayAdapter);
        ListView listView3 = (ListView) findViewById(R.id.paired_devices);
        listView3.setAdapter(arrayAdapter);
        listView3.setOnItemClickListener(this.mDeviceClickListener);
    }

    private void loadAvailableDevices(Set<BluetoothDevice> set, ArrayAdapter<String> arrayAdapter) {
        if (set.size() > 0) {
            for (BluetoothDevice bluetoothDevice : set) {
                arrayAdapter.add(bluetoothDevice.getName() + "\n" + bluetoothDevice.getAddress());
            }
            return;
        }
        arrayAdapter.add("There is no device connected before.");
    }

    public void onDestroy() {
        super.onDestroy();
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.cancelDiscovery();
        }
        unregisterReceiver(this.mReceiver);
    }

    private void doDiscovery() {
        if (this.mBluetoothAdapter.isDiscovering()) {
            this.mBluetoothAdapter.cancelDiscovery();
        }
        this.mBluetoothAdapter.startDiscovery();
    }

    public void scanOnClick(View view) {
        this.progressBar.setVisibility(0);
        this.reminder.setText("Scanning...");
        doDiscovery();
    }

    public void menuBTOnClick(View view) {
        this.fac.setMenuButton();
        finish();
    }

    public void displayBTOnClick(View view) {
        if (!this.address.equals(BuildConfig.FLAVOR)) {
            this.fac.setChooseFileButton(this.address);
        } else {
            Toast.makeText(getApplicationContext(), "Connect your bluetooth first.", 0);
        }
    }
}
