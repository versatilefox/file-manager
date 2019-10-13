package com.FTPFileManager.dao;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface Manager {
    List<File> getAll(String currentDir);
    void deleteFileByPath(String pathTo);
    File getFileByPath(String path);
    boolean uploadFile(MultipartFile file, String path);
    boolean createDirectory(String currentDir, String name);
    InputStreamResource downloadFile(String path);
    void createTxt (String name, String path, String text);
    String getText(String path);
}
