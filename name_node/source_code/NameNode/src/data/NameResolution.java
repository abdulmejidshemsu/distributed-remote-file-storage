/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;

/**
 *
 * @author dlham
 */
public class NameResolution implements Serializable {
    private long fileId;
    private long dataNodeId;
    private String ipAddress;
    private int port;
    private int part;
    private int replcia;
    private long size;
    private String uniqueChunkName;

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public long getDataNodeId() {
        return dataNodeId;
    }

    public void setDataNodeId(long dataNodeId) {
        this.dataNodeId = dataNodeId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public int getReplcia() {
        return replcia;
    }

    public void setReplcia(int replcia) {
        this.replcia = replcia;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUniqueChunkName() {
        return uniqueChunkName;
    }

    public void setUniqueChunkName(String uniqueChunkName) {
        this.uniqueChunkName = uniqueChunkName;
    }
}
