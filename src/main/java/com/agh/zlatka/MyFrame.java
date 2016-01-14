package com.agh.zlatka;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.text.NumberFormat;

public class MyFrame extends JFrame {

    private
    JMenuBar menuBar;
    JMenu menu1, menu2;
    JMenuItem menuItem1, menuItem2, menuItem3;
    JPanel panel;
    JLabel label1, label2,  label4, label5, label6, label7, label8, label9, label10, label11;
    JComboBox kanal, okno, rozmiar, zakladka;
    JFileChooser fc;

    Signal waveform;
    DrawWaveform wavL;
    DrawWaveform wavR;
    DrawFFT drawFFT;

    FFT  fft;

    float [] chLeft;
    float [] chRight;


    public
    String nazwa_pliku;
    int channels;
    String path;
    int fs;
    double dt;
    double df;


    public MyFrame() {



        //menus
        menuBar = new JMenuBar();
        menu1 = new JMenu("Plik");
        menu2 = new JMenu("Pomoc");
        menuBar.add(menu1);
        menuBar.add(menu2);

        //menu items
        menuItem1 = new JMenuItem("Wczytaj");
        menuItem2 = new JMenuItem("Wyjdz");
        menuItem3 = new JMenuItem("Info");
        menuItem2.setToolTipText("Exit application?");
        menuItem1.addActionListener(new chooseFile());
        menuItem2.addActionListener(new exitApp());
        menuItem3.addActionListener(new Info());


        menu1.add(menuItem1);
        menu1.add(menuItem2);
        menu2.add(menuItem3);

        setJMenuBar(menuBar);

        //panele

        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.cyan);
        panel.setLayout(null);
        (this).add(panel);

        // labels
        label1 = new JLabel("Parametry",JLabel.CENTER);
        label1.setBounds(600, 0, 194, 50);
        label1.setBorder(BorderFactory.createLineBorder(Color.black));
        Font myFont = new Font("Arial", Font.BOLD, 18);
        label1.setFont(myFont);
        panel.add(label1);

        label2 = new JLabel("Plik:  " + nazwa_pliku,JLabel.LEFT);
        label2.setBounds(610, 60, 180, 30);
        myFont = new Font("Arial", Font.BOLD, 12);
        label2.setFont(myFont);
        panel.add(label2);


        label4 = new JLabel("l. kanałów: " + channels,JLabel.LEFT);
        label4.setBounds(610, 90, 90, 30);
        label4.setFont(myFont);
        panel.add(label4);

        String fs_str = "fs= "+ fs + " Hz";
        label5 = new JLabel(fs_str ,JLabel.LEFT);
        label5.setBounds(705, 90, 90, 30);
        label5.setFont(myFont);
        panel.add(label5);

        label6 = new JLabel("Kanał:",JLabel.LEFT);
        label6.setBounds(610, 170, 70, 30);
        label6.setFont(myFont);
        panel.add(label6);

        label7 = new JLabel("Okno:",JLabel.LEFT);
        label7.setBounds(610, 260, 70, 30);
        label7.setFont(myFont);
        panel.add(label7);

        label8 = new JLabel("Rozmiar:",JLabel.LEFT);
        label8.setBounds(610, 350, 70, 30);
        label8.setFont(myFont);
        panel.add(label8);

        label9 = new JLabel("Zakładka:",JLabel.LEFT);
        label9.setBounds(610, 440, 70, 30);
        label9.setFont(myFont);
        panel.add(label9);

        label10 = new JLabel("dt: " + dt + " ms",JLabel.LEFT);
        label10.setBounds(610, 120, 95, 30);
        label10.setFont(myFont);
        panel.add(label10);

        label11 = new JLabel("df: " + df + " Hz",JLabel.LEFT);
        label11.setBounds(705, 120, 95, 30);
        label11.setFont(myFont);
        panel.add(label11);


        // comboboxy

        String[] kanalStrings = { "L", "R", "(L+R)/2", "(L-R)/2" };
        kanal = new JComboBox(kanalStrings);
        kanal.setSelectedIndex(2);
        kanal.setBounds(680, 170, 100, 30);
        panel.add(kanal);
        kanal.addActionListener(new UpdateSettings());

        String[] oknoStrings = { "RECTANGULAR", "BARTLETT", "WELCH", "HANN", "HAMMING", "KAISER", "GAUSSIAN" };
        okno = new JComboBox(oknoStrings);
        okno.setSelectedIndex(4);
        okno.setBounds(680, 260, 100, 30);
        panel.add(okno);

        String[] rozmiarStrings = { "128", "256", "512", "1024", "2048", "4096" };
        rozmiar = new JComboBox(rozmiarStrings);
        rozmiar.setSelectedIndex(2);
        rozmiar.setBounds(680, 350, 100, 30);
        panel.add(rozmiar);

        String[] zakladkaStrings = { "0 %", "25 %", "50 %", "75 %" };
        zakladka = new JComboBox(zakladkaStrings);
        zakladka.setSelectedIndex(2);
        zakladka.setBounds(680, 440, 100, 30);
        panel.add(zakladka);

        //file chooser

        fc = new JFileChooser();
        fc.changeToParentDirectory();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV files", "wav");
        fc.setFileFilter(filter);

        //separatory

        JSeparator sep= new JSeparator(JSeparator.VERTICAL);
        panel.add(sep);
        sep.setBounds(600,0,100,600);


        //labels

        Update_Labels();


        //settings od frame
        setSize(800, 600);
        setLocation(400,100);
        setResizable(false);
        setTitle("Analizator widma");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Update

    public void Update_Labels(){

        final NumberFormat nf = NumberFormat.getInstance() ;
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.setGroupingUsed(false);

        label2.setText("Plik:  " + nazwa_pliku);
        label4.setText("l. kanałów: " + channels);
        String fs_str = "fs= "+ fs + " Hz";
        label5.setText(fs_str);
        label10.setText("dt: " + nf.format(dt) + " ms");
        label11.setText("df: " + nf.format(df) + " Hz");

    }


    // Exit app
    static class exitApp implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

    // Info
    static class Info implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(null, "Analizator widmowy - Grzegorz Kolusz, Zbigniew Latka");
        }
    }

    // Choose file
    class chooseFile implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int returnVal = fc.showOpenDialog(panel);

            if (returnVal == JFileChooser.APPROVE_OPTION) {

                File file = fc.getSelectedFile();
                path = file.getAbsolutePath();

                waveform = new Signal(path);
                chLeft = waveform.getChannel(Signal.chName.CH_LEFT);
                chRight = waveform.getChannel(Signal.chName.CH_RIGHT);
                wavL = new DrawWaveform(chLeft, panel, 10, 10, 580, 100);
                wavR = new DrawWaveform(chRight, panel, 10, 120, 580, 100);

                Update_Panels(Signal.chName.CH_SUM);

                nazwa_pliku = file.getName();
                channels = waveform.getNumOfCh();
                fs = waveform.getFs();
                dt = 1000.0/fs; // w [ms]
                df = (double) fs/chLeft.length;
                Update_Labels();

            }

        }
    }

    // Updtade STFT Settings
    class UpdateSettings  implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JComboBox cb = (JComboBox)e.getSource();
            String channels = (String)cb.getSelectedItem();

            if (channels.equals("L")) {
                Update_Panels(Signal.chName.CH_LEFT);
            }
            else if (channels.equals("R")) {
                Update_Panels(Signal.chName.CH_RIGHT);
            }
            else if (channels.equals("(L+R)/2")) {
                Update_Panels(Signal.chName.CH_SUM);
            }
            else if (channels.equals("(L-R)/2")) {
                Update_Panels(Signal.chName.CH_DIFF);
            }

        }
    }

    public void Update_Panels(Signal.chName channels)
    {

        fft = new FFT(waveform, channels);
        double [] fft_magn  = fft.getMagnitude();
        
        drawFFT = new DrawFFT(fft_magn, panel, 10, 230, 580, 300);
    }



}

