
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package napakalaki;

import GUI.NewWindowP5;

/**
 *
 * @author minelli
 */
public class NewMainp5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        ControladorP5 c = ControladorP5.getInstance();

        java.awt.EventQueue.invokeLater(new Runnable() {
                                            public void run() {
                                                new NewWindowP5().setVisible(true);
                                            }
                                        });
    }
}
