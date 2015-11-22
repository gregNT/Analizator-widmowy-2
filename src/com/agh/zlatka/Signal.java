package com.agh.zlatka ;

import jm.gui.wave.WaveFileReader ;
import jm.gui.wave.WaveView ;
import jm.util.* ;

public class Signal {

    // Typy wyliczeniowe
    public enum chName {CH_LEFT, CH_RIGHT}

    // - - - Zmienne - - -
    private int fs, bit, ch ;
    private float [] data, leftChannel, rightChannel ;
    private String path ;
    private WaveFileReader wv ;

    // - - - Metody - - -

    // Konstruktor podstawowy.
    public Signal (String wavePath) {

        // Wczytanie próbek sygnału do zmiennej "data".
        path = wavePath ;
        data = Read.audio(path) ;

        // Wczytanie informacji o pliku wave.
        wv = new WaveFileReader(path) ;
        fs = wv.getSampleRate() ;
        bit = wv.getBitResolution() ;
        ch = wv.getChannels() ;

        // - - - Separacja kanałów. - - -
        if (ch == 2) {

            // Określenie długości kanału.
            int chLen ;
            if (data.length % 2 == 0)
                chLen = data.length / 2 ;
            else
                chLen = (data.length + 1) / 2 ;

            leftChannel = new float[chLen] ;
            rightChannel = new float[chLen] ;

            for (int i = 0; i < chLen - (data.length % 2); ++i) {
                leftChannel[i] = data[2 * i];
                rightChannel[i] = data[2 * i + 1];
            }
            /* Przypisanie wartości ostatnich próbek kanałów. Gdy liczba
            próbek wave jest nieparzysta to ostatniej próbce lewego
            kanału przypisujemy ostatnią próbkę pliku wave,
            a ostatniej próbce prawego - zero (podejrzewam, że tak robi MATLAB).
            */
            if (data.length % 2 == 1)
                leftChannel[chLen - 1] = data[data.length - 1] ;
                rightChannel[chLen - 1] = 0 ;
        }
    }

    // Wyświetlenie parametrów pliku wave w konsoli.
    public void printInfo() {
        System.out.printf("Sample rate: %d, Bit depth: %d%n", fs, bit) ;
    }

    // Narysowanie oscylogramu poprzez "mini-GUI" wbudowane w bibliotekę jMusic
    public void drawWave() {
        WaveView wView = new WaveView(path) ;
    }

    // "Gettery" - zwracają wartości składowych prywatnych.
    public int getFs(){
        return fs ;
    }

    public int getNumOfCh(){
        return ch ;
    }

    public float [] getData(){
        return data ;
    }

    public String getPath(){
        return path ;
    }

    /* Ta funkcja powinna obsługiwać przypadek, w którym nagranie ma jeden kanał,
     a użytkownik zażąda lewego lub prawego kanału. */
    public float [] getChannel(chName name) {
        switch (name) {
            case CH_LEFT:
                if (ch == 2)
                    return leftChannel ;
                else
                    System.out.println("Nagranie posiada tylko 1 kanał.") ;
                    return new float [] {} ;
            case CH_RIGHT:
                if (ch == 2)
                    return rightChannel ;
                else
                    System.out.println("Nagranie posiada tylko 1 kanał.") ;
                    return new float [] {} ;
            // Ten przypadek nie powinien nigdy nastąpić.
            default:    return data ;
        }
    }
}

