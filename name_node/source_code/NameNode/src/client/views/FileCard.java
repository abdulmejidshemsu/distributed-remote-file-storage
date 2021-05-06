/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.views;

import client.RemtoteFileStorageClient;
import utils.FileUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger; 
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel; 
import data.File;

/**
 *
 * @author dlham
 */
public class FileCard extends JPanel {

    private final File file;
    private final FileCard card;
    private final String currentPanel;

    public FileCard(File file, String currentPanel) {
        this.file = file;
        this.card = this;
        this.currentPanel = currentPanel;
        this.setBackground(new java.awt.Color(255, 255, 255));
        this.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 4, true));
        this.setForeground(new java.awt.Color(0, 0, 255));
        this.setSize(new java.awt.Dimension(300, 200));

        JLabel extensionIcon = this.createExtensionIcon();
        JLabel fileName = this.createFileName();
        JLabel fileSize = this.createFileSize();
        JLabel deleteButton = this.createDeleteButton();
        JLabel shareButton = this.createShareButton();
        JLabel favoriteButton = this.createFavoriteButton();
        JLabel downloadButton = this.createDownloadButton();

        javax.swing.GroupLayout fielCardLayout = new javax.swing.GroupLayout(this);
        this.setLayout(fielCardLayout);

        fielCardLayout.setHorizontalGroup(
                fielCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(fielCardLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(extensionIcon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(fielCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(fileName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(fielCardLayout.createSequentialGroup()
                                                .addComponent(fileSize)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(fielCardLayout.createSequentialGroup()
                                .addContainerGap(88, Short.MAX_VALUE)
                                .addComponent(shareButton)
                                .addGap(25, 25, 25)
                                .addComponent(favoriteButton)
                                .addGap(25, 25, 25)
                                .addComponent(downloadButton)
                                .addGap(25, 25, 25)
                                .addComponent(deleteButton))
        );

        fielCardLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[]{deleteButton, downloadButton, favoriteButton, shareButton});

        fielCardLayout.setVerticalGroup(
                fielCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(fielCardLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(fielCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(extensionIcon)
                                        .addGroup(fielCardLayout.createSequentialGroup()
                                                .addComponent(fileName)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(fileSize)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                .addGroup(fielCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(shareButton)
                                        .addComponent(favoriteButton)
                                        .addComponent(downloadButton)
                                        .addComponent(deleteButton))
                                .addContainerGap())
        );
    }

    private JLabel createExtensionIcon() {
        JLabel extensionIcon = new JLabel();
        extensionIcon.setBackground(new java.awt.Color(255, 255, 255));
        try {
            extensionIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/assets/images/file_icons/" + file.getExtension() + ".png")));
        } catch (Exception e) {
            extensionIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/assets/images/file_icons/unknown.png")));
        }

        return extensionIcon;
    }

    private JLabel createFileName() {
        JLabel fileName = new JLabel(file.getName());
        fileName.setOpaque(false);
        fileName.setBorder(null);
        fileName.setMinimumSize(new Dimension(80, 20));
        fileName.setMaximumSize(new Dimension(80, 20));
        fileName.setPreferredSize(new Dimension(80, 20));
        fileName.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        fileName.setToolTipText(file.getName());
        return fileName;
    }

    private JLabel createFileSize() {
        JLabel fileSize = new JLabel();
        fileSize.setText(FileUtils.humanReadableByte((long) file.getSize()));
        return fileSize;
    }

    private JLabel createDeleteButton() {
        JLabel deleteButton = new JLabel();
        deleteButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/assets/images/file_icons/delete.png")));
        deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteButton.setToolTipText("Delete " + file.getName());
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    RemtoteFileStorageClient.rfsReference.delete(file);
                    card.setVisible(false);
                } catch (RemoteException ex) {
                    Logger.getLogger(FileCard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return deleteButton;
    }

    private JLabel createShareButton() {
        JLabel shareButton = new JLabel();
        shareButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shareButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/assets/images/file_icons/share.png")));
        return shareButton;
    }

    private JLabel createFavoriteButton() {
        JLabel favoriteButton = new JLabel();
        favoriteButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        favoriteButton.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/client/assets/images/file_icons/" + (file.isIsFavorite() ? "favorite" : "not_favorite") + ".png"))
        );
        favoriteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        favoriteButton.setToolTipText((file.isIsFavorite() ? "Remove from" : "Make") + " favorite " + file.getName());
        favoriteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    RemtoteFileStorageClient.rfsReference.makeFavorite(file);
                    file.setIsFavorite(!file.isIsFavorite());
                    if ("favoritePanel".equals(currentPanel) && !file.isIsFavorite()) { 
                        card.setVisible(false);
                    } else {
                        favoriteButton.setIcon(new javax.swing.ImageIcon(
                                getClass().getResource("/client/assets/images/file_icons/" + (file.isIsFavorite() ? "favorite" : "not_favorite") + ".png"))
                        );
                        favoriteButton.setToolTipText((file.isIsFavorite() ? "Remove from" : "Make") + " favorite " + file.getName());
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(FileCard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return favoriteButton;
    }

    private JLabel createDownloadButton() {
        JLabel downloadButton = new JLabel();
        downloadButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        downloadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/assets/images/file_icons/download.png")));
        downloadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        downloadButton.setToolTipText("Download " + file.getName());
        downloadButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                final JFileChooser fileChooser = new JFileChooser(); 
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setApproveButtonText("Download");
                int isFileSelected = fileChooser.showOpenDialog(card);

                if (isFileSelected == JFileChooser.APPROVE_OPTION) {
                    RemtoteFileStorageClient.download(file, fileChooser.getSelectedFile().toString());
                }
            }
        });

        return downloadButton;
    }
}
