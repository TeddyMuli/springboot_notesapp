package com.example.NotesApp.service;

import com.example.NotesApp.model.Note;

import java.util.Optional;
import java.util.List;

public interface NoteService {
    List<Note> getAllNotes();
    Optional<Note> getNoteById(Long id);
    Note saveNote(Note note);
    Note updateNote(Long id, Note noteDetails);
    void deleteNote(Long id);
    List<Note> searchNotes(String keyword);
}
