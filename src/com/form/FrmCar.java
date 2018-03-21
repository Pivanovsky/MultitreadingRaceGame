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
    MySecundomer[] mySec = new MySecundomer[3];

    public FrmCar() {
        initialize();
    }

    private void initialize() {

        for (int i = 0; i < mySec.length; i++) {
            mySec[i] = new MySecundomer();
        }

        pnlMain.setBounds(50, 50, 1000, 500);
        pnlMain.setLayout(null);

        btnStart.setBounds(0, 0, 50, 50);
        lbl.setBounds(100, 0, 150, 50);

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
                for (int i = 0; i < mySec.length; i++) {
                    mySec[i].restart();
                    JLabel lbSec = lblCar1;
                    switch (i) {
                        case 0:
                            lbSec = lblCar1;
                            break;
                        case 1:
                            lbSec = lblCar1;
                            break;
                        case 2:
                            lbSec = lblCar1;
                            break;
                    }

                    runSec(mySec[i], lbSec);
                }
                runCar(btn1);
                runCar(btn2);
                runCar(btn3);
            }
        });


        pnlMain.add(btnStart);
        pnlMain.add(lbl);
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

        lblCar1.setBounds(10, 330, 50, 20);
        lblCar2.setBounds(10, 360, 50, 20);
        lblCar3.setBounds(10, 390, 50, 20);

    }

    private int getMilSec() {
        return (int) (Math.random() * 1000);
    }

    private void runCar(JButton btn) {
        Thread car = new Thread() {
            @Override
            public void run() {
                while ((btn.getX() + 50) <= btn.getParent().getWidth()) {
                    try {
                        Thread.sleep(getMilSec());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int x = btn.getX() + 10;
                    btn.setBounds(x, btn.getY(), btn.getWidth(), btn.getHeight());
                }
                if (!winner) {
                    lbl.setText(btn.getText() + " is Winner!!!");
                    winner = true;
                }
            }
        };
        car.start();

    }

    private void runSec(MySecundomer msec, JLabel lbl_sec) {
        Thread secund = new Thread() {
            @Override
            public void run() {
                while (msec.isStopSec()) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    msec.increaseMiliSec(5);
                    lbl_sec.setText(msec.toString());
                }

            }
        };
        secund.start();

    }
}
