package com.agh.zlatka;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class STFTTest {

    private Signal waveform ;
    private STFT waveform_transformed ;

    @Before
    public void setUp() throws Exception {

        waveform = new Signal("sound/STFT_stereo_2.wav") ;
        float [] leftChannel = waveform.getChannel(Signal.chName.CH_LEFT) ;

        waveform_transformed = new STFT(leftChannel) ;
    }

    @Test
    public void testCompute() throws Exception {

        Settings s = new Settings() ;



        waveform_transformed.compute(s, waveform.getFs()) ;
        //waveform_transformed.printSTFTMatrix("STFTtest.txt");


        double [][] STFTMatrix = waveform_transformed.getSTFTMatrix() ;
        waveform_transformed.plotSTFTMatrix("title");
        int row = STFTMatrix.length ;
        int col = STFTMatrix[0].length ;
        System.out.println("rows:" + row + ",cols:" + col) ;



        /*

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                System.out.print(STFTMatrix[i][j] + " ");
            System.out.println() ;
        */


    }
}