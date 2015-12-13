// Pomysł - nie trzeba się bawić z liczbami urojonymi, bo wystarczy
// przechowywać tablicę double [] [] z KWADRATAMI amplitud.
// Najprościej będzie wykorzystać klasę dr Flanagana - są tam gotowe funkcje do obliczenia
// macierzy STFT (http://www.ee.ucl.ac.uk/~mflanaga/java/FourierTransform.html#class)

package com.agh.zlatka;

/* W File->Project Structure->Modules dodać bibliotekę Dr Flanagana:
   http://www.ee.ucl.ac.uk/~mflanaga/java/flanagan.jar */
import flanagan.io.*;
import flanagan.math.*;
import flanagan.plot.*;

// Parametry do obliczenia STFT: ch, window, noverlap, nfft, fs.
public class STFT {

    // zmienne
    private FourierTransform ft;
    private double[][] STFTMatrix;

    // Konwersja tablicy float'ów na tablicę typu double. Jest to konieczne, bo biblioteka jMusic
    // operuje na typie float, a Flanangan operuje na double. Na razie nie wymyśliłem lepszego sposobu.
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
    public STFT(float[] data) {

        ft = new FourierTransform(convertFloatsToDoubles(data)) ;
    }

    // Oblicza STFT z ustawieniami z klasy Settings.
    public void compute(Settings s, int fs) {

        // Ustawienie kroku czasowego.
        ft.setDeltaT(1 / (double) fs);

        // Ustawienie okna.
        switch (s.getWindowName()) {
            case RECTANGULAR:
                ft.setRectangular();
                break;
            case BARTLETT:
                ft.setBartlett();
                break;
            case WELCH:
                ft.setWelch();
                break;
            case HANN:
                ft.setHann();
                break;
            case HAMMING:
                ft.setHamming();
                break;
            case KAISER:
                ft.setKaiser();
                break;
            case GAUSSIAN:
                ft.setGaussian();
                break;
        }

        // Nie wiem, czy ustawienie overlapa zadziała również do STFT, bo w dokumentacji
        // klasy Flanagana jest tylko mowa o użyciu tego przy Power Spectrum.
        switch (s.getOverlap()) {
            case NONE:
                ft.setOverlapOption(false);
                break;
            case HALF:
                ft.setOverlapOption(true);
                break;
        }

        // Ustawienie długości okna.
        ft.setSegmentLength(s.getWindowLength());

        // Wyznaczenie macierzy STFT.
        STFTMatrix = ft.shortTime(s.getWindowLength());
    }

    public double[][] getSTFTMatrix() {
        return STFTMatrix ;
    }
}









