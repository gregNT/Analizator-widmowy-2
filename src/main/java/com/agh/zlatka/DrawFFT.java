package com.agh.zlatka;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

/**
 * Created by Zbigu on 2015-12-23.
 */
public class DrawFFT {

    private
    JPanel jPanel2;
    List<Double> punkty = new ArrayList<Double>();
    List<Double> punkty2 = new ArrayList<Double>();
    int x, y, sizeX, sizeY;



    public DrawFFT(double [] data2, JPanel parent, int X, int Y, int SizeX, int SizeY)
    {
        double [] data = Arrays.copyOfRange(data2, 0, data2.length/2);
        x=X; y=Y; sizeX=SizeX; sizeY=SizeY;

        jPanel2 = new Panel2();
        parent.add(jPanel2);
        jPanel2.setBackground(Color.black);
        jPanel2.setLayout(null);

        int skala= data.length/sizeX;
        double max_magn=0;

        for (int i=0; i<data.length/skala; i++){

            double max=0;


            for(int k = i*skala; k < (i+1)*skala; k++) {
                if(data[k] > max) {
                    max = data[k];
                }

                if(max>max_magn){
                    max_magn=max;
                }
            }

            punkty2.add(max);
        }



        for (int i=0; i<data.length/skala; i++){


            punkty.add(sizeY-sizeY*punkty2.get(i)/max_magn);

        }



    }

    class Panel2 extends JPanel {


        Panel2() {

            setSize(sizeX, sizeY);
            setLocation(x,y);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (int i=0; i<punkty.size(); i++){

                g.drawLine(i, sizeY-1, i, punkty.get(i).intValue());
                g.setColor(Color.red);
            }
        }
    }

}
