/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.views;

import java.awt.Color;
import java.text.SimpleDateFormat; 
import javax.swing.JLabel;
import javax.swing.JPanel;
import utils.FileUtils;
import data.File;

/**
 *
 * @author dlham
 */
public class ListTilePanel extends JPanel {
    private File file; 

    public ListTilePanel(File file) {
        this.file = file; 
        this.setBackground(new java.awt.Color(255, 255, 255));

        JLabel underlineLabel = this.createUnderlineLabel(); 
        JLabel fileNameLabel = this.createFileNameLabel(); 
        JLabel fileTypeLabel = this.createFileTypeLabel(); 
        JLabel fileUploadedAt = this.createFileUploadedAtLabel(); 
        JLabel fileSize = this.createFileSizeLabel(); 

        javax.swing.GroupLayout groupLayout = new javax.swing.GroupLayout(this);
        this.setLayout(groupLayout);

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(underlineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1763, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(fileNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(fileTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(277, 277, 277)
                                                .addComponent(fileUploadedAt, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(326, 326, 326)
                                                .addComponent(fileSize, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(253, 253, 253))))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(fileTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fileUploadedAt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fileSize, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addComponent(underlineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.CENTER, groupLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(fileNameLabel)
                                .addGap(11, 11, 11))
        );
    }
    
    private JLabel createUnderlineLabel() {
        JLabel underline = new JLabel();
        underline.setForeground(new java.awt.Color(204, 204, 204));
        underline.setOpaque(true);
        underline.setPreferredSize(new java.awt.Dimension(0, 1)); 
        return underline;
    }
    
    private JLabel createFileNameLabel() {
        JLabel fileName = new JLabel();
        fileName.setText("   " + file.getName());
        try{
            fileName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/assets/images/file_icons/"+ file.getExtension() +".png")));
        }
        catch(Exception e) {
            fileName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/assets/images/file_icons/unknown.png")));
        }
        
        fileName.setFont(new java.awt.Font("Tahoma", 1, 14));
        fileName.setForeground(new java.awt.Color(102, 102, 102));
        return fileName;
    }
    
    private JLabel createFileTypeLabel() {
        JLabel fileType = new JLabel();
        fileType.setFont(new java.awt.Font("Tahoma", 1, 14));
        fileType.setForeground(new java.awt.Color(102, 102, 102));
        fileType.setText(file.getType());
        return fileType;
    }
    
    private JLabel createFileUploadedAtLabel() {
        JLabel uploadedAt = new JLabel();
        uploadedAt.setFont(new java.awt.Font("Tahoma", 1, 14));
        uploadedAt.setForeground(new java.awt.Color(102, 102, 102)); 
        uploadedAt.setText((new SimpleDateFormat("MMM dd, yyyy")).format( file.getUploadedAt() ));
        return uploadedAt;
    }
    
    private JLabel createFileSizeLabel() {
        JLabel fileSize = new JLabel();
        fileSize.setFont(new java.awt.Font("Tahoma", 1, 14));
        fileSize.setForeground(new java.awt.Color(102, 102, 102));
        fileSize.setText(FileUtils.humanReadableByte((long) file.getSize()));
        return fileSize;
    }

}
