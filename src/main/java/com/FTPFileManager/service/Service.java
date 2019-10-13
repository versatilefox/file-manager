package com.FTPFileManager.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface Service {
    List<File> getAll(String currentDir);
    void deleteFileByPath(String pathTo);
    File getFileByPath(String path);
    boolean createDirectory(String currentDir, String name);
    boolean uploadFile(MultipartFile file, String path);
    InputStreamResource downloadFile(String path);
    void createTxt (String name, String path, String text);
    String getText(String path);
}
