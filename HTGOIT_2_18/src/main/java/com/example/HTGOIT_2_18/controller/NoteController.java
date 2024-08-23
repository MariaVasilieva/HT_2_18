package com.example.HTGOIT_2_18.controller;

import com.example.HTGOIT_2_18.model.Note;
import com.example.HTGOIT_2_18.model.NoteService;
import com.example.HTGOIT_2_18.model.exception.NoteNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    // GET /api/notes
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.listAll();
        return ResponseEntity.ok(notes);
    }

    // GET /api/notes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable long id) {
        Note note = noteService.getById(id);
        return ResponseEntity.ok(note);
    }

    // POST /api/notes
    @PostMapping
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        Note createdNote = noteService.add(note);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }

    // PUT /api/notes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable long id, @Valid @RequestBody Note note) {
        note.setId(id);
        return ResponseEntity.ok(noteService.update(note));
    }

    // DELETE /api/notes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable long id) {
        noteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<String> handleNoteNotFound(NoteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}