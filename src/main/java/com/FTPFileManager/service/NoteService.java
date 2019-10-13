package com.FTPFileManager.service;

import com.FTPFileManager.model.Note;

import java.util.Date;

public interface NoteService {
    void create(String path, String message, Date date);
    void delete(String path);
    Note find(String path);

}
