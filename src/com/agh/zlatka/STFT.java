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

// Parametry do obliczenia STFT: ch, window, noverlap, nfft, fs
public class STFT {

    // Konwersja tablicy float'ów na tablicę typu double. Jest to konieczne, bo biblioteka jMusic
    // operuje na typie float, a Flanangan operuje na double. Na razie nie wymyśliłem lepszego sposobu.
    public static double[] convertFloatsToDoubles(float[] input)
    {
        if (input == null)
        {
            return null;
        }
        double [] output = new double[input.length] ;
        for (int i = 0; i < input.length; i++)
        {
            output[i] = input[i] ;
        }
        return output ;
    }

    // Konstruktor podstawowy - przyjmuje wektor z danymi do transformacji.

    // Zrobić przekazywanie całego obiektu Signal.
    STFT (float [] data) {
        FourierTransform ft = new FourierTransform(convertFloatsToDoubles(data)) ;
    }

    // STFT
    // setWindowLength(int wlen) ;
    //      int wlen = 512 ;
    // setWindowType(...) ;
    // setOverlap(albo 0 albo 50%) ;
    // calculate() ;
    //      double [][] STFTMatrix = ft.shortTime(wlen) ;




}








/* - początek kodu napisany przez Zbyszka
public class SFFT {

    private

    float N; //dlugosc okna
    float overlap; //zakladka w procentach
    double K; // ilosc okien ktore zmieszcza sie w sygnale
    float[] s; //sygnal


    public SFFT (float[] y, int NFFT, float Overlap) {

        s= y;
        N= NFFT;
        overlap= Overlap/100;

        K=Math.floor((y.length-N)/(N*(1-overlap))+1);



        for (int i=1; i<K+1; i++){

            FT = fft(y[N(i-1) : N*i-1]*okno);
            SFFT = [SFFT ; FT]
        }

    }
*/
