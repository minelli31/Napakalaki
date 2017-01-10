
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;

/**
 *
 * @author minelli
 */
public class BadConsequenceFactory {
    public static BadConsequence create(String t, int l, ArrayList<TreasureKind> v, ArrayList<TreasureKind> h) {
        return new SpecificBadConsequence(t, l, v, h);
    }

    public static BadConsequence create(String t, int l, int nVisible, int nHidden) {
        return new NumericBadConsequence(t, l, nVisible, nHidden);
    }

    public static BadConsequence create(String t, int l, int nVisible, int nHidden, boolean death) {
        return new DeathBadConsequence(t, l, nVisible, nHidden, death);
    }
}
