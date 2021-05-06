/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author dlham
 */
public class Dashboard implements Serializable {

    private int totalImagesCount;
    private double totalImagesSize;

    private int totalVideosCount;
    private double totalVideosSize;

    private int totalAudiosCount;
    private double totalAudiosSize;

    private int totalDocumentsCount;
    private double totalDocumentsSize;

    private ArrayList<File> recentFiles;

    public int getTotalImagesCount() {
        return totalImagesCount;
    }

    public void setTotalImagesCount(int totalImagesCount) {
        this.totalImagesCount = totalImagesCount;
    }

    public double getTotalImagesSize() {
        return totalImagesSize;
    }

    public void setTotalImagesSize(double totalImagesSize) {
        this.totalImagesSize = totalImagesSize;
    }

    public int getTotalVideosCount() {
        return totalVideosCount;
    }

    public void setTotalVideosCount(int totalVideosCount) {
        this.totalVideosCount = totalVideosCount;
    }

    public double getTotalVideosSize() {
        return totalVideosSize;
    }

    public void setTotalVideosSize(double totalVideosSize) {
        this.totalVideosSize = totalVideosSize;
    }

    public int getTotalAudiosCount() {
        return totalAudiosCount;
    }

    public void setTotalAudiosCount(int totalAudiosCount) {
        this.totalAudiosCount = totalAudiosCount;
    }

    public double getTotalAudiosSize() {
        return totalAudiosSize;
    }

    public void setTotalAudiosSize(double totalAudiosSize) {
        this.totalAudiosSize = totalAudiosSize;
    }

    public int getTotalDocumentsCount() {
        return totalDocumentsCount;
    }

    public void setTotalDocumentsCount(int totalDocumentsCount) {
        this.totalDocumentsCount = totalDocumentsCount;
    }

    public double getTotalDocumentsSize() {
        return totalDocumentsSize;
    }

    public void setTotalDocumentsSize(double totalDocumentsSize) {
        this.totalDocumentsSize = totalDocumentsSize;
    }

    public ArrayList<File> getRecentFiles() {
        return recentFiles;
    }

    public void setRecentFiles(ArrayList<File> recentFiles) {
        this.recentFiles = recentFiles;
    }

}
