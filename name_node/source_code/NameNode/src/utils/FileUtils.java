/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import data.ChunkedByte;

/**
 *
 * @author dlham
 */
public class FileUtils {

    public static final int CHUNK_SIZE = 1 * 1024 * 1024; // 1MB max 2GB
    public static final int REPLICA_SIZE = 2;

    public static String humanReadableByte(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f %cB", value / 1024.0, ci.current());
    }

    public static byte[] fileToByte(String fileName) {
        byte[] b = null;
        try {
            java.io.File file = new java.io.File(fileName);
            b = new byte[(int) file.length()];
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
            is.read(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static String getFileType(String fileExtension) {
        fileExtension = fileExtension.toLowerCase();

        ArrayList<String> imageExtensions = new ArrayList<>(Arrays.asList(
                "jpg", "jpeg", "png", "gif", "svg", "tiff", "tif", "bmp", "eps", "raw"
        ));

        ArrayList<String> videoExtensions = new ArrayList<>(Arrays.asList(
                "webm", "mpg", "mp2", "mpeg", "mpe", "mpv", "ogg", "mp4", "m4p", "m4v", "avi",
                "wmv", "mov", "qt", "flv", "swf", "avchd", "mkv"
        ));

        ArrayList<String> documentExtensions = new ArrayList<>(Arrays.asList(
                "doc", "docx", "html", "htm", "odt", "dat", "pdf", "xls", "xlsx",
                "ods", "ppt", "pptx", "txt", "css", "php", "cpp", "js", "c", "dll"
        ));

        ArrayList<String> audioExtensions = new ArrayList<>(Arrays.asList(
                "aif", "cda", "mid", "midi", "mp3", "mpa", "ogg", "wav", "wma", "wpl"
        ));

        // Compressed
        ArrayList<String> compressedExtensions = new ArrayList<>(Arrays.asList(
                "7z", "arj", "deb", "pkg", "rar", "rpm", "tar.gz", "z", "zip"
        ));

        // Disc or Media
        ArrayList<String> discExtensions = new ArrayList<>(Arrays.asList(
                "bin", "dmg", "iso", "toast", "vdc"
        ));

        // Data or Database
        ArrayList<String> dataExtensions = new ArrayList<>(Arrays.asList(
                "csv", "dat", "db", "log", "mdb", "sav", "sql", "tar", "xml"
        ));

        ArrayList<String> emailExtensions = new ArrayList<>(Arrays.asList(
                "email", "eml", "msg", "oft", "ost", "pst", "vcf", "tar", "xml"
        ));

        ArrayList<String> executableExtensions = new ArrayList<>(Arrays.asList(
                "apk", "bat", "bin", "cgi", "pl", "com", "gadget", "jar", "msi", "py", "wsf"
        ));

        if (imageExtensions.contains(fileExtension)) {
            return "Image";
        } else if (videoExtensions.contains(fileExtension)) {
            return "Video";
        } else if (audioExtensions.contains(fileExtension)) {
            return "Audio";
        } else if (compressedExtensions.contains(fileExtension)) {
            return "Compressed";
        } else if (executableExtensions.contains(fileExtension)) {
            return "Executable";
        } else if (emailExtensions.contains(fileExtension)) {
            return "Email";
        } else if (dataExtensions.contains(fileExtension)) {
            return "Data or Database";
        } else if (discExtensions.contains(fileExtension)) {
            return "Disc or Media";
        } else if (documentExtensions.contains(fileExtension)) {
            return "Document";
        } else {
            return "Other";
        }
    }

    public static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    } 
    
    public static ArrayList<ChunkedByte> getFileChunks(byte[] mainFile, long fileId) { 
        ArrayList<ChunkedByte> chunkedBytes = new ArrayList<>();

        int part = 1;
        for (int i = 0; i < mainFile.length; part++) {
            byte[] chunk = new byte[Math.min(FileUtils.CHUNK_SIZE, mainFile.length - i)];
            for (int j = 0; j < chunk.length; j++, i++) {
                chunk[j] = mainFile[i];
            }

            int replica = FileUtils.REPLICA_SIZE;
            
            while (replica > 0) {
                String uniqueChunkName = "file_id_" + fileId + "_part_" + part + "_replica_" + replica;
                
                ChunkedByte chunkedFile = new ChunkedByte();
                chunkedFile.setFileId(fileId);
                chunkedFile.setUniqueChunkName(uniqueChunkName);
                chunkedFile.setContent(chunk);
                chunkedFile.setPart(part);
                chunkedFile.setReplica(replica);
                chunkedFile.setSize(chunk.length);
                chunkedFile.setReplica(replica--);
                
                chunkedBytes.add(chunkedFile);
            }
        }
        return chunkedBytes;
    }

}
