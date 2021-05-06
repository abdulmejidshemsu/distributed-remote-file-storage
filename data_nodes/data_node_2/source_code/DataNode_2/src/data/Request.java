package data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author dlham
 */
public class Request implements Serializable {

    private static final long serialVersionUID = 11L;

    private String requestType;
    private ArrayList<ChunkedByte> chunkedBytes;
    private long totalStorageSpace;
    private long freeStorageSpace;
    private boolean writeSuccess;
    private String uniqueChunkName;
    private byte[] individualChunk;
    private long fileId;

    public Request(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public ArrayList<ChunkedByte> getChunkedBytes() {
        return chunkedBytes;
    }

    public void setChunkedBytes(ArrayList<ChunkedByte> chunkedBytes) {
        this.chunkedBytes = chunkedBytes;
    }

    public long getTotalStorageSpace() {
        return totalStorageSpace;
    }

    public void setTotalStorageSpace(long totalStorageSpace) {
        this.totalStorageSpace = totalStorageSpace;
    }

    public long getFreeStorageSpace() {
        return freeStorageSpace;
    }

    public void setFreeStorageSpace(long freeStorageSpace) {
        this.freeStorageSpace = freeStorageSpace;
    }

    public boolean isWriteSuccess() {
        return writeSuccess;
    }

    public void setWriteSuccess(boolean writeSuccess) {
        this.writeSuccess = writeSuccess;
    }

    public String getUniqueChunkName() {
        return uniqueChunkName;
    }

    public void setUniqueChunkName(String uniqueChunkName) {
        this.uniqueChunkName = uniqueChunkName;
    }

    public byte[] getIndividualChunk() {
        return individualChunk;
    }

    public void setIndividualChunk(byte[] individualChunk) {
        this.individualChunk = individualChunk;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }
}
