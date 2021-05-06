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
public class ChunkedByte implements  Serializable  {
    private long fileId;
    private long size;
    private int part;
    private int replica;
    private byte[] content; 
    private long dataNodeId;  
    private String uniqueChunkName;

    public long getDataNodeId() {
        return dataNodeId;
    }

    public void setDataNodeId(long dataNodeId) {
        this.dataNodeId = dataNodeId;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public int getReplica() {
        return replica;
    }

    public void setReplica(int replica) {
        this.replica = replica;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getUniqueChunkName() {
        return uniqueChunkName;
    }

    public void setUniqueChunkName(String uniqueChunkName) {
        this.uniqueChunkName = uniqueChunkName;
    }
}
