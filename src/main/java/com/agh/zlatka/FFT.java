/* W File->Project Structure->Modules dodać bibliotekę Dr Flanagana:
   http://www.ee.ucl.ac.uk/~mflanaga/java/flanagan.jar */
package com.agh.zlatka;

import flanagan.math.FourierTransform;

public class FFT {

    // zmienne
    private FourierTransform ft ;
    private double [] magnitude ;

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
    // Tu trzeba dopisać obsługę wyboru kanału: L, R, L+R/2 i L-R/2.
    public FFT(float[] signal) {

        ft = new FourierTransform(convertFloatsToDoubles(signal)) ;
    }

    // Oblicza FFT
    public void compute() {

        int n = ft.getUsedDataLength() ;
        magnitude = new double[n] ;

        // Obliczenie FFT.
        ft.transform();

        double[] alternateFFTResult = ft.getTransformedDataAsAlternate();

        for (int i = 0; i < (n); i++)
            magnitude[i] =  Math.sqrt(Math.pow(alternateFFTResult[2 * i], 2)  + Math.pow(alternateFFTResult[2 * i + 1], 2)) ;
    }

    public double [] getMagnitude() {
        return magnitude ;
    }

    public void printMagnitude() {
        for (double x : magnitude)
            System.out.print(x + " ") ;
        System.out.println() ;
    }

}









