package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public class Document{

    private int oneCrossA;
    private int oneCrossB;
    private int threeCrossA;
    private int threeCrossB;
    private int snTime;
    private int ewTime;

    ArrayList<Car> CarList;
    Light t;
    static HashMap<String, Integer> prior;
    int[] One = { 2, 5 };
    int[] Three = { 1, 3, 4, 6 };
    int[] Final = { 1, 2, 3, 4, 5, 6 };

    public Document() {
        t = new Light();
        CarList = new ArrayList<Car>();
        prior = new HashMap<String, Integer>();
        snTime = Integer.parseInt(Setting.SNText.getText());
        ewTime = Integer.parseInt(Setting.EWText.getText());
        setDocumentTimer();
    }

    public void setDocumentTimer() {
        Timer timerOne = new Timer();
        Timer timerThree = new Timer();
        timerOne.schedule(new generateCarOne(), 0, ewTime * 1000);
        timerThree.schedule(new generateCarThree(), 0, snTime * 1000);
    }

    // Cars are randomly generated at east-west intersections, and any point of EHW, EHE is used as the starting point.
    public void randomOne() {
        int index;
        Random ran = new Random();
        index = ran.nextInt(One.length);
        oneCrossA = One[index];
        //Use a hashset to prevent duplicate entries and exits.
        Set<Integer> setOne = new HashSet<Integer>();
        setOne.add(One[index]);
        while (setOne.size() < 2) {
            index = ran.nextInt(Final.length);
            setOne.add(Final[index]);
        }
        for (Integer id : setOne) {
            //If the starting intersection and the ending intersection are the same, it is regenerated.
            if (id != oneCrossA)
                oneCrossB = id;
        }
        setOne.clear();
    }

    // Cars are randomly generated at the north-south intersection, any point of BSN, TBS, BS, TBS as the starting point.
    public void randomThree() {
        int index;
        Random ran = new Random();
        index = ran.nextInt(Three.length);
        threeCrossA = Three[index];
        Set<Integer> setThree = new HashSet<Integer>();
        setThree.add(Three[index]);
        while (setThree.size() < 2) {
            index = ran.nextInt(Final.length);
            setThree.add(Final[index]);
        }
        for (Integer id : setThree) {
            if (id != threeCrossA)
                threeCrossB = id;
        }
        setThree.clear();
    }
    //Cars are produced regularly at east-west intersections
    private class generateCarOne extends TimerTask {
        public void run() {
            randomOne();
            if (!judgeJam(oneCrossA)) {
                Car car = new Car(oneCrossA, oneCrossB, t);
                CarList.add(car);
            }
        }
    }
    //Cars are regularly produced at the intersection of north and south
    private class generateCarThree extends TimerTask {
        public void run() {
            randomThree();
            if (!judgeJam(threeCrossA)) {
                Car car = new Car(threeCrossA, threeCrossB, t);
                CarList.add(car);
            }

        }
    }
    //To determine whether the intersection is saturated, the method to determine the intersection saturation is: if there is a car at the beginning of the intersection at this second, stop generating, otherwise generate the car.
    public boolean judgeJam(int begin) {
        if (begin == 2) {
            String first = String.valueOf(Road.E.x)
                    + String.valueOf(Road.E.y);
            if (prior.containsKey(first)) {
                return true;
            }
        }
        if (begin == 5) {
            String first = String.valueOf(Road.a.x)
                    + String.valueOf(Road.a.y);
            if (prior.containsKey(first)) {
                return true;
            }
        }
        if (begin == 1) {
            String first = String.valueOf(Road.A.x)
                    + String.valueOf(Road.A.y);
            if (prior.containsKey(first)) {
                return true;
            }
        }
        if (begin == 6) {
            String first = String.valueOf(Road.O.x)
                    + String.valueOf(Road.O.y);
            if (prior.containsKey(first)) {
                return true;
            }
        }
        if (begin == 3) {
            String first = String.valueOf(Road.N.x)
                    + String.valueOf(Road.N.y);
            if (prior.containsKey(first)) {
                return true;
            }
        }

        if (begin == 4) {
            String first = String.valueOf(Road.Z.x)
                    + String.valueOf(Road.Z.y);
            if (prior.containsKey(first)) {
                return true;
            }
        }
        return false;
    }
}