
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package napakalaki;

import java.util.Random;

import javax.swing.Timer;

/**
 *
 * @author minelli
 */
public class Dice {
    private static final Dice instance = new Dice();
    private static int        number;
    private Random            generator = new Random();
    private Timer             timerDice;
    private int               value;

    private Dice() {}

    public int nextNumber() {
        return (generator.nextInt(6) + 1);
    }

    public static Dice getInstance() {
        return instance;
    }
}
