/* W File->Project Structure->Modules dodać bibliotekę Dr Flanagana:
   http://www.ee.ucl.ac.uk/~mflanaga/java/flanagan.jar */
package com.agh.zlatka;

import flanagan.math.FourierTransform;

public class FFT {

    // zmienne
    private FourierTransform ft ;
    private int fs ;
    private double [] magnitude ;
    private double [] freq_vector ;

    // Konwersja tablicy float'ów na tablicę typu double. Jest to konieczne, bo biblioteka jMusic
    // operuje na typie float, a Flanangan operuje na double. Nie wymyśliłem lepszego sposobu.
    public static double[] convertFloatsToDoubles(float[] input) {
        if (input == null) {
            return null;
        }
        double[] output = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = input[i];
        }
        return output;
    }

    // Konstruktor podstawowy - przyjmuje wektor z danymi do transformacji.
    public FFT(float[] signal, int sampling_freq) {

        ft = new FourierTransform(convertFloatsToDoubles(signal)) ;
        fs = sampling_freq ;
        ft.setDeltaT(1.0 / fs);
        compute();
    }
    // Konstruktor podstawowy - przyjmuje wektor z danymi do transformacji.
    public FFT(Signal sig, Signal.chName channelName) {

        ft = new FourierTransform(convertFloatsToDoubles(sig.getChannel(channelName))) ;
        fs = sig.getFs() ;
        ft.setDeltaT(1.0 / fs);
        compute();
    }

    // Oblicza FFT
    public void compute() {

        int n = ft.getUsedDataLength() ;
        magnitude = new double[n] ;
        freq_vector = new double[n] ;

        // Obliczenie FFT.
        ft.transform();

        double[] alternateFFTResult = ft.getTransformedDataAsAlternate() ;

        for (int i = 0; i < n; i++) {
            magnitude[i] =20* Math.log10( Math.sqrt(Math.pow(alternateFFTResult[2 * i], 2) + Math.pow(alternateFFTResult[2 * i + 1], 2))) ;
            freq_vector[i] = (double) i / (n*ft.getDeltaT()) ;
        }
    }

    public double [] getMagnitude() {
        return magnitude ;
    }

    public double [] getFreqVector() {
        return freq_vector ;
    }

    public void printMagnitude() {
        for (double x : magnitude)
            System.out.print(x + " ") ;
        System.out.println() ;
    }

}









