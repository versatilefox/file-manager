package com.FTPFileManager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "note")
public class Note implements Serializable {

    @Id
    @Column(name = "path")
    private String path;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "message")
    private String message;

    public Note() {

    }

    public Note(String path, Date date, String message) {
        this.path = path;
        this.date = date;
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

//    @ElementCollection
//    @CollectionTable(name = "history", joinColumns = {@JoinColumn(name = "path", referencedColumnName = "path")})
//    @MapKeyColumn(name = "date")
//    @Column(name = "message")
//    private Map<Date, String> pathMessage;


