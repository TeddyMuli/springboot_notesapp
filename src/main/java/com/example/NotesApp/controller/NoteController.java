package com.example.NotesApp.controller;

import com.example.NotesApp.model.Note;
import com.example.NotesApp.service.NoteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // GET /api/notes - Get all notes
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        return ResponseEntity.ok(notes);
    }

    // GET /api/notes/{id} - Get note by ID
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> note = noteService.getNoteById(id);
        return note.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/notes - Create new note
    @PostMapping
    @Transactional
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        Note savedNote = noteService.saveNote(note);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
    }

    // PUT /api/notes/{id} - Update existing note
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id,
                                           @Valid @RequestBody Note noteDetails) {
        Note updatedNote = noteService.updateNote(id, noteDetails);
        return ResponseEntity.ok(updatedNote);
    }

    // DELETE /api/notes/{id} - Delete note
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/notes/search?keyword=term - Search notes
    @GetMapping("/search")
    public ResponseEntity<List<Note>> searchNotes(@RequestParam String keyword) {
        List<Note> notes = noteService.searchNotes(keyword);
        return ResponseEntity.ok(notes);
    }

    // GET /api/notes/count - Get total count of notes
    @GetMapping("/count")
    public ResponseEntity<Long> getNotesCount() {
        long count = noteService.getAllNotes().size();
        return ResponseEntity.ok(count);
    }

    // GET /api/notes/recent - Get recent notes (last 5)
    @GetMapping("/recent")
    public ResponseEntity<List<Note>> getRecentNotes() {
        List<Note> allNotes = noteService.getAllNotes();
        List<Note> recentNotes = allNotes.stream()
                .limit(5)
                .toList();
        return ResponseEntity.ok(recentNotes);
    }
}