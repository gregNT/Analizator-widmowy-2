package com.agh.zlatka;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Created by Zbigu on 2015-12-23.
 */
public class DrawWaveform {

    private
    JPanel jPanel2;
    List<Float> punkty = new ArrayList<Float>();
    int x, y, sizeX, sizeY;



    public DrawWaveform(float [] data, JPanel parent, int X, int Y, int SizeX, int SizeY)
    {
        x=X; y=Y; sizeX=SizeX; sizeY=SizeY;

        jPanel2 = new Panel2();
        parent.add(jPanel2);
        jPanel2.setBackground(Color.yellow);
        jPanel2.setLayout(null);

        int skala= 2*data.length/sizeX;

        for (int i=0; i<data.length/skala; i++){

            float max=0;
            float min=0;

            for(int k = i*skala; k < (i+1)*skala; k++) {
                if(data[k] > max) {
                    max = data[k];
                }
                if(data[k] < min) {
                    min = data[k];
                }

            }


            punkty.add(-(sizeY/2)*max + sizeY/2);
            punkty.add(-(sizeY/2)*min + sizeY/2);
        }



    }

    class Panel2 extends JPanel {


        Panel2() {
            // set a preferred size for the custom panel.
            setSize(sizeX, sizeY);
            setLocation(x,y);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (int i=0; i<punkty.size()-1; i++){

                g.drawLine(i, punkty.get(i).intValue(), i+1, punkty.get(i+1).intValue());
            }
        }
    }

}
