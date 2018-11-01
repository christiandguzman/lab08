/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.NoteDB;
import database.NotesDBException;
import java.util.Date;
import java.util.List;
import models.Note;

/**
 *
 * @author 683676
 */
public class NoteService {

    private NoteDB db;

    public NoteService() {
        db = new NoteDB();
    }

    public Note get(int noteId) throws NotesDBException {
        return db.getNote(noteId);
    }

    public List<Note> getAll() throws NotesDBException {
        return db.getAll();

    }

    public int update(int noteid, String contents) throws NotesDBException {
        Note note = new Note();
        note.setContents(contents);
        note.setDatecreated(new Date());
        note.setNoteid(noteid);
        return db.update(note);
    }

    public int insert(String contents) throws NotesDBException {
        Note note = new Note();
        note.setDatecreated(new Date());
        note.setContents(contents);
        return db.insert(note);
    }

    public int delete(int noteid) throws NotesDBException {

        return db.delete(new Note(noteid));
    }

}
