package com.FTPFileManager.dao;

import com.FTPFileManager.model.Note;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@Transactional
@Repository
public class NoteDAO implements DAO {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void create(String path, String message, Date date) {
        if (entityManager.find(Note.class, path) == null){
            entityManager.persist(new Note(path, date, message));
        }else {
            Note note = entityManager.getReference(Note.class, path);
            note.setMessage(message);
            entityManager.merge(note);
        }
    }

    @Override
    public void delete(String path) {
        Note n = entityManager.find(Note.class, path);
        if (n != null) entityManager.remove(n);
    }

    @Override
    public Note find(String path) {
        return entityManager.find(Note.class, path);
    }

}
