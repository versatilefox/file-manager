package com.FTPFileManager.dao;

import com.FTPFileManager.GlobalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Component
public class FileManager implements Manager {

    @Autowired
    private GlobalProperties globalProperties;

    public FileManager(GlobalProperties globalProperties) {
        this.globalProperties = globalProperties;
    }

    public String getText(String path){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return sb.toString();
    }

    public void createTxt (String name, String path, String text){
        File f = new File(path + "/" + name);
        FileWriter w = null;
        try {
            w = new FileWriter(f);
            w.write(text);
            w.flush();
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<File> getAll(String currentDir){
        File file = new File(currentDir);
        List<File> files = Arrays.asList(file.listFiles());
        return files;
    }


    @Override
    public void deleteFileByPath(String pathTo) {
        File file = new File(pathTo);
        final boolean delete = file.delete();
    }

    @Override
    public File getFileByPath(String path) {

        return new File(path);
    }

    @Override
    public boolean uploadFile(MultipartFile file, String path) {
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
            Path p = Paths.get(path + "/" + file.getOriginalFilename());
            Files.write(p, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (new File(path + "/" + file.getOriginalFilename()).exists()) return true;
        else return false;
    }

    public boolean createDirectory(String currentDir, String name){
        File files = new File(currentDir + "/" + name);
        boolean isCreated = files.mkdir();
        return isCreated;
    }

    public InputStreamResource downloadFile(String path){
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return resource;
    }


}
