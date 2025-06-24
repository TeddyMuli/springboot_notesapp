package com.example.NotesApp.repository;

import com.example.NotesApp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    // Find all notes ordered by creation date (newest first)
    List<Note> findAllByOrderByCreatedAtDesc();

    // Alternative method using @Query annotation (use this if the above doesn't work)
    @Query("SELECT n FROM Note n ORDER BY n.createdAt DESC")
    List<Note> findAllNotesOrderedByCreatedAt();

    // Search notes by title or content (case-insensitive)
    @Query("SELECT n FROM Note n WHERE " +
            "LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(n.content) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "ORDER BY n.createdAt DESC")
    List<Note> findByTitleOrContentContainingIgnoreCase(@Param("keyword") String keyword);
}
