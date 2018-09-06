package com.example.marco.myfeeder;

import java.util.Arrays;

public class Configuration {


    private static class Format{
        public String name;//max 16 chars
        public int times[];
    }
    public static final int size = 8;
    public static final int length = 32;

    private static int active;//min 0, max 7
    private static Format formats[];

    static{
        formats=new Format[size];
        active=3;
        for(int i=0; i<size; i++){

            formats[i]=new Format();
            formats[i].name= "Formato "+i;
            formats[i].times= new int[length];
            for (int j=0;j<2; j++){
                formats[i].times[j]=1;
            }
            for (int j=2;j<length; j++){
                formats[i].times[j]=-1;
            }

        }
    }

    private void update(){
        //TODO send via bt
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
        }
    }

    public static void setName(int index,String s) {
        //check len < 16
        formats[index].name=s;
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
    }


    private Configuration(){}

}
