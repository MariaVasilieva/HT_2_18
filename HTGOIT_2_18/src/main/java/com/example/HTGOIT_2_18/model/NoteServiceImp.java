package com.example.HTGOIT_2_18.model;

import com.example.HTGOIT_2_18.model.exception.NoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImp implements NoteService {
    private final NoteRepository noteRepository;
    @Override
    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note add(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public Note update(Note note) {
        Note existingNote = noteRepository.findById(note.getId())
                .orElseThrow(()-> new NoteNotFoundException("Note with id " + note.getId() + " not found"));
        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
        return noteRepository.save(existingNote);
    }

    @Override
    public Note getById(long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note with id " + id + " not found"));
    }
}
