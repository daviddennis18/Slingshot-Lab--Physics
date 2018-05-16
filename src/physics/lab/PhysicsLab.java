/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physics.lab;

import java.util.*;

public class PhysicsLab {

    //Initialize constants and variables
    final static double mass = 0.143;
    final static double drag = 0.4;
    final static double dens = 1.23;
    final static double A = 0.00426;
    final static double g = -9.8;
    final static double k = 92.2;
    static double ysp = 0;
    static double ypos = 1.00;
    static double yA = 0;
    static double xsp = 0;
    static double xpos = 0;
    static double xA = 0;
    static double ext;
    static double iterations = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the extension: ");//Get the extension you are using
        ext = Double.parseDouble(scanner.nextLine());
        ysp = (Math.sqrt((k * ext * ext) / mass)) * Math.sin(Math.PI / 4);//calculate the intial y speed
        System.out.println("Initial ysp is :" + round(ysp));
        xsp = (Math.sqrt((k * ext * ext) / mass)) * Math.cos(Math.PI / 4);//calculate the intial x speed
        System.out.println("Initial xsp is :" + round(xsp));

        //This while loop runs until the y position is 0. 
        //As it starts at 1m above ground this will only happen once
        //it also counts the iterations
        while (ypos > 0) {
            changeYA(ysp);//changes the acceleraton for the iteration
            changeYSp(yA);//based n that acceleration, firgures out the next speed
            checkYPos();//calculates the new position
            iterations++;
        }
        System.out.println("ysp:" + round(ysp) + " yA: " + round(yA) + " Ypos: " + round(ypos) + " # " + iterations);

        //Tis loop accept the number of iteration from the loop above, and runs the simulation of the x positions
        for (int i = 0; i < iterations; i++) {
            changeXA(xsp);//changes the acceleraton for the iteration
            changeXSp(xA);//based n that acceleration, firgures out the next speed
            checkXPos();//calculates the new position
        }
        System.out.println("xsp: " + round(xsp) + " xA: " + round(xA) + " Xpos " + round(xpos) + " time: " + iterations / 10000 + "seconds");

    }

    //Calculates the new Y acceleration for the instance
    public static void changeYA(double ySp) {
        double Fnet;
        Fnet = (g * mass) - (-0.5 * drag * dens * A * (ySp * ySp));
        yA = Fnet / mass;
    }

    //Calculates the new X acceleration for the instance
    public static void changeXA(double xSp) {
        double Fnet;
        Fnet = (-0.5 * drag * dens * A * (xSp * xSp));
        xA = Fnet / mass;
    }

    //Calculates the new Y Speed for the instance
    public static void changeYSp(double aY) {
        ysp = ysp + aY * (0.0001);
    }

    //Calculates the new X Speed for the instance
    public static void changeXSp(double aX) {
        ysp = ysp + aX * (0.0001);
    }

    //Calculates the new Y position for the instance
    public static void checkYPos() {
        ypos = ypos + ysp * 0.0001 + 0.5 * yA * 0.0001 * 0.0001;
    }

    //Calculates the new X position for the instance
    public static void checkXPos() {
        xpos = xpos + xsp * 0.0001 + 0.5 * xA * 0.0001 * 0.0001;
    }

    //rounding method for clarity of output
    public static double round(double k) {
        k = (double) Math.round(k * 100) / 100;
        return k;
    }
}
