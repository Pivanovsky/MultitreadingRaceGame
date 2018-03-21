package com.classes;

public class MySecundomer {
    private int hour;
    private int min;
    private int sec;
    private int milisec;
    private boolean stopSec;

    public MySecundomer() {
        this.hour=0;
        this.min=0;
        this.sec=0;
        this.milisec=0;
        this.stopSec=false;
    }
    public void restart(){
        this.hour=0;
        this.min=0;
        this.sec=0;
        this.milisec=0;
        this.stopSec=false;
    }

    public void increaseMiliSec(int msec){
        milisec+=msec;
        if (milisec>=1000) {
            sec++;
            milisec = 0;
        }
        if (sec>=60) {
            min++;
            sec = 0;
        }
         if(min>=60){
            hour++;
            min=0;
            }
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d:%03d",hour,min,sec,milisec);
    }

    public boolean isStopSec() {
        return stopSec;
    }
}

