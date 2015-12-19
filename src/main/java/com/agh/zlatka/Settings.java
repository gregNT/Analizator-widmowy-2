package com.agh.zlatka;

/**
 * Klasa przechowująca ustawienia programu. Przed obliczeniem STFT
 * będą one odczytywane. Na ich podstawie nastąpi konfiguracja obiektu
 * typu FourierTransform z klasy STFT.
 * Created by Grzesiek on 2015-12-12.
 */
public class Settings {

    // Typy wyliczeniowe.
    public enum wName {RECTANGULAR, BARTLETT, WELCH, HANN, HAMMING, KAISER, GAUSSIAN}
    public enum overlapVal {NONE, HALF}

    // Zmienne.
    private Signal.chName currentChannel ;
    private wName windowName ;
    private int windowLength ;
    private overlapVal overlap ;

    // - - - Metody - - -

    // Konstruktor domyślny - posłuży do zainicjalizowania domyślnych ustawień programu.
    public Settings() {
        currentChannel = Signal.chName.CH_LEFT ;
        windowName = wName.RECTANGULAR ;
        windowLength = 512 ;
        overlap = overlapVal.NONE ;
    }

    // - - - Gettery - - -
    public Signal.chName getCurrentChannel() { return currentChannel ; }

    public wName getWindowName() { return windowName ; }

    public int getWindowLength() {return windowLength ; }

    public overlapVal getOverlap() { return overlap ; }

    // - - - Settery - - -
    public void setCurrentChannel(Signal.chName ch) { currentChannel = ch ; }

    public void setWindowName(wName wname) { windowName = wname ; }

    public void setWindowLength(int wlen) {windowLength = wlen ; }

    public void setOverlap(overlapVal ov) { overlap = ov ; }
}
