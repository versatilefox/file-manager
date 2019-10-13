package com.FTPFileManager.dao;

import com.FTPFileManager.model.Note;

import java.util.Date;


public interface DAO {
    void create(String path, String message, Date date);
    void delete(String path);
    Note find(String path);
}
