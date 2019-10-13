package com.FTPFileManager.service;

import com.FTPFileManager.dao.DAO;
import com.FTPFileManager.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private DAO dao;

    public NoteServiceImpl(DAO dao) {
        this.dao = dao;
    }


    @Override
    public void create(String path, String message, Date date) {

        dao.create(path, message, date);
    }

    @Override
    public void delete(String path) {
        dao.delete(path);
    }

    @Override
    public Note find(String path) {
        return dao.find(path);
    }

}
