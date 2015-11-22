package com.agh.zlatka;


public class Main {

    public static void main(String args[]) {

        Signal waveform = new Signal("sound/test_stereo.wav");

        waveform.drawWave();
        waveform.printInfo();

        // Z tego można by zrobić test konstruktora klasy Signal
        float [] chLeft = waveform.getChannel(Signal.chName.CH_LEFT) ;
        float [] chRight = waveform.getChannel(Signal.chName.CH_RIGHT) ;

        System.out.println("Ilość próbek:\n kanał lewy - " + chLeft.length + "\nkanał prawy - " + chRight.length) ;
    }
}