package com.agh.zlatka;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Grzesiek on 2015-12-19.
 * Signal: klasa opakowująca próbki sygnału wave w różne przydatne funkcje.
 */
public class SignalTest {

    private Signal waveform ;

    @Before
    public void setUp() throws Exception {
        waveform = new Signal("sound/test_stereo_2.wav") ;
    }

    @Test
    public void testWavParams() throws Exception {

        int expectedFs = 48000 ;
        int expectedBit = 16 ;
        int expectedCh = 2 ;
        String expectedPath = "sound/test_stereo_2.wav" ;

        int resultFs = waveform.getFs() ;
        int resultBit = waveform.getBitResolution() ;
        int resultCh = waveform.getNumOfCh() ;
        String resultPath = waveform.getPath() ;

        assertEquals(expectedFs, resultFs) ;
        assertEquals(expectedBit, resultBit) ;
        assertEquals(expectedCh, resultCh) ;
        assertEquals(expectedPath, resultPath) ;
    }

    @Test
    public void testInterleavedWavValues() throws Exception {
        float [] expectedData = {
                0.000518798828125f,
                0.016204833984375f,
                0.000885009765625f,
                0.013519287109375f,
                0.000701904296875f,
                0.01513671875f,
                0.000885009765625f,
                0.013885498046875f,
                0.000701904296875f,
                0.010498046875f,
                0.000885009765625f,
                0.011016845703125f,
                0.000518798828125f,
                0.013702392578125f,
                0.000885009765625f,
                0.017791748046875f,
                0.000701904296875f,
                0.01654052734375f,
                0.000701904296875f,
                0.015655517578125f
        } ;
        float [] resultData = waveform.getData() ;
        // Jak próbuję zmniejszyć deltę o 1 rząd to już liczby się nie zgadzają - dlaczego?
        // (wczytałem sygnał do MATLAB'a poleceniem wavread, otworzyłem zmienną y, skopiowałem
        // 10 pierwszych wartości obu kanałów i wkleiłem do Excela poprzez ctrl+v, które
        // zamieściłem w tym kodzie).
        assertArrayEquals(expectedData, resultData, 0.000001f) ;
    }

    @Test
    public void testChannelValues() throws Exception {
        float [] expectedLeftCh = {
                0.000518798828125f,
                0.000885009765625f,
                0.000701904296875f,
                0.000885009765625f,
                0.000701904296875f,
                0.000885009765625f,
                0.000518798828125f,
                0.000885009765625f,
                0.000701904296875f,
                0.000701904296875f,
        } ;

        float [] expectedRightCh = {
                0.016204833984375f,
                0.013519287109375f,
                0.01513671875f,
                0.013885498046875f,
                0.010498046875f,
                0.011016845703125f,
                0.013702392578125f,
                0.017791748046875f,
                0.01654052734375f,
                0.015655517578125f
        } ;

        float [] resultLeftCh = waveform.getChannel(Signal.chName.CH_LEFT) ;
        float [] resultRightCh = waveform.getChannel(Signal.chName.CH_RIGHT) ;

        // Jak próbuję zmniejszyć deltę o 1 rząd to już liczby się nie zgadzają - dlaczego?
        // (wczytałem sygnał do MATLAB'a poleceniem wavread, otworzyłem zmienną y, skopiowałem
        // 10 pierwszych wartości obu kanałów i wkleiłem do Excela poprzez ctrl+v, które
        // zamieściłem w tym kodzie).
        assertArrayEquals(expectedLeftCh, resultLeftCh, 0.000001f);
        assertArrayEquals(expectedRightCh, resultRightCh, 0.000001f);
    }

    @Test
    public void testSumAndDiffValues() throws Exception {
        float [] expectedSum = {
                0.00836181640625f,
                0.0072021484375f,
                0.0079193115234375f,
                0.00738525390625f,
                0.0055999755859375f,
                0.005950927734375f,
                0.007110595703125f,
                0.00933837890625f,
                0.0086212158203125f,
                0.0081787109375f,
        } ;

        float [] resultSum = waveform.getChannel(Signal.chName.CH_SUM) ;
        assertArrayEquals(expectedSum, resultSum, 0.000001f);

        float [] expectedDiff = {
                -0.007843017578125f,
                -0.006317138671875f,
                -0.0072174072265625f,
                -0.006500244140625f,
                -0.0048980712890625f,
                -0.00506591796875f,
                -0.006591796875f,
                -0.008453369140625f,
                -0.0079193115234375f,
                -0.007476806640625f,
        } ;

        float [] resultDiff = waveform.getChannel(Signal.chName.CH_DIFF) ;
        assertArrayEquals(expectedDiff, resultDiff, 0.000001f);

    }
}

