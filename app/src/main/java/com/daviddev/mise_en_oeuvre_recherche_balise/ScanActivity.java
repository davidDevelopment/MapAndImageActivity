package com.daviddev.mise_en_oeuvre_recherche_balise;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class ScanActivity extends Activity implements View.OnClickListener{

    RelativeLayout lay;
    NfcAdapter nfcAdapter;
    Tag tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_scan);

        lay = findViewById(R.id.lay);
        lay.setOnClickListener(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.9), (int) (height * 0.9));

        //Get the NFC adapter to check if the NFC feature is ok.
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (isNfcEnable(nfcAdapter) != true) {
            Toast.makeText(this, "Please enable NFC feature", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        enableCatchingNfcIntents();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        //Check if the new intent is an NFC intent
        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {

            //Extract tag object from the intent
            tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            //Read the NFC tag
            String payloadString = readNdefMessage(tag);
            int GéocacheID = Integer.parseInt(payloadString);

            intent = new Intent(this, QuestionActivity.class);
            intent.putExtra("GéocacheID", GéocacheID);
            startActivity(intent);

            Toast.makeText(this, "number is: " + payloadString, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onClick(View v) {
        ScanActivity.this.finish();
    }

    // Disable Catching Nfc Intents
    @Override
    protected void onPause() {
        super.onPause();
        disableCatchingNfcIntents();
    }

    //Enable Catching Nfc intents
    @Override
    protected void onResume() {
        super.onResume();
        // enableCatchingNfcIntents function must be called in onResume methode.

    }

    //Methode to check if NFC feature is enable
    private boolean isNfcEnable(NfcAdapter nfcAdapter) {
        if (nfcAdapter == null || !nfcAdapter.isEnabled())
            return false;
        else
            return true;
    }

    //Methode to read
    private String readNdefMessage(Tag tag){

        //Check if the tag object is null
        if (tag == null) {
            Toast.makeText(this, "Tag object cannot be null.", Toast.LENGTH_SHORT).show();
            return "";
        }

        String payloadString = "";

        try {
            /* Methode to extract NdefString from a cached tag:
             * Tag
             *     -> Ndef
             *             -> NdefMessage
             *                            -> NdefRecords
             *                                           -> recordString
             *  V V V The methode is implemented below V V V
             */

            //Extract ndef object from the tag
            Ndef ndef = Ndef.get(tag);

            //Extract ndefmessage from the ndef object
            NdefMessage ndefMessage = ndef.getCachedNdefMessage();

            //Extract ndef records from the ndef message
            NdefRecord[] records = ndefMessage.getRecords();

            //If records is empty
            if (records.length == 0)
            {
                return "";
            }
            //If records is not empty
            else {
                //Parse records and extract the record string
                for (NdefRecord Record : records) {

                    if (Record.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(Record.getType(), NdefRecord.RTD_TEXT)) {
                        //Get payload array from the record
                        byte[] contentpayload = Record.getPayload();

                        String Encoding = ((contentpayload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
                        int languageCodeLength = contentpayload[0] & 0063;

                        //Decode payload array and create payloadString.
                        payloadString = new String(contentpayload, languageCodeLength + 1, contentpayload.length - languageCodeLength - 1, Encoding);
                    }
                }
            }

        }
        catch (UnsupportedEncodingException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return payloadString;
    }

    // MainActivity take the hand on NFC intents
    private void enableCatchingNfcIntents() {

        // FLAG_RECEIVER_REPLACE_PENDING allow the MainActivity to stay in the foreground
        Intent intent = new Intent(this, ScanActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        // Argument one for enableForegroundDIspatch
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        // Argument two for enableForegroundDIspatch
        IntentFilter[] intentFilter = new IntentFilter[]{};
        // Allow to handle NFC events in this activity
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
    }

    // MainActivity lost the hand on NFC intents
    private void disableCatchingNfcIntents() {
        nfcAdapter.disableForegroundDispatch(this);
    }

}
