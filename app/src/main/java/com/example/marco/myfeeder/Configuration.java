package com.example.marco.myfeeder;

public class Configuration {
    public static class Format{
        public String name;//max 16 chars
        public int overtime;
        public int undertime;
        public int times[];
        public boolean dotted[];
    }

    public static final int size = 8;
    public static int active;//min 0, max 7
    public static Format formats[];

    static{
        formats=new Format[size];
        active=3;
        for(int i=0; i<size; i++){

            formats[i]=new Format();
            formats[i].name= new String("Formato "+i);
            formats[i].times= new int[12];
            for (int j=0;j<12; j++){
                formats[i].times[j]=j;
            }

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
        }
    }


}
