package com.jj15.iremote4;



import static com.jj15.iremote4.R.id.*;
import androidx.appcompat.app.AppCompatActivity;
import com.jj15.iremote4.IRBulkTX;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.jj15.iremote4.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public String IRDB_index="";
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
        public ConsumerIrManager ir = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);
        TextView log = findViewById(R.id.mtx);
        log.setMovementMethod(new ScrollingMovementMethod());


        IRDefsCh irDefsCh = new IRDefsCh();
        IRDefsPower irDefsPower = new IRDefsPower();
        IRDefsUtil irDefsUtil = new IRDefsUtil();
        IRDefsVol irDefsVol = new IRDefsVol();
        IRBulkTX irBulkTX = new IRBulkTX();
        IRUtils iru = new IRUtils();


        final ImageButton beginbtn = findViewById(R.id.beginbutton);
        final ImageButton volumeupbutton = findViewById(R.id.volumeupbutton);
        final ImageButton volumedownbutton = findViewById(R.id.volumedownbutton);
        final ImageButton chplusbutton = findViewById(R.id.chplusbutton);
        final ImageButton chminusbutton = findViewById(R.id.chminusbutton);
        final ImageButton menubutton = findViewById(R.id.menubutton);
        final ImageButton mutebutton = findViewById(R.id.mutebutton);



        beginbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[] totx = irDefsPower.getPower();
                irBulkTX.BulxTX(totx, log);
            }
        });

        volumeupbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String[] totx = irDefsVol.getVolUp();
                irBulkTX.BulxTX(totx, log);
            }
        });

        volumedownbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String[] totx = irDefsVol.getVolDown();
                irBulkTX.BulxTX(totx, log);
            }
        });

        chplusbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String[] totx = irDefsCh.getChPlus();
                irBulkTX.BulxTX(totx, log);
            }
        });

        chminusbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String[] totx = irDefsCh.getChMinus();
                irBulkTX.BulxTX(totx, log);
            }
        });

        mutebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String[] totx = irDefsUtil.getMute();
                irBulkTX.BulxTX(totx, log);
            }
        });

        menubutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String[] totx = irDefsUtil.getMenu();
                String[] testx = {"NEC,32,159,3", "NEC,32,159,13"};
                irBulkTX.BulxTX(testx, log);
            }
        });

    }
    public void updateTv(TextView log, String msg){
        final String str = msg;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                log.setText(str);
            }
        });
    }
}