package com.FTPFileManager.service;

import com.FTPFileManager.dao.FileManager;
import com.FTPFileManager.dao.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FileService implements com.FTPFileManager.service.Service {
    @Autowired
    private Manager manager;

    public FileService(Manager manager) {
        this.manager = manager;
    }

    public List<File> getAll(String currentDir){

        return manager.getAll(currentDir);
    }


    @Override
    public void deleteFileByPath(String pathTo) {
        manager.deleteFileByPath(pathTo);
    }

    @Override
    public File getFileByPath(String path) {
        return manager.getFileByPath(path);
    }

    @Override
    public boolean createDirectory(String currentDir, String name) {
        return manager.createDirectory(currentDir, name);
    }

    @Override
    public boolean uploadFile(MultipartFile file, String path) {
        return manager.uploadFile(file, path);
    }

    @Override
    public InputStreamResource downloadFile(String path) {
        return manager.downloadFile(path);
    }

    @Override
    public void createTxt(String name, String path, String text) {
        manager.createTxt(name, path, text);
    }

    @Override
    public String getText(String path) {
        return manager.getText(path);
    }


}
