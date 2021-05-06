/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author dlham
 */
public class FileToBeFormed {

    private long totalSize;
    private ArrayList<Map<Integer, ChunkedByte>> chunks;

    public boolean isChunkedAdded(ChunkedByte chunk) {
        for (Map<Integer, ChunkedByte> map : chunks) {
            for (Map.Entry<Integer, ChunkedByte> entry : map.entrySet()) { 
                if(entry.getKey() == chunk.getPart()) {
                    return true;
                }
            }
        }
        return false;
    }
    
//    public boolean addChunks()
}
