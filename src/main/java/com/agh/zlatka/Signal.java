package com.agh.zlatka;

import jm.gui.wave.WaveFileReader ;
import jm.gui.wave.WaveView ;
import jm.util.* ;

public class Signal {

    // Typy wyliczeniowe
    public enum chName {CH_LEFT, CH_RIGHT, CH_SUM, CH_DIFF}

    // - - - Zmienne - - -
    private int fs, bit, ch;
    private float [] data, leftChannel, rightChannel, sum, diff ;
    private String path ;
    private WaveFileReader wv ;

    // - - - Metody prywatne. - - -

    // suma: (L + R)/2
    private float[] calculateAvgSum( float[] a, float[] b )
    {
        float[] c = new float[a.length];

        for ( int i = 0; i < a.length; i++ )
        {
            c[i] = (a[i] + b[i]) / 2;
        }
        return c;
    }

    // różnica: (L - R)/2
    private float[] calculateAvgDiff( float[] a, float[] b )
    {
        float[] c = new float[a.length];

        for ( int i = 0; i < a.length; i++ )
        {
            c[i] = (a[i] - b[i]) / 2;
        }
        return c;
    }

    // - - - Metody publiczne - - -

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

        // - - - Separacja kanałów. (przenieść to do osobnej funkcji prywatnej) - - -
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
            if (data.length % 2 == 1) {
                leftChannel[chLen - 1] = data[data.length - 1];
                rightChannel[chLen - 1] = 0;
            }
        }

        // Jeśli sygnał stereo to oblicz sumę i różnicę
        if (ch == 2) {
            // - - - Obliczenie (L+R)/2 (czyli sum) i (L-R)/2 (czyli diff)
            sum = calculateAvgSum(leftChannel, rightChannel);
            diff = calculateAvgDiff(leftChannel, rightChannel);
        }
        // Jeśli sygnał mono to pomiń obliczanie sumy i różnicy.
        else {
            sum = null ;
            diff = null ;
        }



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

    public int getBitResolution() {
        return bit ;
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
            case CH_SUM:
                if (ch == 2)
                    return sum ;
                else
                    System.out.println("Nagranie posiada tylko 1 kanał.") ;
                return new float [] {} ;
            case CH_DIFF:
                if (ch == 2)
                    return diff ;
                else
                    System.out.println("Nagranie posiada tylko 1 kanał.") ;
                return new float [] {} ;
            // Ten przypadek nie powinien nigdy nastąpić.
            default:    return data ;
        }
    }

}

