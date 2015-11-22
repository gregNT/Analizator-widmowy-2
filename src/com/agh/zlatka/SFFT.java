package com.agh.zlatka;


// Pomysł - nie trzeba się bawić z liczbami urojonymi, bo wystarczy
// przechowywać tablicę double [] [] z KWADRATAMI amplitud.
// Najprościej będzie wykorzystać klasę dr Flanagana - są tam gotowe funkcje do obliczenia
// macierzy STFT (http://www.ee.ucl.ac.uk/~mflanaga/java/FourierTransform.html#class)
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

        /*

        for (int i=1; i<K+1; i++){

            // FT = fft(y[N(i-1) : N*i-1]*okno);
            // SFFT = [SFFT ; FT]
        }
        */

    }
}
