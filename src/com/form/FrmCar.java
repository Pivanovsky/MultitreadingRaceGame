package com.form;

import com.classes.MySecundomer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmCar {
    JFrame frm = new JFrame();
    JButton btnStart = new JButton("START");
    JButton btn1 = new JButton("1");
    JButton btn2 = new JButton("2");
    JButton btn3 = new JButton("3");
    JPanel pnlMain = new JPanel();
    Boolean winner = false;
    JLabel lbl = new JLabel();
    JLabel lblCar1 = new JLabel("Sec1");
    JLabel lblCar2 = new JLabel("Sec2");
    JLabel lblCar3 = new JLabel("Sec3");

    JRadioButton radio1=new JRadioButton("Sport car");
    JRadioButton radio2=new JRadioButton("Standart car");
    JRadioButton radio3=new JRadioButton("Track");
    ButtonGroup groupTypeofCar=new ButtonGroup();
    MySecundomer[] mySec = new MySecundomer[3];

    public FrmCar() {
        initialize();
    }

    private void initialize() {

        groupTypeofCar.add(radio1);
        groupTypeofCar.add(radio2);
        groupTypeofCar.add(radio3);
        radio1.setSelected(true);
        radio1.setBounds(70,10,100,20);
        radio2.setBounds(170,10,100,20);
        radio3.setBounds(270,10,100,20);


        for (int i = 0; i < mySec.length; i++) {
            mySec[i] = new MySecundomer();
        }

        pnlMain.setBounds(50, 50, 1000, 500);
        pnlMain.setLayout(null);

        btnStart.setBounds(0, 0, 50, 50);
        lbl.setBounds(100, 30, 150, 50);

        btn1.setIcon(new ImageIcon("src\\com\\data\\i1.jpg"));
        btn2.setIcon(new ImageIcon("src\\com\\data\\i2.jpg"));
        btn3.setIcon(new ImageIcon("src\\com\\data\\i3.jpg"));
        startCarPosition();

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winner = false;
                lbl.setText("Who is first?");


                startCarPosition();

                runCar(btn1, lblCar1, mySec[0]);
                runCar(btn2, lblCar2, mySec[1]);
                runCar(btn3, lblCar3, mySec[2]);
            }
        });


        pnlMain.add(btnStart);
        pnlMain.add(lbl);
        pnlMain.add(radio1);
        pnlMain.add(radio2);
        pnlMain.add(radio3);
        pnlMain.add(btn1);
        pnlMain.add(btn2);
        pnlMain.add(btn3);
        pnlMain.add(lblCar1);
        pnlMain.add(lblCar2);
        pnlMain.add(lblCar3);

        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setBounds(100, 100, 1100, 700);
        frm.setContentPane(pnlMain);
        frm.setVisible(true);
    }

    private void startCarPosition() {
        btn1.setBounds(10, 60, 50, 50);
        btn2.setBounds(10, 160, 50, 50);
        btn3.setBounds(10, 260, 50, 50);

        lblCar1.setBounds(10, 330, 100, 20);
        lblCar2.setBounds(10, 360, 100, 20);
        lblCar3.setBounds(10, 390, 100, 20);

    }

    private int getMilSec() {
        int speed=1000;
        if(radio1.isSelected())
            speed=100;
        if(radio2.isSelected())
            speed=500;
        if(radio3.isSelected())
            speed=1000;

        return (int) (Math.random() * speed);
    }

    private void runCar(JButton btn, JLabel lbCar, MySecundomer mySc) {
        Thread car = new Thread() {
            @Override
            public void run() {
                while ((btn.getX() + 50) <= btn.getParent().getWidth()) {
                    try {
                        int ml = getMilSec();
                        Thread.sleep(ml);
                        mySc.increaseMiliSec(ml);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int x = btn.getX() + 10;
                    btn.setBounds(x, btn.getY(), btn.getWidth(), btn.getHeight());
                    lbCar.setText(mySc.toString());
                }
                if (!winner) {
                    lbl.setText(btn.getText() + " is Winner!!!");
                    winner = true;
                }
            }
        };
        car.start();

    }


}
