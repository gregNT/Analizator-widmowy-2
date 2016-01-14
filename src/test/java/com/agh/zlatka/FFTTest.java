package com.agh.zlatka;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FFTTest {

    private Signal waveform ;
    private FFT waveform_transformed ;

    @Before
    public void setUp() throws Exception {
        waveform = null ;
        waveform_transformed = null ;
    }

    /*
    @Test
    // Test 5 pierwszych obliczonych wartości widma amplitudowego w sytuacji, gdy długość danych to potęga liczby 2.

    public void testCompute() throws Exception {

        waveform = new Signal("sound/STFT_stereo_2.wav") ;


        waveform_transformed = new FFT(waveform, Signal.chName.CH_LEFT) ;
        waveform_transformed.compute() ;

        double [] expectedData = {
                0.151458740234375,
                0.0258898261281961,
                0.0132835427629758,
                0.00858665259681398,
                0.00716176219863384

        } ;
        double [] magnitude = waveform_transformed.getMagnitude() ;
        double [] resultData = {
                20.0*Math.log10(magnitude[0]),
                20.0*Math.log10(magnitude[1]),
                20.0*Math.log10(magnitude[2]),
                20.0*Math.log10(magnitude[3]),
                20.0*Math.log10(magnitude[4])
        } ;

        assertArrayEquals(expectedData, resultData, 0.0001) ;
    }
    */

    @Test
    // Test 5 pierwszych obliczonych wartości widma amplitudowego w sytuacji, gdy długość danych nie jest jedną z potęg
    // liczby 2.
    public void testCompute() throws Exception {

        waveform = new Signal("sound/stereo_2.wav") ;
        //float [] leftChannel = waveform.getChannel(Signal.chName.CH_LEFT) ;

        //waveform_transformed = new FFT(leftChannel, waveform.getFs()) ;

        waveform_transformed = new FFT(waveform, Signal.chName.CH_LEFT) ;

        waveform_transformed.compute() ;

        double [] magnitude = waveform_transformed.getMagnitude() ;
        double [] resultData = {
                20.0*Math.log10(magnitude[0]),
                20.0*Math.log10(magnitude[1]),
                20.0*Math.log10(magnitude[2]),
                20.0*Math.log10(magnitude[3]),
                20.0*Math.log10(magnitude[4])
        } ;
        //for (int i = 0; i < resultData.length; i++)
           // System.out.println(resultData[i]) ;
        for (double x: resultData)
            System.out.println(x) ;
    }
}