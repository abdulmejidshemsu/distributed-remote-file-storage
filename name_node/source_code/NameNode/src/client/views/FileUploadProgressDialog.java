/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.views;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 *
 * @author dlham
 */
public class FileUploadProgressDialog extends JDialog {
    
    private JProgressBar progressBar;

    public FileUploadProgressDialog(JFrame frame) {
        setTitle("Uploading");
        setSize(400, 200);
        setLocationRelativeTo(frame);
        this.setModal(true);
        progressBar();
    }

    private void progressBar() {
        progressBar = new JProgressBar();
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        add(progressBar); 
    }

    public void iterate() {
        System.out.println("iterate");
        int i = 0;
        while (i <= 2000) {
            progressBar.setValue(i);
            i = i + 20;
            System.out.println("i: " + i);
            try {
                Thread.sleep(150);
            } catch (Exception e) {
            }
        }
    }

}
