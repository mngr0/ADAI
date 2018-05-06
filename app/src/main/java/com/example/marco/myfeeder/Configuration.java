package com.example.marco.myfeeder;

public class Configuration {
    public static class Format{
        public String name;//max 16 chars
        public int overtime;
        public int undertime;
        public int times[];
        public boolean dotted[];
    }



    public static int active;//min 0, max 7
    public static Format formats[];
    public static int machineType;// 1 or 2
    public static final int TYPE_ENCODER = 1;
    public static final int TYPE_TIMER = 2;

    static{
        formats=new Format[10];
        active=3;
        machineType=TYPE_ENCODER;
        for(int i=0; i<10; i++){

            formats[i]=new Format();
            formats[i].name= new String("Formato "+i);
            formats[i].times= new int[12];
            for (int j=0;j<12; j++){
                formats[i].times[j]=j;
            }

        }
    }



}
