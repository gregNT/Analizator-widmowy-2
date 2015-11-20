package com.agh.zlatka;


public class Main {


    public static float[] y_r, y_l;



    public static void main(String args[]) {

        //wczytanie wava

        WaveReader wr = new WaveReader("test.wav");

        float[] s = wr.get_data();

        int ch = wr.get_ch();


        //podzielenie sygnalu na prawy i lewy kanal
        y_r= new float[(s.length)/2];
        y_l= new float[(s.length)/2];

        for (int i=0; i<(s.length); i=i+ch) {

            y_r[i/2] = s[i];
            y_l[i/2] = s[i+1];
        }


        //SFFT







        wr.draw_wave();
        wr.print_info();


    }

}
