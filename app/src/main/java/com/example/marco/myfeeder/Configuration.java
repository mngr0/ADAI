package com.example.marco.myfeeder;


import android.util.Log;

import com.example.marco.myfeeder.ble.BluetoothChatService;

import java.util.Arrays;

public class Configuration {

    private static BluetoothChatService mBCS;

    public static String getLog() {
        return log;
    }

    private static class Format{
        public String name;//max 16 chars
        public int times[];
    }
    public static final int size = 8;
    public static final int length = 32;
    private static String log;
    private static int active;//min 0, max 7
    private static Format formats[];
    private static LogReceivedListener mListener;

    static{
        formats=new Format[size];
        active=3;
        for(int i=0; i<size; i++){

            formats[i]=new Format();
            formats[i].name= "Format "+i;
            formats[i].times= new int[length];
            for (int j=0;j<2; j++){
                formats[i].times[j]=1;
            }
            for (int j=2;j<length; j++){
                formats[i].times[j]=-1;
            }

        }
    }

    private static String serialize(){
        String ser="";
        ser+=active;
        for (int i=0; i<size; i++){
            ser+="#";
            ser+=formats[i].name;
            for (int j=0;j<length;j++){
                ser+=";";
                ser+=formats[i].times[j];
            }

        }
        return ser;
    }



    public static void logReceived(String mLog){
        log=mLog;
    }


    public static boolean readLog(LogReceivedListener listener){
        mBCS= BluetoothChatService.getInstance();
        if(mBCS.isConnected()){
            mBCS.write("logrequest".getBytes());
            mListener=listener;
            return true;
        }else{
            Log.d("Conf","unable to connect");
            return false;
        }

    }

    public static void deserialize(String received){
        String pieces [] = received.split("#");
        active= Integer.parseInt(pieces[0]);
        for (int i=1; i<size; i++){
            String [] parts = pieces[i].split(";");
            formats[i-1].name= parts[0];
            for (int j=1;j<length;j++){
                formats[i-1].times[j-1]=Integer.parseInt(parts[j]);
            }
        }
    }

    private static void update(){
        mBCS= BluetoothChatService.getInstance();
        if(mBCS.isConnected()){
            mBCS.write(serialize().getBytes());
        }else{
            Log.d("Conf","unable to connect");
        }
    }


    public static String getName(int i){
        return formats[i].name;
    }

    public static int getActive(){
        return active;
    }

    public static void setActive(int i){
        if((i<size)&&(i>=0)){
            active=i;
            update();
        }
    }

    public static void setName(int index,String s) {
        //check len < 16
        formats[index].name=s;
        update();
    }

    public static  int[] getTimes(int index){
        int last=0;
        while((formats[index].times[last]!=-1)&&(last<length-1)){
            last++;
        }
        return Arrays.copyOfRange(formats[index].times,0,last);
    }

    public static void setTimes(int index, int[] times) {
        //Log.d("CONF","");
        for(int i=0; i<length;i++){
            if(i<times.length){
                formats[index].times[i]=times[i];
            }else{
                formats[index].times[i]=-1;
            }
        }
        update();
    }

    private Configuration(){}

}
