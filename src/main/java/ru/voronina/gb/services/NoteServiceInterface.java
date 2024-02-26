package ru.voronina.gb.services;

import ru.voronina.gb.model.Note;

import java.util.List;

public interface NoteServiceInterface {

    List<Note> getAllNotes();
    Note createNote(Note note);
    Note getNoteById(Long id);
    Note updateNote(Long id, String changeHeading, String changeContent);
    void deleteNote(Long id);
}
