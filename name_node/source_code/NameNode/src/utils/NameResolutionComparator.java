/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import data.NameResolution;
import java.util.Comparator;

/**
 *
 * @author dlham
 */
public class NameResolutionComparator implements Comparator<NameResolution> {
    @Override
    public int compare(NameResolution nr1, NameResolution nr2)
    {
        if (nr1.getPart() > nr2.getPart()) return 1;
        return -1;
    }
}