package com.agh.zlatka;



import jm.gui.wave.WaveFileReader;
import jm.gui.wave.WaveView;
import jm.util.* ;

public class WaveReader {

    private

    int fs, bit, ch;
    float[] data;
    String path;
    WaveFileReader wv;

    public WaveReader (String wave_path) {

        path = wave_path;
        data = Read.audio(path);

        wv = new WaveFileReader(path);
        fs = wv.getSampleRate();
        bit = wv.getBitResolution();
        ch = wv.getChannels();

    }

    public void print_info() {

        // To wyświetla wyniki w konsoli.
        System.out.printf("Sample rate: %d, Bit depth: %d%n", fs, bit);
    }

    public void draw_wave() {

        // Ta funkcja wyświetla "mini-GUI" z oscylogramem.
        WaveView wview = new WaveView(path);
    }

    public int get_fs(){
        return fs;
    }

    public int get_ch(){
        return ch;
    }

    public float[] get_data(){
        return data;
    }

    public String get_path(){
        return path;
    }

}

