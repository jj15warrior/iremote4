package com.jj15.iremote4;

import android.hardware.ConsumerIrManager;
import com.jj15.iremote4.ProtoParser;
import android.widget.TextView;


import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import com.jj15.iremote4.MainActivity;
import java.util.ServiceConfigurationError;

public class IRBulkTX extends AppCompatActivity{

    public boolean BulxTX(String[] totx, TextView log){
        IRUtils iru = new IRUtils();
        String logstr = "starting...";
        MainActivity mainActivity = new MainActivity();
        mainActivity.updateTv(log, logstr);
        ProtoParser pp = new ProtoParser();
        ConsumerIrManager ir = mainActivity.ir;
        for (int i = 0; i < totx.length; i++) {
            if(i%500==0){
                ir = mainActivity.ir;
            }
            try{
                String proto = totx[i].split(",")[0];
                proto = pp.parse(proto); // fix protocol names
                mainActivity.updateTv(log, (i/totx.length)*100 + "%");
                int dev = Integer.parseInt(totx[i].split(",")[1]);
                int subdev = Integer.parseInt(totx[i].split(",")[2]);
                int funct = Integer.parseInt(totx[i].split(",")[3]);
                String forbidden = "Bose,CanalSat,NEC1-f16,RCA-38(Old),RCA(Old),Velleman,Replay,Sejin-1-56,Grundig16-30,Akai,NRC16,NEC2-f16,PaceMSS";
                if(!forbidden.contains(proto)){
                    logstr = totx[i] + " : " + i + "\n" + logstr;
                    logstr = logstr.substring(0, Math.min(logstr.length(),200));
                    mainActivity.updateTv(log, logstr);
                    //System.out.println(totx[i]+": "+i);
                    iru.TX(ir, proto, dev, subdev, funct);
                }
            }catch (IllegalArgumentException e){
                System.out.println(e.toString());
                System.out.println(totx[i].split(",")[0]);
                return false;

            }
        }
        logstr = "Done" + "\n" + logstr;
        logstr = logstr.substring(0,Math.min(logstr.length(),200));
        mainActivity.updateTv(log, logstr);

        return true;
    }
}
