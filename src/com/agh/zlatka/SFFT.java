package com.agh.zlatka;


public class SFFT {

    private

    int N; //dlugosc okna
    float overlap; //zakladka w procentach
    int K; // ilosc okien ktore zmieszcza sie w sygnale
    float[] s; //sygnal


    public SFFT (float[] y, int NFFT, float Overlap) {

        s= y;
        N= NFFT;
        overlap= Overlap;



    }
}
