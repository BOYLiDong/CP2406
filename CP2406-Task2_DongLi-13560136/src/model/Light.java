package model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Light {

    // 0: red light, 1: green light
    // Initial setting l2, l3 are green lights; l1, l4 are red lights
    // Initial setting l6, l7 are green lights; l5, l8 are red lights

    private int l1 = 0;
    private int l2 = 1;
    private int l5;
    private int l6;
    private int redGap;
    private int greenGap;
    private int delayGap;
    private int gap;
    private int specialGap;

    // Set timer
    public Light() {

        // Get the relevant parameters of the window for setting parameters
        redGap = Integer.parseInt(Setting.redText.getText());
        greenGap = Integer.parseInt(Setting.greenText.getText());
        delayGap = Integer.parseInt(Setting.delayText.getText());
        gap = redGap + greenGap;
        //Determine how the traffic lights at the initial moment of the CR2 intersection should be set.
        if (redGap > delayGap) {
            l5 = 1;
            l6 = 0;
            specialGap = delayGap + greenGap;
        } else {
            l5 = 0;
            l6 = 1;
            specialGap = delayGap - redGap;
        }

        setLightTimer();

    }

    public void setLightTimer() {

        Timer t1 = new Timer();
        
        t1.schedule(new light1(), greenGap * 1000, gap * 1000);

        Timer t2 = new Timer();
   
        t2.schedule(new light2(), gap * 1000, gap * 1000);

        Timer t3 = new Timer();

        t3.schedule(new light3(), specialGap * 1000, gap * 1000);

        Timer t4 = new Timer();

        t4.schedule(new light4(), delayGap * 1000, gap * 1000);
    }

    //Control CR1 junction
    private class light1 extends TimerTask {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (l1 == 0) {
                l1 = 1;
                l2 = 0;
            }
        }
    }
    //控制路口
    private class light2 extends TimerTask {
        @Override
        public void run() {
            if (l1 == 1) {
                l1 = 0;
                l2 = 1;
            }
        }
    }
    //Control junction
    private class light3 extends TimerTask {
        @Override
        public void run() {
            if (l5 == 0) {
                l6 = 0;
                l5 = 1;

            }
        }
    }
    //Control junction
    private class light4 extends TimerTask {
        @Override
        public void run() {
            if (l5 == 1) {
                l6 = 1;
                l5 = 0;
            }
        }
    }

    public int getl1() {
        return l1;
    }

    public int getl2() {
        return l2;
    }

    public int getl5() {
        return l5;
    }

    public int getl6() {
        return l6;
    }

}