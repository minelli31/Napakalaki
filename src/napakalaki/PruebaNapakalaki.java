
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author minelli
 */
public class PruebaNapakalaki {
    static ArrayList<Monster> monstruos      = new ArrayList();
    static ArrayList<Monster> monstersResult = new ArrayList();
    static int                tamMonsters;
    static TreasureKind       treasure;

    public static ArrayList<Monster> biggerThen(ArrayList<Monster> monstruos, int n) {
        ArrayList<Monster> monstersConsult = new ArrayList();

        tamMonsters = monstruos.size();
        System.out.println("Nivel de combate superior a " + n + ":");

        for (int i = 0; i < tamMonsters; i++) {
            if (monstruos.get(i).getLevel() > n) {
                monstersConsult.add(monstruos.get(i));
            }
        }

        return monstersConsult;
    }

    public static ArrayList<Monster> greedLevels(ArrayList<Monster> monstruos, int n) {
        ArrayList<Monster> monstersConsult = new ArrayList();

        tamMonsters = monstruos.size();
        System.out.println("Buen Rollo: ganancia de niveles superiores a " + n + ":");

        for (int i = 0; i < tamMonsters; i++) {
            if ((monstruos.get(i).getPrizeLevels() > n)) {
                monstersConsult.add(monstruos.get(i));
            }
        }

        return monstersConsult;
    }

    public static ArrayList<Monster> lossLevels(ArrayList<Monster> monstruos) {
        ArrayList<Monster> monstersConsult = new ArrayList();

        tamMonsters = monstruos.size();
        System.out.println("Mal Rollo: Sólo perdida de niveles:");

        for (int i = 0; i < tamMonsters; i++) {
            if ((monstruos.get(i).getBadLevels() != 0)
                    && (monstruos.get(i).getBadnHiddenTreasures() == 0)
                    && (monstruos.get(i).getBadnVisibleTreasures() == 0)) {
                monstersConsult.add(monstruos.get(i));
            }
        }

        return monstersConsult;
    }

    public static ArrayList<Monster> lossSpecificTreasures(ArrayList<Monster> monstruos) {
        ArrayList<Monster>      monstersConsult     = new ArrayList();
        ArrayList<TreasureKind> listSpecificVisible = new ArrayList();
        ArrayList<TreasureKind> listSpecificHidden  = new ArrayList();

        tamMonsters = monstruos.size();
        System.out.println("Mal Rollo: Pérdida de tesoros especificos visibles y invisibles:");

        for (int i = 0; i < tamMonsters; i++) {
            listSpecificVisible = monstruos.get(i).getSpecificVisibleTreasures();
            listSpecificHidden  = monstruos.get(i).getSpecificHiddenTreasures();

            if ((listSpecificHidden != null)
                    && (monstruos.get(i).getBadnHiddenTreasures() == 0)
                    && (listSpecificVisible != null)
                    && (monstruos.get(i).getBadnVisibleTreasures() == 0)
                    &&!monstruos.get(i).getDeath()) {
                System.out.println(monstruos.get(i).getName());
                System.out.println(monstruos.get(i).getBadText());
                System.out.println("Visibles => " + listSpecificVisible);
                System.out.println("Ocultos => " + listSpecificHidden);
                monstersConsult.add(monstruos.get(i));
            }
        }

        return monstersConsult;
    }

    public static ArrayList<Monster> lossSpecificTreasures(ArrayList<Monster> monstruos, TreasureKind treasure) {
        ArrayList<Monster>      monstersConsult     = new ArrayList();
        ArrayList<TreasureKind> listSpecificVisible = new ArrayList();
        ArrayList<TreasureKind> listSpecificHidden  = new ArrayList();
        int                     tamTreasureVisible;
        int                     tamTreasureHidden;
        boolean                 beVisible = false;
        boolean                 beHidden  = false;

        tamMonsters = monstruos.size();
        System.out.println("Mal Rollo: Pérdida del tesoro especifico " + treasure + " visible e invisible:");

        for (int i = 0; i < tamMonsters; i++) {
            listSpecificVisible = monstruos.get(i).getSpecificVisibleTreasures();
            listSpecificHidden  = monstruos.get(i).getSpecificHiddenTreasures();

            if (((listSpecificHidden != null) || (listSpecificVisible != null))
                    && (monstruos.get(i).getBadnHiddenTreasures() == 0)
                    && (listSpecificVisible != null)
                    && (monstruos.get(i).getBadnVisibleTreasures() == 0)
                    &&!monstruos.get(i).getDeath()) {
                tamTreasureVisible = listSpecificVisible.size();
                tamTreasureHidden  = listSpecificHidden.size();
                beVisible          = beHidden = false;

                for (int j = 0; j < tamTreasureVisible; j++) {
                    if ((listSpecificVisible.get(j) == treasure)) {
                        System.out.println(monstruos.get(i).getName());
                        System.out.println(monstruos.get(i).getBadText());
                        System.out.println("Visibles => " + listSpecificVisible.get(j) + ".");
                        beVisible = true;
                    }
                }

                for (int k = 0; k < tamTreasureHidden; k++) {
                    if ((listSpecificHidden.get(k) == treasure)) {
                        System.out.println(monstruos.get(i).getName());
                        System.out.println(monstruos.get(i).getBadText());
                        System.out.println("Ocultos => " + listSpecificHidden.get(k) + ".");
                        beHidden = true;
                    }
                }

                if (beVisible || beHidden) {
                    monstersConsult.add(monstruos.get(i));
                }
            }
        }

        return monstersConsult;
    }

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("UnusedAssignment")
    public static void main(String[] args) {

        /**
         * VARIABLES
         */
        String         nameMonster;
        String         badCons;
        boolean        death;
        Prize          p;
        BadConsequence b;
        Monster        m;

        // **************************El Rey de Rosado***************************
        nameMonster = "El Rey de Rosado";
        badCons     = "Pierdes 5 niveles y 3 tesoros visibles.";
        death       = false;
        p           = new Prize(3, 2);
        b           = new BadConsequence(badCons, 5, 3, 0);
        m           = new Monster(nameMonster, 11, b, p);
        monstruos.add(m);

        // ************************3 Byakhees de Bonanza************************
        nameMonster = "3 Byakhees de Bonanza";
        badCons     = "Pierdes tu armadura visible y otra oculta.";
        death       = false;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons,
                                         0,
                                         new ArrayList(Arrays.asList(TreasureKind.ARMOR)),
                                         new ArrayList(Arrays.asList(TreasureKind.ARMOR)));
        m = new Monster(nameMonster, 8, b, p);
        monstruos.add(m);

        // ************************Tenochtitlan*********************************
        nameMonster = "Tenochtitlan";
        badCons     = "Embobados con el lindo primigenio te descartas de tu casco visible.";
        death       = false;
        p           = new Prize(1, 1);
        b           = new BadConsequence(badCons,
                                         0,
                                         new ArrayList(Arrays.asList(TreasureKind.HELMET)),
                                         new ArrayList());
        m           = new Monster(nameMonster, 2, b, p);
        monstruos.add(m);

        // ************************El Sopor de Dunwich**************************
        nameMonster = "El Sopor de Dunwich";
        badCons     = "El primordial bostezo contagioso. Pierdes el calzado visible.";
        death       = false;
        p           = new Prize(1, 1);
        b           = new BadConsequence(badCons, 0, new ArrayList(Arrays.asList(TreasureKind.SHOE)), new ArrayList());
        m           = new Monster(nameMonster, 2, b, p);
        monstruos.add(m);

        // ************************Demonios de Magaluf**************************
        nameMonster = "Demonios de Magaluf";
        badCons     =
            "Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta.";
        death = false;
        p     = new Prize(4, 1);
        b     = new BadConsequence(badCons,
                                   0,
                                   new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
                                   new ArrayList(Arrays.asList(TreasureKind.ONEHAND)));
        m = new Monster(nameMonster, 2, b, p);
        monstruos.add(m);

        // ************************El Gorrón en el Umbral***********************
        nameMonster = "El Gorrón en el Umbral";
        badCons     = "Pierdes todos tus tesoros visibles.";
        death       = false;
        p           = new Prize(3, 1);
        b           = new BadConsequence(badCons, 0, Integer.MAX_VALUE, 0);
        m           = new Monster(nameMonster, 13, b, p);
        monstruos.add(m);

        // ***************************H.P. Munchcraft***************************
        nameMonster = "H.P. Munchcraft";
        badCons     = "Pierdes la armadura visible.";
        death       = false;
        p           = new Prize(1, 1);
        b           = new BadConsequence(badCons, 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList());
        m           = new Monster(nameMonster, 13, b, p);
        monstruos.add(m);

        // *****************************Necrófago*******************************
        nameMonster = "Necrófago";
        badCons     = "Sientes bichos bajo la ropa. Descarta la armadura visible.";
        death       = false;
        p           = new Prize(3, 1);
        b           = new BadConsequence(badCons, 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList());
        m           = new Monster(nameMonster, 13, b, p);
        monstruos.add(m);

        // ******************************Flecher********************************
        nameMonster = "Flecher";
        badCons     = "Toses los pulmones y pierdes 2 niveles.";
        death       = false;
        p           = new Prize(1, 1);
        b           = new BadConsequence(badCons, 2, 0, 0);
        m           = new Monster(nameMonster, 2, b, p);
        monstruos.add(m);

        // ****************************Los Hondos*******************************
        nameMonster = "Los Hondos";
        badCons     = "Estos monstruos resultan bastante superficiales y te aburren mortalmente. Estas muerto.";
        death       = true;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons, death);
        m           = new Monster(nameMonster, 8, b, p);
        monstruos.add(m);

        // ***************************Semillas Cthulhu**************************
        nameMonster = "Semillas Cthulhu";
        badCons     = "Pierdes 2 niveles y 2 tesoros ocultos.";
        death       = false;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons, 2, 0, 2);
        m           = new Monster(nameMonster, 4, b, p);
        monstruos.add(m);

        // ******************************Dameargo*******************************
        nameMonster = "Dameargo";
        badCons     = "Te intentas escaquear. Pierdes una mano visible.";
        death       = false;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons,
                                         0,
                                         new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
                                         new ArrayList());
        m           = new Monster(nameMonster, 1, b, p);
        monstruos.add(m);

        // ************************Polllipólipo Volante*************************
        nameMonster = "Polllipólipo Volante";
        badCons     = "Da mucho asquito. Pierdes 3 niveles.";
        death       = false;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons, 3, 0, 0);
        m           = new Monster(nameMonster, 3, b, p);
        monstruos.add(m);

        // ***************************Yskhtihyssg-Goth**************************
        nameMonster = "Yskhtihyssg-Goth";
        badCons     = "No le hace gracia que pronuncien mal su nombre. Estas muerto.";
        death       = true;
        p           = new Prize(3, 1);
        b           = new BadConsequence(badCons, death);
        m           = new Monster(nameMonster, 14, b, p);
        monstruos.add(m);

        // ****************************Familia Feliz****************************
        nameMonster = "Familia Feliz";
        badCons     = "La familia te atrapa. Estáss muerto.";
        death       = true;
        p           = new Prize(3, 1);
        b           = new BadConsequence(badCons, death);
        m           = new Monster(nameMonster, 1, b, p);
        monstruos.add(m);

        // *****************************Roboggoth*******************************
        nameMonster = "Roboggoth";
        badCons     = "La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible.";
        death       = false;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons,
                                         2,
                                         new ArrayList(Arrays.asList(TreasureKind.BOTHHAND)),
                                         new ArrayList());
        m           = new Monster(nameMonster, 8, b, p);
        monstruos.add(m);

        // ****************************El Espía Sordo***************************
        nameMonster = "El Espía Sordo";
        badCons     = "Te asusta en la noche. Pierdes un casco visible.";
        death       = false;
        p           = new Prize(1, 1);
        b           = new BadConsequence(badCons,
                                         2,
                                         new ArrayList(Arrays.asList(TreasureKind.HELMET)),
                                         new ArrayList());
        m           = new Monster(nameMonster, 5, b, p);
        monstruos.add(m);

        // ********************************Tongue*******************************
        nameMonster = "Tongue";
        badCons     = "Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles.";
        death       = false;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons, 2, 5, 0);
        m           = new Monster(nameMonster, 19, b, p);
        monstruos.add(m);

        // ****************************Bicéfalo*********************************
        nameMonster = "Bicéfalo";
        badCons     = "Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos.";
        death       = false;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons,
                                         3,
                                         new ArrayList(Arrays.asList(TreasureKind.ONEHAND,
                                                                     TreasureKind.ONEHAND,
                                                                     TreasureKind.BOTHHAND)),
                                         new ArrayList());
        m = new Monster(nameMonster, 21, b, p);
        monstruos.add(m);

        /*
         *  printMonsters(monstruos);
         * monstersResult = biggerThen(monstruos, 20);
         * printMonsters(monstersResult);
         * System.out.println("########################################################################");
         * monstersResult = lossLevels(monstruos);
         * printMonsters(monstersResult);
         * System.out.println("########################################################################");
         * monstersResult = greedLevels(monstruos, 1);
         * printMonsters(monstersResult);
         */

        /*
         * System.out.println("###################################  ARMOR  #####################################");
         * lossSpecificTreasures(monstruos, TreasureKind.ARMOR);
         * System.out.println("###################################  ONEHAND  ###################################");
         * lossSpecificTreasures(monstruos, TreasureKind.ONEHAND);
         * System.out.println("###################################  BOTHHAND  ##################################");
         * lossSpecificTreasures(monstruos, TreasureKind.BOTHHAND);
         * System.out.println("###################################  HELMET  ####################################");
         * lossSpecificTreasures(monstruos, TreasureKind.HELMET);
         * System.out.println("###################################  SHOE  ######################################");
         * lossSpecificTreasures(monstruos, TreasureKind.SHOE);
         * System.out.println("###################################  NECKLACE  ##################################");
         * lossSpecificTreasures(monstruos, TreasureKind.NECKLACE);
         */
        monstersResult = lossSpecificTreasures(monstruos, TreasureKind.SHOE);
        printMonsters(monstersResult);
    }

    public static void printMonsters(ArrayList<Monster> monstruos) {
        tamMonsters = monstruos.size();

        switch (tamMonsters) {
        case 0 :
            System.out.println("No fuerón encontrados monstruos:");

            break;

        case 1 :
            System.out.println("Fue encontrado " + tamMonsters + " monstruo:");

            break;

        default :
            System.out.println("Fuerón encontrados " + tamMonsters + " monstruos:");

            break;
        }

        for (int i = 0; i < tamMonsters; i++) {
            System.out.println(monstruos.get(i));
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
