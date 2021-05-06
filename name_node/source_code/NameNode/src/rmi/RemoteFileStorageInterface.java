/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import data.Dashboard;
import data.File;
import data.NameResolution;

/**
 *
 * @author dlham
 */
public interface RemoteFileStorageInterface extends Remote {
    public Dashboard getDashboardData() throws RemoteException;
    public ArrayList<File> getAllFiles() throws RemoteException;
    public ArrayList<File> getFilesWithExtension(String extension) throws RemoteException;
    public ArrayList<File> getImages() throws RemoteException;
    public ArrayList<File> getAudios() throws RemoteException;
    public ArrayList<File> getVideos() throws RemoteException;
    public ArrayList<File> getDocuments() throws RemoteException;
    public ArrayList<File> getFavorites() throws RemoteException;
    public ArrayList<File> getWithFileNameLike(String query) throws RemoteException;
    public void upload(String filename, long fileSize, byte[] fileContent) throws RemoteException;
    public void delete(File file) throws RemoteException;
    public ArrayList<NameResolution> getNameResolution(File file) throws RemoteException;
    public void makeFavorite(File file) throws RemoteException;
}
