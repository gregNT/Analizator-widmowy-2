package com.agh.zlatka;

import jm.util.* ;

public class Main {

    public static void main(String args[]) {

        /* Sygnał specjalnie dobrałem tak, żeby kanały różniły się pomiędzy sobą w sensie odsłuchowym
        (głoska 'u', kanał lewy - sygnał akustyczny, kanał prawy - sygnał elektroglotograficzny. */
        Signal waveform = new Signal("sound/test_stereo_2.wav");

        MyFrame okno = new MyFrame();


       //waveform.drawWave();
        waveform.printInfo();

        // To właściwie test, czy separacja kanałów działa poprawnie (tylko pytanie, jak to przenieść do jUnit).
        float [] chLeft = waveform.getChannel(Signal.chName.CH_LEFT) ;
        float [] chRight = waveform.getChannel(Signal.chName.CH_RIGHT) ;

        System.out.println("Ilość próbek:\n kanał lewy - " + chLeft.length + "\nkanał prawy - " + chRight.length) ;

        Write.audio(chLeft, "test_stereo_2_ch_left.wav", 1, waveform.getFs(), waveform.getBitResolution()) ;
        Write.audio(chRight, "test_stereo_2_ch_right.wav", 1, waveform.getFs(), waveform.getBitResolution()) ;

    }
}