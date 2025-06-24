package com.example.NotesApp.service;

import com.example.NotesApp.exception.NoteNotFoundException;
import com.example.NotesApp.model.Note;
import com.example.NotesApp.repository.NoteRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Note> getAllNotes() {
        System.out.println("NoteService: Fetching all notes...");
        List<Note> notes = noteRepository.findAllByOrderByCreatedAtDesc();
        System.out.println("NoteService: Found " + notes.size() + " notes");
        for (Note note : notes) {
            System.out.println("Note: " + note.getId() + " - " + note.getTitle());
        }
        return notes;
    }

    @Override
    public Optional<Note> getNoteById(Long id) {
        System.out.println("NoteService: Fetching note with id: " + id);
        return noteRepository.findById(id);
    }

    @Override
    @Transactional
    public Note saveNote(Note note) {
        System.out.println("NoteService: Saving note - Title: " + note.getTitle());
        Note savedNote = noteRepository.save(note);

        // Force flush to ensure the data is written to DB
        noteRepository.flush();

        System.out.println("NoteService: Note saved with ID: " + savedNote.getId());

        // Verify the note was actually saved
        long totalCount = noteRepository.count();
        System.out.println("NoteService: Total notes in database: " + totalCount);

        return savedNote;
    }

    @Override
    public Note updateNote(Long id, Note noteDetails) {
        System.out.println("NoteService: Updating note with id: " + id);
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id: " + id));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        return noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        System.out.println("NoteService: Deleting note with id: " + id);
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id " + id));

        noteRepository.delete(note);
    }

    @Override
    public List<Note> searchNotes(String keyword) {
        System.out.println("NoteService: Searching notes with keyword: " + keyword);
        return noteRepository.findByTitleOrContentContainingIgnoreCase(keyword);
    }
}
