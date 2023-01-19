package com.jj15.iremote4;

import android.hardware.ConsumerIrManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class IRUtils extends AppCompatActivity {
    static {
        System.loadLibrary("testc");
    }
    private int[] mapStrToInt(String STArr[]) { //Converts a string array to an int array
        int[] arr = new int[STArr.length];
        for (int i = 0; i < STArr.length; i++) {
            if(STArr[i].contains(".")){
                STArr[i] = STArr[i].split("\\.")[0];
            }
            if(STArr[i] != "") {
                arr[i] = Integer.parseInt(STArr[i]);

            }
        }
        for(int i = 0; i < arr.length; i++){
            if(arr[i]<=0){
                arr[i] = 1;
            }
        }
        return arr;
    }
    public int getFREQ(String a) {
        a = params(a);
        if(Objects.equals(a, "null")){
            throw new IllegalArgumentException("Invalid protocol");
        }
        a = a.replace(a.split("Frequency.*")[1], "");
        if (!a.startsWith("Frequency")){
            a = a.replace(a.split("Frequency.*")[0], "");
        }
        a = a.replace("Frequency=","");
        return Integer.parseInt(a);
    }

    public void TX(ConsumerIrManager ir, String prot, int dev, int subdev, int fn){ //Transmits a signal from simplified parameters
        int freq = getFREQ(prot);
        int[] pattern = mapStrToInt(generateIRSeq(prot, dev, subdev, fn).split(" ")); //converts simpliefied parameters to a pattern

        ir.transmit(freq, pattern);
    }

    public native String generateIRSeq(String proto, int device, int subdevice, int function);
    public native String params(String proto);
}
